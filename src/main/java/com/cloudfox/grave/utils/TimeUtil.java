package com.cloudfox.grave.utils;

import java.time.LocalDateTime;

public class TimeUtil {

    private TimeUtil() {

    }

    public static String getData(LocalDateTime time) {
        String[] result = time.toString().split("T");
        return result[0];

    }

    public static String getTime(LocalDateTime time) {
        String[] temp = time.toString().split("T");
        String s = temp[1];

        return s.substring(0,s.indexOf("."));
    }
}
