package yubzhu.service;

import org.json.JSONArray;
import ucar.ma2.Array;
import ucar.ma2.InvalidRangeException;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;
import yubzhu.configure.GodasConfig;

import java.io.IOException;
import java.text.DecimalFormat;

import static java.lang.Math.sqrt;

/**
 * Created by 朱宇斌 on 2018/4/21
 */

public class MomentumFlux {

    private static final float SCALE_FACTOR = 1.22074E-4f;

    private static final short FILL_VALUE = 32767;

    public JSONArray getMmtflx(int timeIndex, float fromLat, float toLat, float fromLon, float toLon) {
        JSONArray mmtJsonArray = new JSONArray();
        try {
            NetcdfFile uflxNC = NetcdfFile.open(GodasConfig.GODAS_PATH + "/uflx.nc");
            NetcdfFile vflxNC = NetcdfFile.open(GodasConfig.GODAS_PATH + "/vflx.nc");
            Variable uflxVariable = uflxNC.findVariable("uflx");
            Variable vflxVariable = vflxNC.findVariable("vflx");
            Array uflxArray = uflxVariable.read(new int[]{timeIndex, 0, 0}, new int[]{1, 418, 360});
            Array vflxArray = vflxVariable.read(new int[]{timeIndex, 0, 0}, new int[]{1, 418, 360});
            uflxArray = uflxArray.reduce();
            vflxArray = vflxArray.reduce();
            DecimalFormat decimalFormat6 = new DecimalFormat("0.000000");
            for (int lat = 0; lat < 418; lat += 1) {
                if (lat / 3.0 - 74.5 < fromLat || lat / 3.0 - 74.5 > toLat) {
                    continue;
                }
                for (int lon = 0; lon < 360; lon += 1) {
                    if (lon + 0.5 < fromLon || lon + 0.5 > toLon) {
                        continue;
                    }
                    float uflx = uflxArray.getFloat(lat * 360 + lon);
                    float vflx = vflxArray.getFloat(lat * 360 + lon);
                    if (uflx == FILL_VALUE) {
                        uflx = 0;
                    }
                    if (vflx == FILL_VALUE) {
                        vflx = 0;
                    }
                    JSONArray jsonArray = new JSONArray();
                    jsonArray.put(lon); // not 0.5 + lon
                    jsonArray.put(lat); // not -74.5 + lat / 3.0
                    jsonArray.put(Double.parseDouble(decimalFormat6.format(uflx * SCALE_FACTOR)));
                    jsonArray.put(Double.parseDouble(decimalFormat6.format(vflx * SCALE_FACTOR)));
                    jsonArray.put(Double.parseDouble(decimalFormat6.format(sqrt(uflx * uflx + vflx * vflx) * SCALE_FACTOR)));
                    mmtJsonArray.put(jsonArray);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidRangeException e) {
            e.printStackTrace();
        }
        return mmtJsonArray;
    }
}
