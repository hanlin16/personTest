package com.event;

/**
 * 事件类,用于封装事件源及一些与事件相关的参数.
 * @author liuxd
 * @version 1.0
 * @date 2019-11-01 17:50
 */
import java.util.EventObject;

public class CusEvent extends EventObject {

    private static final long serialVersionUID = 1L;

    //事件源
    private Object source;

    public CusEvent(Object source){
        super(source);
        this.source = source;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }
}
