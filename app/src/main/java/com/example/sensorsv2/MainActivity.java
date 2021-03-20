package com.example.sensorsv2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.sensorsv2.SensorObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
    SensorEventListener listener;
    SensorManager mngr;
    List<Sensor> sensorList;
    List<com.example.sensorsv2.SensorObject> sensorObjectList;
    SensorAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mngr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorList = mngr.getSensorList(Sensor.TYPE_ALL);
        sensorObjectList = new ArrayList<>();


        for(Sensor sensor : sensorList){
            final SensorObject sense = new SensorObject(sensor);
            sensorObjectList.add(sense);

            sense.listener = new SensorEventListener() {
                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {
                    return;
                }

                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                    sense.setValue(sensorEvent.values);
                    sense.setMax(sensorEvent.values);
                    sense.setMin(sensorEvent.values);
                    adapter.notifyDataSetChanged();
                    return;
                }
            };

            mngr.registerListener(sense.listener, sense.sensor, SensorManager.SENSOR_DELAY_UI);
        }

        adapter = new SensorAdapter(this, sensorObjectList);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        for(SensorObject sense: sensorObjectList){
            mngr.unregisterListener(sense.listener);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        for(SensorObject sense: sensorObjectList){
            mngr.registerListener(sense.listener, sense.sensor, SensorManager.SENSOR_DELAY_FASTEST);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        for(SensorObject sense: sensorObjectList){
            mngr.unregisterListener(sense.listener);
        }
    }

}
