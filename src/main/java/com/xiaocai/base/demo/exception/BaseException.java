package com.xiaocai.base.demo.exception;

import com.xiaocai.base.demo.tools.StringFormat;

/**
 * @author Xiaocai.Zhang
 */
public abstract class BaseException extends RuntimeException implements IException{

    public boolean debugDetailStackTrace = false;

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    /**
     * get self exception
     * @return
     */
    public abstract Integer getSelfExceptionCode();

    @Override
    public Integer getErrorCode() {
        return Integer.parseInt(ErrorCode.SYSTEM_SERVICE_ERROR_CODE.getErrorCode() + StringFormat.format0Right((long)this.getSelfExceptionCode(), 4));
    }

    @Override
    public String getErrorType() {
        return ErrorCode.SYSTEM_SERVICE_ERROR_CODE.getErrorType();
    }
}
