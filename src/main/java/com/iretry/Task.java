package com.iretry;

/**
 * @author Liuxd
 * @date 2020-06-14
 */
public interface Task {

    void run() throws Exception;

    default void retryFailed(Throwable e){}

    default Object snapshot(){
        return null;
    }
}
