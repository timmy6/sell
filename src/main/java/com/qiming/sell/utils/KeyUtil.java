package com.qiming.sell.utils;

import java.util.Random;

/**
 * @author liuqiming
 * @date 2018/8/21
 * @email liuqiminggood@gmail.com
 * @Description
 */
public class KeyUtil {


    /**
     * 生成唯一的主键
     * 格式：时间+随机数
     *
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        System.currentTimeMillis();
        Integer number = random.nextInt(900000) + 100000;    //生成一个六位随机数
        return System.currentTimeMillis() + String.valueOf(number);
    }
}