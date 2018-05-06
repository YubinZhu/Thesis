import org.json.JSONArray;
import ucar.ma2.Array;
import ucar.ma2.InvalidRangeException;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;

import static java.lang.Math.sqrt;

/**
 * Created by 朱宇斌 on 2018/5/2
 */

public class GetInfoTest {

    private static final float SCALE_FACTOR = 1.22074E-4f;

    public static void main(String args[]) {
        try {
            String[] strings = {"thflx", "sltfl", "sshg",
                    "dbss_obil", "dbss_obml", "uflx", "vflx",
                    "salt", "ucur", "vcur", "dzdt", "pottmp"};
            for (String string : strings) {
                NetcdfFile thflxNC = NetcdfFile.open("misc/GODAS/merged/" + string + ".nc");
                System.out.println(thflxNC.findVariable(string));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
