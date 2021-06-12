package com.observer;

public class TestSubjectObserver {

    public static void main(String[] args) {
        // 1.创建目标
        SubjectWeatherConcrete subjectWeather = new SubjectWeatherConcrete();

        // 2.创建观察者
        ObserverGirl observerGirl = new ObserverGirl("朋友：", "明天约会，地点大运河公园，不见不散哦");
        ObserverMom observerMum = new ObserverMom("老妈：", "明天购物，地点大润发超市");

        // 3.注册观察者
        subjectWeather.attach(observerGirl);
        subjectWeather.attach(observerMum);

        // 4.目标发布天气
        subjectWeather.setWeatherContent("明天，天气晴朗，蓝天白云，气温28℃");
    }

}
