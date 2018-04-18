import ucar.ma2.InvalidRangeException;

import java.io.IOException;

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
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidRangeException e) {
            e.printStackTrace();
        }
    }
}
