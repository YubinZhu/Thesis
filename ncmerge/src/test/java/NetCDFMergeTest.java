import org.json.JSONArray;
import org.json.JSONObject;
import ucar.ma2.Array;
import ucar.ma2.InvalidRangeException;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;

import java.io.IOException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.sqrt;

/**
 * Created by 朱宇斌 on 2018/4/10
 */

public class NetCDFMergeTest {
    public static void main(String args[]) {
        try {
            if (false) {
                NetCDFMerge.thflxMerge();
                NetCDFMerge.sltflMerge();
                NetCDFMerge.sshgMerge();
                NetCDFMerge.dbss_obilMerge();
                NetCDFMerge.dbss_obmlMerge();
                NetCDFMerge.uflxMerge();
                NetCDFMerge.vflxMerge();
                NetCDFMerge.saltMerge();
                NetCDFMerge.ucurMerge();
                NetCDFMerge.vcurMerge();
                NetCDFMerge.dzdtMerge();
                NetCDFMerge.pottmpMerge();
            }

            NetcdfFile uflxNC = NetcdfFile.open("misc/GODAS/merged/uflx.nc");
            NetcdfFile vflxNC = NetcdfFile.open("misc/GODAS/merged/vflx.nc");
            Variable uflx = uflxNC.findVariable("uflx");
            Variable vflx = vflxNC.findVariable("vflx");
            Array uflxArray = uflx.read(new int[] {0, 0, 0}, new int[] {1, 418, 360});
            Array vflxArray = vflx.read(new int[] {0, 0, 0}, new int[] {1, 418, 360});
            uflxArray = uflxArray.reduce();
            vflxArray = vflxArray.reduce();
            JSONArray jsonArray = new JSONArray();
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            for (int lat = 0; lat < 418; lat += 1) {
                for (int lon = 0; lon < 360; lon += 1) {
                    float u = uflxArray.getFloat(lat * 360 + lon);
                    float v = vflxArray.getFloat(lat * 360 + lon);
                    if (u == 32767) {
                        //u = 0;
                    }
                    if (v == 32767) {
                        //v = 0;
                    }
                    JSONArray jsonArrayTemp = new JSONArray();
                    jsonArrayTemp.put(Double.parseDouble(decimalFormat.format(-74.5 + lat / 3.0)));
                    jsonArrayTemp.put(Double.parseDouble(decimalFormat.format(0.5 + lon)));
                    jsonArrayTemp.put(u);
                    jsonArrayTemp.put(v);
                    jsonArrayTemp.put(Double.parseDouble(decimalFormat.format(sqrt(u * u + v * v))));
                    jsonArray.put(jsonArrayTemp);
                }
            }
            System.out.println(jsonArray.length());
            System.out.println(uflxNC.getVariables());



            PrintStream printStream = new PrintStream("flx.json");
            System.setOut(printStream);
            System.out.println(jsonArray.toString());

        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidRangeException e) {
            e.printStackTrace();
        }
    }
}
