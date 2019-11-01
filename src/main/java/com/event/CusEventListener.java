package com.event;

/**
 * 事件监听器，实现java.util.EventListener接口。定义回调方法，将你想要做的事
 * 放到这个方法下,因为事件源发生相应的事件时会调用这个方法。
 * @author liuxd
 * @version 1.0
 * @date 2019-11-01 17:52
 */
import java.util.EventListener;


public class CusEventListener implements EventListener {

    //事件发生后的回调方法
    public void fireCusEvent(CusEvent e){
        EventSourceObject eObject = (EventSourceObject)e.getSource();
        System.out.println("My name has been changed!");
        System.out.println("I got a new name,named \""+eObject.getName()+"\"");
    }
}

