package com.iretry;

public interface Task {

    void run() throws Exception;

    default void retryFailed(Throwable e) {
    }

    default Object snapshot() {
        return null;
    }
}
