package com.example.lab3_20200334_iot.sensorListeners;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class AccManager implements SensorEventListener {

    private static final float UMBRAL_ACELERACION = 15.0f; // Umbral de aceleración

    private Context context;
    private SensorManager sensorManager;

    private RecyclerView recyclerView;

    public AccManager(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
    }

    public void iniciar() {
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (sensor != null) {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Toast.makeText(context, "El dispositivo no tiene sensor de acelerómetro", Toast.LENGTH_SHORT).show();
        }
    }

    public void detener() {
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] valoresAceleracion = event.values;
        float aceleracion = (float) Math.sqrt(valoresAceleracion[0] * valoresAceleracion[0]
                + valoresAceleracion[1] * valoresAceleracion[1] + valoresAceleracion[2] * valoresAceleracion[2]);

        if (aceleracion > UMBRAL_ACELERACION) {
            // Realizar scroll automático en el RecyclerView
            recyclerView.smoothScrollBy(0, 200);

            // Mostrar la aceleración en un Toast
            Toast.makeText(context, "Su aceleración: " + aceleracion + " m/s^2", Toast.LENGTH_SHORT).show();
        }
    }
}
