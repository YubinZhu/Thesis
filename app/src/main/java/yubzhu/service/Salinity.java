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

public class Salinity {

    private static final float SCALE_FACTOR = 3.051851E-6f;

    private static final float ADD_OFFSET = 0.05f;

    private static final short FILL_VALUE = 32767;

    public JSONArray getSalt(int timeIndex, int levelIndex, float fromLat, float toLat, float fromLon, float toLon) {
        JSONArray saltJsonArray = new JSONArray();
        try {
            NetcdfFile saltNC = NetcdfFile.open(GodasConfig.GODAS_PATH + "/salt.nc");
            Variable saltVariable = saltNC.findVariable("salt");
            Array saltArray = saltVariable.read(new int[]{timeIndex, levelIndex, 0, 0}, new int[]{1, 1, 418, 360});
            saltArray = saltArray.reduce();
            DecimalFormat decimalFormat2 = new DecimalFormat("0.00");
            DecimalFormat decimalFormat5 = new DecimalFormat("0.00000");
            for (int lat = 0; lat < 418; lat += 1) {
                if (lat / 3.0 - 74.5 < fromLat || lat / 3.0 - 74.5 > toLat) {
                    continue;
                }
                for (int lon = 0; lon < 360; lon += 1) {
                    if (lon + 0.5 < fromLon || lon + 0.5 > toLon) {
                        continue;
                    }
                    float salt = saltArray.getFloat(lat * 360 + lon);
                    if (salt == FILL_VALUE) {
                        salt = 0;
                    }
                    JSONArray jsonArray = new JSONArray();
                    jsonArray.put(Double.parseDouble(decimalFormat2.format(lon + 0.5)));
                    jsonArray.put(Double.parseDouble(decimalFormat2.format(lat / 3.0 - 74.5)));
                    jsonArray.put(Double.parseDouble(decimalFormat5.format(salt * SCALE_FACTOR + (salt == 0 ? 0 : ADD_OFFSET))));
                    saltJsonArray.put(jsonArray);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidRangeException e) {
            e.printStackTrace();
        }
        return saltJsonArray;
    }
}
