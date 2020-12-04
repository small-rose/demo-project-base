package com.xiaocai.base.demo.exception;

/**
 * @author Xiaocai.Zhang
 */
public interface IErrorCode extends IException{
    /**
     *  获取错误信息
     * @return
     */
    String getErrorMsg();

    default void throwException() {
        throw new BizCommonException(this, (Object[])null);
    }

    default void throwException(Object[] msgArgs) {
        throw new BizCommonException(this, msgArgs);
    }
}
