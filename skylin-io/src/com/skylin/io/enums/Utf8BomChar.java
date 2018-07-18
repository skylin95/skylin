package com.skylin.io.enums;

/**
 * @author skylin
 * <p>CreateTime:2018-07-18 10:31:01</p>
 * <p>Utf-8的BOM头定义</p>
 */
public enum Utf8BomChar {
    FirstChar(0xEF, -17),
    SecondChar(0xBB, -69),
    ThirdChar(0xBE, -65);

    private int hexValue = 0x00;
    private int decValue = 0;

    private Utf8BomChar(int hexValue, int decValue) {
        this.hexValue = hexValue;
        this.decValue = decValue;
    }

    public int getHexValue() {
        return hexValue;
    }

    public void setHexValue(int hexValue) {
        this.hexValue = hexValue;
    }

    public int getDecValue() {
        return decValue;
    }

    public void setDecValue(int decValue) {
        this.decValue = decValue;
    }
}
