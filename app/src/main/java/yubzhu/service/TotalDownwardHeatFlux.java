package yubzhu.service;

import org.json.JSONArray;
import ucar.ma2.Array;
import ucar.ma2.InvalidRangeException;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;
import yubzhu.configure.GodasConfig;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Created by 朱宇斌 on 2018/5/5
 */

public class TotalDownwardHeatFlux {

    private static final float SCALE_FACTOR = 0.08545183f;

    private static final short FILL_VALUE = 32767;

    public JSONArray getThflx(int timeIndex, float fromLat, float toLat, float fromLon, float toLon) {
        JSONArray thflxJsonArray = new JSONArray();
        try {
            NetcdfFile thflxNC = NetcdfFile.open(GodasConfig.GODAS_PATH + "/thflx.nc");
            Variable thflxVariable = thflxNC.findVariable("thflx");
            Array thflxArray = thflxVariable.read(new int[]{timeIndex, 0, 0}, new int[]{1, 418, 360});
            thflxArray = thflxArray.reduce();
            DecimalFormat decimalFormat2 = new DecimalFormat("0.00");
            for (int lat = 0; lat < 418; lat += 1) {
                if (lat / 3.0 - 74.5 < fromLat || lat / 3.0 - 74.5 > toLat) {
                    continue;
                }
                for (int lon = 0; lon < 360; lon += 1) {
                    if (lon + 0.5 < fromLon || lon + 0.5 > toLon) {
                        continue;
                    }
                    float thflx = thflxArray.getFloat(lat * 360 + lon);
                    if (thflx == FILL_VALUE) {
                        thflx = 0;
                    }
                    JSONArray jsonArray = new JSONArray();
                    jsonArray.put(Double.parseDouble(decimalFormat2.format(lon + 0.5)));
                    jsonArray.put(Double.parseDouble(decimalFormat2.format(lat / 3.0 - 74.5)));
                    jsonArray.put(Double.parseDouble(decimalFormat2.format(thflx * SCALE_FACTOR)));
                    thflxJsonArray.put(jsonArray);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidRangeException e) {
            e.printStackTrace();
        }
        return thflxJsonArray;
    }
}
