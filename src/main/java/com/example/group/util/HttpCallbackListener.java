package com.example.group.util;

public interface HttpCallbackListener {
    void OnFinish(String response);
    void onError(Exception e);
}
