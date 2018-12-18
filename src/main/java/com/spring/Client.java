package com.spring;

/**
 * Created by Liuxd on 2018-11-11.
 */
interface Animal {
    public abstract void speak();
}

class Cat implements Animal {
    public void speak() {
        System.out.println("I am a cat");
    }
}

class Dog implements Animal {
    public void speak() {
        System.out.println("I am a dog");
    }
}

class BeanFactory {
    public static Animal getInstance(String ClassName) {
        Animal f = null;
        try {
            f = (Animal) Class.forName(ClassName).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
}

public class Client {
    public static void main(String[] a) {
        Animal animal = BeanFactory.getInstance("com.spring.Cat");
        if (null != animal) {
            animal.speak();
        }
        Animal animal2 = BeanFactory.getInstance("com.spring.Dog");
        if (null != animal2) {
            animal2.speak();
        }
    }
}
