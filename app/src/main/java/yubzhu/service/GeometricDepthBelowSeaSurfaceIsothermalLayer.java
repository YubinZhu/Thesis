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

public class GeometricDepthBelowSeaSurfaceIsothermalLayer {

    private static final float SCALE_FACTOR = 0.1525926f;

    private static final float ADD_OFFSET = 2500.0f;

    private static final short FILL_VALUE = 32767;

    public JSONArray getDbss_obil(int timeIndex, float fromLat, float toLat, float fromLon, float toLon) {
        JSONArray dbss_obilJsonArray = new JSONArray();
        try {
            NetcdfFile dbss_obilNC = NetcdfFile.open(GodasConfig.GODAS_PATH + "/dbss_obil.nc");
            Variable dbss_obilVariable = dbss_obilNC.findVariable("dbss_obil");
            Array dbss_obilArray = dbss_obilVariable.read(new int[]{timeIndex, 0, 0}, new int[]{1, 418, 360});
            dbss_obilArray = dbss_obilArray.reduce();
            DecimalFormat decimalFormat2 = new DecimalFormat("0.00");
            for (int lat = 0; lat < 418; lat += 1) {
                if (lat / 3.0 - 74.5 < fromLat || lat / 3.0 - 74.5 > toLat) {
                    continue;
                }
                for (int lon = 0; lon < 360; lon += 1) {
                    if (lon + 0.5 < fromLon || lon + 0.5 > toLon) {
                        continue;
                    }
                    float dbss_obil = dbss_obilArray.getFloat(lat * 360 + lon);
                    if (dbss_obil == FILL_VALUE) {
                        dbss_obil = 0;
                    }
                    JSONArray jsonArray = new JSONArray();
                    jsonArray.put(Double.parseDouble(decimalFormat2.format(lon + 0.5)));
                    jsonArray.put(Double.parseDouble(decimalFormat2.format(lat / 3.0 - 74.5)));
                    jsonArray.put(Double.parseDouble(decimalFormat2.format(dbss_obil * SCALE_FACTOR + (dbss_obil == 0 ? 0 : ADD_OFFSET))));
                    dbss_obilJsonArray.put(jsonArray);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidRangeException e) {
            e.printStackTrace();
        }
        return dbss_obilJsonArray;
    }
}
