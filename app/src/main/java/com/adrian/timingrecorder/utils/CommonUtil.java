package com.adrian.timingrecorder.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ranqing on 2017/5/30.
 */

public class CommonUtil {

    /**
     * 毫秒转日期
     *
     * @param pattern 日期格式。如：yyyy-MM-dd HH:mm:ss
     * @param millis
     * @return
     */
    public static String millis2date(String pattern, long millis) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date(millis));
    }

    /**
     * 日期转毫秒
     *
     * @param pattern 日期格式。如：yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static long date2millis(String pattern, String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        long millis = 0L;
        try {
            millis = sdf.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return millis;
    }
}
