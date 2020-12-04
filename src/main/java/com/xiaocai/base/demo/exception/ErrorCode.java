package com.xiaocai.base.demo.exception;

/**
 * @author Xiaocai.Zhang
 */

public enum ErrorCode implements IErrorCode {

    SYSTEM_SERVICE_ERROR_CODE("100", "SYSTEM_ERROR", "System Error"),
    ARGUMENT_NOT_VALID_CODE("200", "ARGUMENT_NOT_VALID", "Argument Not Valid"),
    PARAM_MISS("201", "PARAM_MISS", "Param Miss"),
    PARAM_TYPE_ERROR("202", "PARAM_TYPE_ERROR", "Param Type Error"),
    PARAM_BIND_ERROR("203", "PARAM_BIND_ERROR", "Param Bind Error"),
    PARAMETER_VALIDATE_NOT_PASS("204", "PARAMETER_VALIDATE_NOT_PASS", "Param Validate Not Pass"),
    NOT_FOUND("404", "NOT_FOUND", "404 Not Found"),
    MSG_NOT_READABLE("405", "MSG_NOT_READABLE", "Msg Not Readable"),
    METHOD_NOT_SUPPORTED("406", "METHOD_NOT_SUPPORTED", "Method Not Supported"),
    MEDIA_TYPE_NOT_SUPPORTED("407", "MEDIA_TYPE_NOT_SUPPORTED", "Media Type Not Supported"),
    MEDIA_TYPE_NOT_ACCEPT("408", "MEDIA_TYPE_NOT_ACCEPT", "Media Type Not Accept"),
    REQUEST_REJECT("409", "REQUEST_REJECT", "Request Reject"),
    DATA_NOT_EXIST("501", "DATA_NOT_EXIST", "Data Not Exist"),
    DATA_EXISTED("502", "DATA_EXISTED", "Data Existed"),
    DATA_ADD_FAILED("503", "DATA_ADD_FAILED", "Data Add Failed"),
    DATA_UPDATE_FAILED("504", "DATA_UPDATE_FAILED", "Data Update Failed"),
    DATA_DELETE_FAILED("505", "DATA_DELETE_FAILED", "Data Delete Failed"),
    CONNECT_TIME_OUT("506", "CONNECT_TIME_OUT", "Connect Time Out"),
    CANNOT_REPEAT_SUBMIT("808", "CANNOT_REPEAT_SUBMIT", "Method Cannot Repeat Submit"),
    OPTIMISTIC_LOCK_VERSION_IS_NOT_MATCH("694", "OPTIMISTIC_LOCK_VERSION_IS_NOT_MATCH", "Optimistic Lock Version Is Not Match"),
    REDIS_EXECUTE_OR_CONNECTION_ERROR("695", "REDIS_EXECUTE_OR_CONNECTION_ERROR", "Redis Execute Or Connection Error");

    private String errorCode;
    private String errorType;
    private String errorMsg;

    private ErrorCode(String errorCode, String errorType, String errorMsg) {
        this.errorCode = errorCode;
        this.errorType = errorType;
        this.errorMsg = errorMsg;
    }



    @Override
    public Integer getErrorCode()
    {
        return Integer.parseInt(this.errorCode);
    }

    public String getErrCode()
    {
        return this.errorCode;
    }

    @Override
    public String getErrorType()
    {
        return this.errorType;
    }

    @Override
    public String getErrorMsg()
    {
        return this.errorMsg;
    }

    public void setErrorMsg(String errorMsg)
    {
        this.errorMsg = errorMsg;
    }
}
