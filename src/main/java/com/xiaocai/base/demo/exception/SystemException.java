package com.xiaocai.base.demo.exception;

/**
 * @author Xiaocai.Zhang
 */
public class SystemException extends BaseException {
    private static final ErrorCode ERROR_CODE;
    private static final long serialVersionUID = 1L;

    static {
        ERROR_CODE = ErrorCode.SYSTEM_SERVICE_ERROR_CODE;
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public Integer getSelfExceptionCode() {
        return ERROR_CODE.getErrorCode();
    }


}
