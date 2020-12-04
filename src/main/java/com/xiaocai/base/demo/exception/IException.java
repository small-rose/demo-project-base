package com.xiaocai.base.demo.exception;

/**
 * @author Xiaocai.Zhang
 */
public interface IException {
    /**
     * ERROR CODE
     * @return
     */
    Integer getErrorCode();
    /**
     * ERROR TYPE
     * @return
     */
    String getErrorType();
}
