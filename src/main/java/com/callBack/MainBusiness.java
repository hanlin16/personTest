package com.callBack;

/**
 * @author liuxd
 * @version 1.0
 * @date 2020-04-02 8:48
 */
public class MainBusiness {

    private CallbackService callback;

    public void execute(CallbackService callback) {
        this.callback = callback;
        callBack();
    }

    public void callBack() {
        callback.callBackFunc();
    }
}