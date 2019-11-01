package com.classLoad;

/**
 * @author liuxd
 * @version 1.0
 * @date 2019-10-31 11:30
 */
public class LoadTest {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> user =  LoadTest.class.getClassLoader().loadClass("com.classLoad.User");
        
    }
}
