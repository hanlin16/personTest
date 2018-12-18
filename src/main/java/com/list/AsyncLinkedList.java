package com.list;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Liuxd on 2018-11-11.
 */
public class AsyncLinkedList {

    private List<String> list = new LinkedList<>();

    private synchronized void add(String value) {
        list.add(value);
    }

    private synchronized void remove(String value) {
        list.remove(value);
    }


    public void async(AsyncLinkedList asyncLinkedList) {
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            new Thread() {
                @Override
                public void run() {
                    asyncLinkedList.add(String.valueOf(finalI));
                }
            }.start();
        }
    }

    public void printList() {
        int length = list.size();
        int max = 0;
        for (int j = 0; j < length; j++) {
            System.out.print("[" + j + "]:" + list.get(j) + " ");
            int value = Integer.parseInt(list.get(j));
            if (value > max) {
                max = value;
            }
        }
        System.out.println();
        System.out.println("最大值：" + max);
    }

    public static void main(String[] args) throws Exception {

        AsyncLinkedList asyncLinkedList2 = new AsyncLinkedList();
        asyncLinkedList2.async(asyncLinkedList2);
        Thread.sleep(1000);
        asyncLinkedList2.printList();


    }


}
