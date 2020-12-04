package com.xiaocai.base.demo.exception;

/**
 * @author Xiaocai.Zhang
 */
public class BizCommonException extends BaseException{

    private static final long serialVersionUID = -5850025800322330362L;
    private final IErrorCode errorCode;
    private Object[] msgArgs;

    public BizCommonException(IErrorCode err) {

        this(err, (Object[])null, (Throwable)null);
    }

    public BizCommonException(IErrorCode err, Object[] msgArgs) {
        this(err, msgArgs, (Throwable)null);
        this.msgArgs = msgArgs;
    }

    public BizCommonException(IErrorCode err, Object[] msgArgs, Throwable cause) {
        super(cause);
        this.errorCode = err;
        this.msgArgs = msgArgs;
    }
    @Override
    public Integer getSelfExceptionCode() {
        return this.errorCode.getErrorCode();
    }

    public IErrorCode getErrorCodeEnum() {
        return this.errorCode;
    }

    public Object[] getMsgArgs() {
        return this.msgArgs;
    }

}
