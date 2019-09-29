package com.testAop.aop2;


import javax.annotation.Resource;
import javax.sound.midi.Instrument;

public class Counter {
    public Counter() {
    }

    public Counter(double multiplier, String song, Instrument instrument) {
        this.multiplier = multiplier;
        this.song = song;
        this.instrument = instrument;
    }

    private double multiplier;

    private String song;

    @Resource
    private Instrument instrument;

    public double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }


    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

}