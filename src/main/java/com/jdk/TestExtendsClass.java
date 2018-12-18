package com.jdk;

/**
 * Created by Liuxd on 2018-11-02.
 */
public class TestExtendsClass {
    public static void main(String[] args) {
        ParentClass parentClass = new ChildClass();
        System.out.println();

        int pFResult = ParentClass.plus(3,2);
        int pResult = ParentClass.add(3,2);
        int cResult = ChildClass.add(3,2);

        int pMResult = parentClass.minus(2,3);

        System.out.println("父类静final方法计算结果：" + pFResult);
        System.out.println("父类静方法计算结果：" + pResult);
        System.out.println("子类静方法计算结果：" + cResult);
        System.out.println("父类final方法计算结果：" + pMResult);

        System.out.println("父类静态变量num：" + ParentClass.num);
        System.out.println("子类静态变量num：" + ChildClass.num);

    }
}

class ParentClass {
    public static final int num = 1;
    ParentClass() {
        System.out.println("执行父类无参数构造方法");
    }

    static int add(int i, int j) {
        return i + j;
    }

    static final int plus(int i, int j) {
        return i + j;
    }

    final int minus(int i, int j) {
        return i - j;
    }

}

class ChildClass extends ParentClass {
    public static  final int num = 2;
    ChildClass() {
        System.out.println("执行子类无参数构造方法");
    }

//    @Override 无法继承，编译时出错
    static int add(int i, int j) {
        return i * j;
    }

//    无法继承，编译时出错
//    int plus(int i, int j) {
//        return i + j;
//    }

//    无法继承，编译时出错
//    int minus(int i, int j) {
//        return i - j;
//    }

}
