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

public class SaltFlux {

    private static final float SCALE_FACTOR = 1.464888E-10f;

    private static final float ADD_OFFSET = -1.6E-6f;

    private static final short FILL_VALUE = 32767;

    public JSONArray getSltfl(int timeIndex, float fromLat, float toLat, float fromLon, float toLon) {
        JSONArray sltflJsonArray = new JSONArray();
        try {
            NetcdfFile sltflNC = NetcdfFile.open(GodasConfig.GODAS_PATH + "/sltfl.nc");
            Variable sltflVariable = sltflNC.findVariable("sltfl");
            Array sltflArray = sltflVariable.read(new int[]{timeIndex, 0, 0}, new int[]{1, 418, 360});
            sltflArray = sltflArray.reduce();
            DecimalFormat decimalFormat2 = new DecimalFormat("0.00");
            DecimalFormat decimalFormat12 = new DecimalFormat("0.000000000000");
            for (int lat = 0; lat < 418; lat += 1) {
                if (lat / 3.0 - 74.5 < fromLat || lat / 3.0 - 74.5 > toLat) {
                    continue;
                }
                for (int lon = 0; lon < 360; lon += 1) {
                    if (lon + 0.5 < fromLon || lon + 0.5 > toLon) {
                        continue;
                    }
                    float sltfl = sltflArray.getFloat(lat * 360 + lon);
                    if (sltfl == FILL_VALUE) {
                        sltfl = 0;
                    }
                    JSONArray jsonArray = new JSONArray();
                    jsonArray.put(Double.parseDouble(decimalFormat2.format(lon + 0.5)));
                    jsonArray.put(Double.parseDouble(decimalFormat2.format(lat / 3.0 - 74.5)));
                    jsonArray.put(Double.parseDouble(decimalFormat12.format(sltfl * SCALE_FACTOR + (sltfl == 0 ? 0 : ADD_OFFSET))));
                    sltflJsonArray.put(jsonArray);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidRangeException e) {
            e.printStackTrace();
        }
        return sltflJsonArray;
    }
}
