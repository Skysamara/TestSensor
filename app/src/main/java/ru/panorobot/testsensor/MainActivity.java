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
//    private Sensor mSensor;

    private float[] rotationMatrix;     //Матрица поворота
    private float[] accelData;          //Данные с акселерометра
    private float[] magnetData;         //Данные геомагнитного датчика
    private float[] OrientationData;    //Матрица положения в пространстве


    private TextView txtAccuracy;
    private TextView txtValue;
    private TextView txtInfo;

    private int mI;

    private VRPano vrPano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        rotationMatrix = new float[16];
        accelData = new float[3];
        magnetData = new float[3];
        OrientationData = new float[3];

        txtAccuracy = (TextView) findViewById(R.id.txtAccuracy);
        txtValue = (TextView) findViewById(R.id.txtValue);
        txtInfo = (TextView) findViewById(R.id.textInfo);

        vrPano = new VRPano(9,5);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        txtAccuracy.setText("1");

        mI = 0;

    }

    @Override
    protected void onResume() {
        super.onResume();
//        mSensorManager.registerListener(this, mSensor,mSensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI );
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_UI );


        txtInfo.setText(vrPano.toString());
    }

    private void loadNewSensorData(SensorEvent event) {
        final int type = event.sensor.getType(); //Определяем тип датчика
        if (type == Sensor.TYPE_ACCELEROMETER) { //Если акселерометр
//            accelData = event.values.clone();
            System.arraycopy(event.values, 0, accelData, 0, event.values.length);
        }

        if (type == Sensor.TYPE_MAGNETIC_FIELD) { //Если геомагнитный датчик
//            magnetData = event.values.clone();
            System.arraycopy(event.values, 0, magnetData, 0, event.values.length);
        }
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        loadNewSensorData(event); // Получаем данные с датчика
        SensorManager.getRotationMatrix(rotationMatrix, null, accelData, magnetData); //Получаем матрицу поворота
        SensorManager.getOrientation(rotationMatrix, OrientationData); //Получаем данные ориентации устройства в пространстве


        //Выводим результат
//        xyView.setText(String.valueOf(Math.round(Math.toDegrees(OrientationData[0]))));
//        xzView.setText(String.valueOf(Math.round(Math.toDegrees(OrientationData[1]))));
//        zyView.setText(String.valueOf(Math.round(Math.toDegrees(OrientationData[2]))));

            float X = event.values[0];
            txtValue.setText(String.valueOf(Math.round(Math.toDegrees(OrientationData[2]))));
            mI++;
            txtAccuracy.setText(String.valueOf(mI));

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
