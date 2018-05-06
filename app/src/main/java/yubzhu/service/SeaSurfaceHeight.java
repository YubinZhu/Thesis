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

public class SeaSurfaceHeight {
    private static final float SCALE_FACTOR = 1.831111E-4f;

    private static final short FILL_VALUE = 32767;

    public JSONArray getSshg(int timeIndex, float fromLat, float toLat, float fromLon, float toLon) {
        JSONArray sshgJsonArray = new JSONArray();
        try {
            NetcdfFile sshgNC = NetcdfFile.open(GodasConfig.GODAS_PATH + "/sshg.nc");
            Variable sshgVariable = sshgNC.findVariable("sshg");
            Array sshgArray = sshgVariable.read(new int[]{timeIndex, 0, 0}, new int[]{1, 418, 360});
            sshgArray = sshgArray.reduce();
            DecimalFormat decimalFormat2 = new DecimalFormat("0.00");
            for (int lat = 0; lat < 418; lat += 1) {
                if (lat / 3.0 - 74.5 < fromLat || lat / 3.0 - 74.5 > toLat) {
                    continue;
                }
                for (int lon = 0; lon < 360; lon += 1) {
                    if (lon + 0.5 < fromLon || lon + 0.5 > toLon) {
                        continue;
                    }
                    float sshg = sshgArray.getFloat(lat * 360 + lon);
                    if (sshg == FILL_VALUE) {
                        sshg = 0;
                    }
                    JSONArray jsonArray = new JSONArray();
                    jsonArray.put(Double.parseDouble(decimalFormat2.format(lon + 0.5)));
                    jsonArray.put(Double.parseDouble(decimalFormat2.format(lat / 3.0 - 74.5)));
                    jsonArray.put(Double.parseDouble(decimalFormat2.format(sshg * SCALE_FACTOR)));
                    sshgJsonArray.put(jsonArray);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidRangeException e) {
            e.printStackTrace();
        }
        return sshgJsonArray;
    }
}
