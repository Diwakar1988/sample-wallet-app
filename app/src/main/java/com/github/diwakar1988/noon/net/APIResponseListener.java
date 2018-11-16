package com.github.diwakar1988.noon.net;

public interface APIResponseListener<T>{
    void onFail(ApiServiceException e);
    void onSuccess(T response);
}
