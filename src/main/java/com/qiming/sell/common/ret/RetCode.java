package com.qiming.sell.common.ret;

/**
 * @author liuqiming
 * @date 2018/8/9
 * @email liuqiminggood@gmail.com
 * @Description 响应码枚举，参考HTTP状态码的语义
 */
public enum RetCode {
    /**
     * 成功
     */
    SUCCESS(0),

    /**
     * 失败
     */
    FAIL(400),

    /**
     * 未认证(签名错误)
     */
    UNAUTHORIZED(401),

    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(500);

    public int code;

    RetCode(int code) {
        this.code = code;
    }
}