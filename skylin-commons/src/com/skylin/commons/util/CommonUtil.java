package com.skylin.commons.util;

import com.skylin.commons.enums.FormatTimePattern;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author skylin
 * <p>CreateTime:2018-07-18 16:40:01</p>
 * <p>通用工具</p>
 */
public class CommonUtil {
    /**
     * 字符串是否为空
     * @param text
     * @return
     */
    public static boolean isTextNull(String text) {
        return text == null || "".equals(text);
    }

    /**
     * 获取指定时间
     * @param calendar
     * @param pattern
     * @return
     */
    public static String getFormatTime(Calendar calendar, String pattern) {
        if (calendar == null) {
            calendar = Calendar.getInstance();
        }

        if (isTextNull(pattern)) {
            pattern = FormatTimePattern.TIME_WITH_NOTHING.getValue();
        }

        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(calendar.getTime());
    }

    /**
     * 将字符串格式化为特定格式
     * @param srcTime
     * @param srcPattern
     * @param trgtPattern
     * @return
     * @throws ParseException
     */
    public static String getFormatTimeFromText(String srcTime, String srcPattern, String trgtPattern) throws ParseException {
        if (isTextNull(srcTime)) {
            return "";
        }

        if (isTextNull(srcPattern)) {
            srcPattern = FormatTimePattern.TIME_WITH_NOTHING.getValue();
        }

        if (isTextNull(trgtPattern)) {
            trgtPattern = FormatTimePattern.TIME_WITH_NOTHING.getValue();
        }

        SimpleDateFormat srcFormatter = new SimpleDateFormat(srcTime);
        SimpleDateFormat trgtFormatter = new SimpleDateFormat(trgtPattern);

        return trgtFormatter.format(srcFormatter.parse(srcTime));
    }
}
