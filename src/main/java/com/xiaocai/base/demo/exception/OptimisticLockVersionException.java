package com.xiaocai.base.demo.exception;

/**
 * @author Xiaocai.Zhang
 */
public class OptimisticLockVersionException extends BaseException{

    private static final ErrorCode VERSION_IS_NOT_MATCH;

    private static final long serialVersionUID = 1L;

    static {
        VERSION_IS_NOT_MATCH = ErrorCode.OPTIMISTIC_LOCK_VERSION_IS_NOT_MATCH;
    }

    public OptimisticLockVersionException(String message, Throwable cause) {
        super(message, cause);
    }

    public OptimisticLockVersionException(String message) {
        super(message);
    }

    public OptimisticLockVersionException() {
        super(VERSION_IS_NOT_MATCH.getErrorMsg());
    }

    @Override
    public Integer getSelfExceptionCode() {
        return VERSION_IS_NOT_MATCH.getErrorCode();
    }

    @Override
    public String getErrorType() {
        return VERSION_IS_NOT_MATCH.getErrorType();
    }


}
