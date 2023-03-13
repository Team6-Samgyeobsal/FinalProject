package com.samgyeobsal.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static String dateToStr(Date date) {
        return simpleDateFormat.format(date);
    }
}
