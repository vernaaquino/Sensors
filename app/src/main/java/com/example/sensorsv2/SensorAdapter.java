package com.example.sensorsv2;

import android.content.Context;
import android.hardware.Sensor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class SensorAdapter extends ArrayAdapter<com.example.sensorsv2.SensorObject> {

    public SensorAdapter(Context context, List<com.example.sensorsv2.SensorObject> sensors) {
        super(context, 0, sensors);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        com.example.sensorsv2.SensorObject sense = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_sensor, parent, false);
        }
        // Lookup view for data population
        TextView name = (TextView) convertView.findViewById(R.id.sensorName);
        TextView currentValue = (TextView) convertView.findViewById(R.id.currentValue);
        TextView maxValue = (TextView) convertView.findViewById(R.id.maxValue);
        TextView minValue = (TextView) convertView.findViewById(R.id.minValue);


        // Display sensor name with units
        if(sense.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            name.setText("Sensor: " + sense.name + "  Unit: m/s^2");
        }
        else if(sense.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE){
            name.setText("Sensor: " + sense.name + "  Unit: °C");
        }
        else if(sense.sensor.getType() == Sensor.TYPE_GRAVITY){
            name.setText("Sensor: " + sense.name + "  Unit: m/s^2");
        }
        else if(sense.sensor.getType() == Sensor.TYPE_GYROSCOPE){
            name.setText("Sensor: " + sense.name + "  Unit: rad/s");
        }
        else if(sense.sensor.getType() == Sensor.TYPE_LIGHT){
            name.setText("Sensor: " + sense.name + "  Unit: lx");
        }
        else if(sense.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION){
            name.setText("Sensor: " + sense.name + "  Unit: m/s^2");
        }
        else if(sense.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            name.setText("Sensor: " + sense.name + "  Unit: μT");
        }
        else if(sense.sensor.getType() == Sensor.TYPE_PRESSURE){
            name.setText("Sensor: " + sense.name + "  Unit: hPa or mbar");
        }
        else if(sense.sensor.getType() == Sensor.TYPE_PROXIMITY){
            name.setText("Sensor: " + sense.name + "  Unit: cm");
        }
        else if(sense.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY){
            name.setText("Sensor: " + sense.name + "  Unit: %");
        }
        else if(sense.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR){
            name.setText("Sensor: " + sense.name + "  Unit: N/A");
        }
        else{
            name.setText("Sensor: " + sense.name);
        }


        // Display current value
        int i = 0;
        currentValue.setText("");
        if(sense.value != null){
            for(float val: sense.value){
                currentValue.append("\n Value " + i + ":" + String.format("%.2f", val) + ",");
                i++;
            }
        }

        //Display max
        i = 0;
        maxValue.setText("");
        if(sense.max != null){
            for(float val: sense.max){
                maxValue.append("\n Max " + i + ":" + String.format("%.2f", val) + ",");
                i++;
            }
        }

        //Display min
        i = 0;
        minValue.setText("");
        if(sense.min != null){
            for(float val: sense.min){
                minValue.append("\n Min " + i + ":" + String.format("%.2f", val) + ",");
                i++;
            }
        }

        // Return the completed view to render on screen
        return convertView;
    }
}