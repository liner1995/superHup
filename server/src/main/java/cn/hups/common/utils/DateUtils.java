package cn.hups.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间相关工具类
 */
public class DateUtils {
    /**
     * 获取当前系统时间的Ts (yyyy-MM-dd HH:mm:ss)
     * @return
     */
    public static String getKnowTsStr(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    /**
     * 获取当前系统时间的Ts (yyyyMMddHHmmss)
     * @return
     */
    public static String getKnowTsChar(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(new Date());
    }
}
