package com.qiming.sell.utils;

import com.qiming.sell.enums.CodeEnum;

/**
 * @author liuqiming
 * @date 2018/8/29
 * @email liuqiminggood@gmail.com
 * @Description
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
