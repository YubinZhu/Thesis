import ucar.ma2.InvalidRangeException;

import java.io.IOException;

/**
 * Created by 朱宇斌 on 2018/4/10
 */

public class NetCDFMergeTest {
    public static void main(String args[]) {
        try {
            NetCDFMerge.thflxMerge();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("文件创建失败，请检查路径");
        } catch (InvalidRangeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("文件写入错误，超出范围");
        }
    }
}
