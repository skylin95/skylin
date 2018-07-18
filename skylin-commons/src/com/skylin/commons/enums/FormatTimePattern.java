package com.skylin.commons.enums;

/**
 * @author skylin
 * <p>CreateTime:2018-07-18 17:40</p>
 * <p>内置时间格式</p>
 */
public enum FormatTimePattern {
    DATE_WITH_NOTHING("yyyyMMdd"),
    DATE_WITH_MINUS("yyyy-MM-dd"),
    DATE_WITH_OBLIQUE("yyyy/MM/dd"),
    TIME_WITH_NOTHING("yyyyMMddHHmmss"),
    TIME_WITH_MINUS("yyyy-MM-DD HH:mm:ss"),
    TIME_WITH_OBLIQUE("yyyy/MM/DD HH:mm:ss");

    private String value;

    private FormatTimePattern(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
