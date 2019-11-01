package com.event;

/**
 * @author liuxd
 * @version 1.0
 * @date 2019-11-01 17:54
 */
public class MainTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        EventSourceObject object = new EventSourceObject();
        //注册监听器
        object.addCusListener(new CusEventListener(){
            @Override
            public void fireCusEvent(CusEvent e) {
                super.fireCusEvent(e);
            }
        });
        //触发事件
        object.setName("eric");
    }
}
