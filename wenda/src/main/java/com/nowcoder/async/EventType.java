package com.nowcoder.async;

/**
 * Created by wenda on 2018/3/21.
 */
public enum EventType {
    LIKE(0),
    COMMENT(1),
    LOGIN(2),
    MAIL(3),
    ADDFOCUS(4),
    DELFOCUS(5);

    private int value;
    EventType(int value) { this.value = value; }
    public int getValue() { return value; }
}
