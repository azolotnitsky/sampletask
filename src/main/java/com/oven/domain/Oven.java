package com.oven.domain;

import javax.persistence.*;

/**
 * Created by Alexey on 26.01.2018.
 */
@Entity
@Table(name = "ovens")

public class Oven {

    public enum Mode { SWITCHEDOFF, CONVECTION, UP_HEATING, BOTTOM_HEATING, UPANDBOTTOM_HEATING, GRILL, LARGE_GRILL, TURBO_GRILL, FAST_HEATING, PIZZA, FAN, TIMER };

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void changeTemperature(double delta) {
        this.temperature += delta;
    }

    public int getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(int deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "devicenumber")
    private int deviceNumber;

    @Column(name = "mode")
    private Mode mode;

    @Column(name = "temperature")
    private double temperature;

}
