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

public class GeometricDepthBelowSeaSurfaceMixingLayer {

    private static final float SCALE_FACTOR = 0.1525926f;

    private static final float ADD_OFFSET = 2500.0f;

    private static final short FILL_VALUE = 32767;

    public JSONArray getDbss_obml(int timeIndex, float fromLat, float toLat, float fromLon, float toLon) {
        JSONArray dbss_obmlJsonArray = new JSONArray();
        try {
            NetcdfFile dbss_obmlNC = NetcdfFile.open(GodasConfig.GODAS_PATH + "/dbss_obml.nc");
            Variable dbss_obmlVariable = dbss_obmlNC.findVariable("dbss_obml");
            Array dbss_obmlArray = dbss_obmlVariable.read(new int[]{timeIndex, 0, 0}, new int[]{1, 418, 360});
            dbss_obmlArray = dbss_obmlArray.reduce();
            DecimalFormat decimalFormat2 = new DecimalFormat("0.00");
            for (int lat = 0; lat < 418; lat += 1) {
                if (lat / 3.0 - 74.5 < fromLat || lat / 3.0 - 74.5 > toLat) {
                    continue;
                }
                for (int lon = 0; lon < 360; lon += 1) {
                    if (lon + 0.5 < fromLon || lon + 0.5 > toLon) {
                        continue;
                    }
                    float dbss_obml = dbss_obmlArray.getFloat(lat * 360 + lon);
                    if (dbss_obml == FILL_VALUE) {
                        dbss_obml = 0;
                    }
                    JSONArray jsonArray = new JSONArray();
                    jsonArray.put(Double.parseDouble(decimalFormat2.format(lon + 0.5)));
                    jsonArray.put(Double.parseDouble(decimalFormat2.format(lat / 3.0 - 74.5)));
                    jsonArray.put(Double.parseDouble(decimalFormat2.format(dbss_obml * SCALE_FACTOR + (dbss_obml == 0 ? 0 : ADD_OFFSET))));
                    dbss_obmlJsonArray.put(jsonArray);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidRangeException e) {
            e.printStackTrace();
        }
        return dbss_obmlJsonArray;
    }
}
