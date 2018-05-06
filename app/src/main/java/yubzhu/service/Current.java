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
 * Created by 朱宇斌 on 2018/5/6
 */

public class Current {

    private static final float SCALE_FACTOR = 1.22074E-4f;

    private static final short FILL_VALUE = 32767;

    public JSONArray getCur(int timeIndex, int levelIndex, float fromLat, float toLat, float fromLon, float toLon) {
        JSONArray curJsonArray = new JSONArray();
        try {
            NetcdfFile ucurNC = NetcdfFile.open(GodasConfig.GODAS_PATH + "/ucur.nc");
            NetcdfFile vcurNC = NetcdfFile.open(GodasConfig.GODAS_PATH + "/vcur.nc");
            Variable ucurVariable = ucurNC.findVariable("ucur");
            Variable vcurVariable = vcurNC.findVariable("vcur");
            Array ucurArray = ucurVariable.read(new int[]{timeIndex, levelIndex, 0, 0}, new int[]{1, 1, 418, 360});
            Array vcurArray = vcurVariable.read(new int[]{timeIndex, levelIndex, 0, 0}, new int[]{1, 1, 418, 360});
            ucurArray = ucurArray.reduce();
            vcurArray = vcurArray.reduce();
            DecimalFormat decimalFormat4 = new DecimalFormat("0.0000");
            for (int lat = 0; lat < 418; lat += 1) {
                if (lat / 3.0 - 74.5 < fromLat || lat / 3.0 - 74.5 > toLat) {
                    continue;
                }
                for (int lon = 0; lon < 360; lon += 1) {
                    if (lon + 0.5 < fromLon || lon + 0.5 > toLon) {
                        continue;
                    }
                    float ucur = ucurArray.getFloat(lat * 360 + lon);
                    float vcur = vcurArray.getFloat(lat * 360 + lon);
                    if (ucur == FILL_VALUE) {
                        ucur = 0;
                    }
                    if (vcur == FILL_VALUE) {
                        vcur = 0;
                    }
                    JSONArray jsonArray = new JSONArray();
                    jsonArray.put(lon);
                    jsonArray.put(lat);
                    jsonArray.put(Double.parseDouble(decimalFormat4.format(ucur * SCALE_FACTOR)));
                    jsonArray.put(Double.parseDouble(decimalFormat4.format(vcur * SCALE_FACTOR)));
                    jsonArray.put(Double.parseDouble(decimalFormat4.format(sqrt(ucur * ucur + vcur * vcur) * SCALE_FACTOR)));
                    curJsonArray.put(jsonArray);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidRangeException e) {
            e.printStackTrace();
        }
        return curJsonArray;
    }
}
