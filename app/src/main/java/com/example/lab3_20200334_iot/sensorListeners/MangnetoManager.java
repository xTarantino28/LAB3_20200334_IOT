package com.example.lab3_20200334_iot.sensorListeners;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class MangnetoManager implements SensorEventListener {
    private static final float ANGULO_MINIMO_NORTE = 5.0f;  // Ángulo mínimo para considerar que apunta al norte
    private static final float ANGULO_MAXIMO_SUR = 90.0f;    // Ángulo máximo para considerar que apunta al sur
    private static final float ANGULO_MAXIMO = 180.0f;  // Ángulo máximo para considerar que apunta al norte al mínimo

    private Context context;
    private SensorManager sensorManager;
    private float[] magneticFieldValues = new float[3];
    private RecyclerView recyclerView;
    //private OnAnguloChangeListener anguloChangeListener;

   /* public interface OnAnguloChangeListener {
        void onAnguloChanged(float angulo);
    }*/

    public MangnetoManager(Context context, RecyclerView recyclerView) {
        this.context = context;
        //this.anguloChangeListener = listener;
        this.recyclerView = recyclerView;
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
    }

    public void iniciar() {
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (sensor != null) {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Toast.makeText(context, "El dispositivo no tiene sensor de magnetómetro", Toast.LENGTH_SHORT).show();
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
        /*float angulo = calcularAngulo(event.values[0], event.values[1]);
        float porcentajeVisibilidad = calcularPorcentajeVisibilidad(angulo);
        recyclerView.setAlpha((int)(porcentajeVisibilidad/100));
        /*if (anguloChangeListener != null) {
            anguloChangeListener.onAnguloChanged(angulo);
            float porcentajeVisibilidad = calcularPorcentajeVisibilidad(angulo);
        }*/
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            // Obtener las componentes del campo magnético
            magneticFieldValues = event.values;

            // Calcular el ángulo en relación con el norte
            float angleWithNorth = calcularAnguloConNorte(magneticFieldValues[0], magneticFieldValues[1]);

            // Ajustar la visibilidad de la lista de contactos
            ajustarVisibilidadListaContactos(angleWithNorth);
        }

    }


    /*private float calcularPorcentajeVisibilidad(float angulo) {
        // Calcular el porcentaje de visibilidad basado en el ángulo
        if (angulo > 0.9*ANGULO_MAXIMO) {
            return 0.0f;
        } else if (angulo < 0.05*ANGULO_MAXIMO) {
            return 100.0f;
        } else if (angulo < 0.25*ANGULO_MAXIMO && angulo > 0.05*ANGULO_MAXIMO) {
            return 80.0f;
        } else if (angulo < 0.5*ANGULO_MAXIMO && angulo > 0.25*ANGULO_MAXIMO) {
            return 60.0f;
        }else if (angulo < 0.75*ANGULO_MAXIMO && angulo > 0.50*ANGULO_MAXIMO) {
            return 40.0f;
        }else  {
            return 20.0f;
        }

    }*/

    private void ajustarVisibilidadListaContactos(float angleWithNorth) {
        float visibilityPercentage;

        if (angleWithNorth < 0.15*180) {
            visibilityPercentage = 100.0f;
        } else if (angleWithNorth < 0.25*180 && angleWithNorth > 0.15*180 ) {
            visibilityPercentage = 80.0f;
        } else if (angleWithNorth < 0.50*180 && angleWithNorth > 0.25*180) {
            visibilityPercentage = 60.0f;
        } else if (angleWithNorth < 0.75*180 && angleWithNorth > 0.50*180) {
            visibilityPercentage = 40.0f;
        } else if (angleWithNorth < 0.90*180 && angleWithNorth > 0.75*180) {
            visibilityPercentage = 20.0f;
        } else {
            visibilityPercentage = 0.0f;
        }

        recyclerView.setAlpha(visibilityPercentage/100);
    }


    private float calcularAnguloConNorte(float x, float y) {
        float angleInDegrees = (float) Math.toDegrees(Math.atan2(y, x));
        float normalizedAngle = (angleInDegrees + 360) % 360;
        float normalizedAngleabs = Math.abs(Math.abs(normalizedAngle)-90);
        String datanormalizeabs = Float.toString(normalizedAngleabs);

        //Log.d("msg-test",datanormalizeabs);
        float angleFromNorth = 0.0f;
        if (normalizedAngleabs>180) {
            //Log.d("msg-test","dGAGAAGAGAGAGAGAGAGAG");
            angleFromNorth = Math.abs(360-normalizedAngleabs);
        } else {
            angleFromNorth = normalizedAngleabs;
        }
       // float angleFromNorth = normalizedAngleabs > 180?normalizedAngleabs-180:normalizedAngleabs; //180 derecha decente
        String data = Float.toString(angleFromNorth);
        //float anglepositive = Math.abs(angleFromNorth);
        //float angleDefinive = Math.abs(anglepositive-90);
        //String data = Float.toString(angleDefinive);
        Log.d("msg-test",data);

        //String data2 = Float.toString(angleFromNorth);
        //Log.d("msg-test",data2);
        //String data1 = Float.toString(anglepositive);
        //Log.d("msg-test",data1);
        return angleFromNorth;
    }


}
