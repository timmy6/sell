package com.qiming.sell.base;

import com.qiming.sell.common.ret.RetCode;
import com.qiming.sell.common.ret.RetResult;

/**
 * @author liuqiming
 * @date 2018/8/19
 * @email liuqiminggood@gmail.com
 * @Description
 */
public class BaseController<T> {
    protected final static String SUCCESS = "success";
    protected final static String FAIL = "fail";

    protected <T> RetResult<T> makeOKRsp() {
        return new RetResult<T>().setCode(RetCode.SUCCESS).setMsg(SUCCESS);
    }

    public <T> RetResult<T> makeOKRsp(T data) {
        return new RetResult<T>().setCode(RetCode.SUCCESS).setMsg(SUCCESS).setData(data);
    }

    protected <T> RetResult<T> makeErrRsp() {
        return new RetResult<T>().setCode(RetCode.FAIL).setMsg(FAIL);
    }

    public <T> RetResult<T> makeErrRsp(String message) {
        return new RetResult<T>().setCode(RetCode.FAIL).setMsg(SUCCESS);
    }

    public <T> RetResult<T> makeRsp(int code, String msg) {
        return new RetResult<T>().setCode(code).setMsg(msg);
    }

    public <T> RetResult<T> makeRsp(int code, String msg, T data) {
        return new RetResult<T>().setCode(code).setMsg(msg).setData(data);
    }
}