package com.example.sensorsv2;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.util.Log;

public class SensorObject extends Object{
    public String name;
    public SensorEventListener listener;
    public Sensor sensor;
    public float[] value;
    public float[] max;
    public float[] min;



    public SensorObject(Sensor sensor) {
        this.sensor = sensor;
        this.name = this.sensor.getName();

    }

    public void setValue(float[] value) {
        this.value = value;

    }


    public void setMin(float[] values){
        if(this.min == null){
            this.min = new float[values.length];
            for(int i=0; i < values.length; i++){
                min[i] = values[i];
            }
        }
        else {
            for (int i = 0; i < this.value.length; i++) {
                if (this.value[i] < this.min[i]) {
                    this.min[i] = this.value[i];
                }
            }
        }
    }

    public void setMax(float[] values){
        if(this.max == null){
            this.max = new float[values.length];
            for(int i=0; i < values.length; i++){
                max[i] = values[i];
            }
        }
        else {
            for (int i = 0; i < this.value.length; i++) {
                if (this.value[i] > this.max[i]) {
                    this.max[i] = this.value[i];
                }
            }
        }
    }
}



