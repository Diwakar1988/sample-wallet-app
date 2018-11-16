package com.github.diwakar1988.noon.net;

/**
 * Created by 'Diwakar Mishra' on 16,November,2018
 */
public class ApiServiceException extends Exception {
    private int httpResponseCode;

    public ApiServiceException(int httpCode, Throwable cause) {
        super(cause);
        this.httpResponseCode = httpCode;
    }

    public ApiServiceException(int httpResponseCode,String message) {
        super(message);
        this.httpResponseCode = httpResponseCode;
    }

    public int getResponseCode() {
        return httpResponseCode;
    }
}
