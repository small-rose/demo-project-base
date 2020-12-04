package com.xiaocai.base.demo.exception;

/**
 * @author Xiaocai.Zhang
 */
public class ValidateException extends BaseException {

    private static final ErrorCode ERROR_CODE;
    private static final long serialVersionUID = 1L;

    static {
        ERROR_CODE = ErrorCode.PARAMETER_VALIDATE_NOT_PASS;
    }

    public ValidateException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public Integer getSelfExceptionCode() {
        return ERROR_CODE.getErrorCode();
    }

}
