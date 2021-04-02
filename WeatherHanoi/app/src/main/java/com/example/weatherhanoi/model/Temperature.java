package com.example.weatherhanoi.model;

public class Temperature {
    private float Value;
    private   String Unit;


    public float getValue() {
        return Value;
    }

    public void setValue(float value) {
        Value = value;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }
}
