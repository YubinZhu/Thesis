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
 * Created by 朱宇斌 on 2018/5/6
 */

public class GeometricVerticalVelocity {

    private static final float SCALE_FACTOR = 1.22074E-7f;

    private static final short FILL_VALUE = 32767;

    public JSONArray getDzdt(int timeIndex, int levelIndex, float fromLat, float toLat, float fromLon, float toLon) {
        JSONArray dzdtJsonArray = new JSONArray();
        try {
            NetcdfFile dzdtNC = NetcdfFile.open(GodasConfig.GODAS_PATH + "/dzdt.nc");
            Variable dzdtVariable = dzdtNC.findVariable("dzdt");
            Array dzdtArray = dzdtVariable.read(new int[]{timeIndex, levelIndex, 0, 0}, new int[]{1, 1, 418, 360});
            dzdtArray = dzdtArray.reduce();
            DecimalFormat decimalFormat2 = new DecimalFormat("0.00");
            DecimalFormat decimalFormat10 = new DecimalFormat("0.0000000000");
            for (int lat = 0; lat < 418; lat += 1) {
                if (lat / 3.0 - 74.5 < fromLat || lat / 3.0 - 74.5 > toLat) {
                    continue;
                }
                for (int lon = 0; lon < 360; lon += 1) {
                    if (lon + 0.5 < fromLon || lon + 0.5 > toLon) {
                        continue;
                    }
                    float dzdt = dzdtArray.getFloat(lat * 360 + lon);
                    if (dzdt == FILL_VALUE) {
                        dzdt = 0;
                    }
                    JSONArray jsonArray = new JSONArray();
                    jsonArray.put(Double.parseDouble(decimalFormat2.format(lon + 0.5)));
                    jsonArray.put(Double.parseDouble(decimalFormat2.format(lat / 3.0 - 74.5)));
                    jsonArray.put(Double.parseDouble(decimalFormat10.format(dzdt * SCALE_FACTOR)));
                    dzdtJsonArray.put(jsonArray);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidRangeException e) {
            e.printStackTrace();
        }
        return dzdtJsonArray;
    }
}
