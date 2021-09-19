package com.example.notebook.domain;

public interface Callback<T> {

    void onSuccess(T data);
}
