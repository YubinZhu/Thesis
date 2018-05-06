package yubzhu.configure;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 * Created by 朱宇斌 on 2018/5/2
 */

public class GodasConfig {
    public static final DateTime START_TIME = DateTime.parse("2000-01", DateTimeFormat.forPattern("yyyy-MM"));

    public static final DateTime END_TIME = DateTime.parse("2018-03", DateTimeFormat.forPattern("yyyy-MM"));

    public static final String GODAS_PATH = "misc/GODAS/merged";
}
