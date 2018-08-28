package com.qiming.sell.utils;

/**
 * @author liuqiming
 * @date 2018/8/28
 * @email liuqiminggood@gmail.com
 * @Description
 */
public class MathUtil {
    private static final Double MONEY_RANGE = 0.01;

    /**
     * 判断两个金额是否相等
     *
     * @param d1
     * @param d2
     * @return
     */
    public static Boolean equals(Double d1, Double d2) {
        Double result = Math.abs(d1 - d2);
        return result < MONEY_RANGE;
    }
}