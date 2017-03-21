package ru.panorobot.testsensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private SensorManager mSensorManager;
    private Sensor mSensor;

    private TextView txtAccuracy;
    private TextView txtValue;

    private int mI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        txtAccuracy = (TextView) findViewById(R.id.txtAccuracy);
        txtValue = (TextView) findViewById(R.id.txtValue);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        txtAccuracy.setText("1");

        mI = 0;

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor,mSensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        synchronized (this) {
            float X = event.values[0];
            txtValue.setText(String.valueOf(X));
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
