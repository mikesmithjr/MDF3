package com.wickedsoftwaredesign.whenwherehow;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.Window;
import android.widget.TextView;

public class SensorActivity extends Activity implements SensorEventListener{

	private SensorManager sensorManager;
	
	private Sensor proximitySensor;
	private Sensor accelerometerSensor;
	private Sensor tempSensor;
	private Sensor lightSensor;
	
	private TextView dist;
	private TextView accelX;
	private TextView accelY;
	private TextView accelZ;
	private TextView tempC;
	private TextView light;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Requesting no title bar for app view
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		//setting app orientation to landscape
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		setContentView(R.layout.activity_sensor);
		
		dist = (TextView) findViewById(R.id.proximityDist);
		accelX = (TextView) findViewById(R.id.accelXval);
		accelY = (TextView) findViewById(R.id.accelYval);
		accelZ = (TextView) findViewById(R.id.accelZval);
		tempC = (TextView) findViewById(R.id.tempVal);
		light = (TextView) findViewById(R.id.lightVal);
		
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
		lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
		
		if(proximitySensor == null){
			dist.setText("Sensor Not Found");
		}else{
			sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
		}
		
		if(accelerometerSensor == null){
			accelX.setText("Sensor Not Found");
			accelY.setText("Sensor Not Found");
			accelZ.setText("Sensor Not Found");
		}else{
			sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
		}
		
		if(tempSensor == null){
			tempC.setText("Sensor Not Found");
		}else{
			sensorManager.registerListener(this, tempSensor, SensorManager.SENSOR_DELAY_NORMAL);
		}
		
		if(lightSensor == null){
			light.setText("Sensor Not Found");
		}else{
			sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sensor, menu);
		return true;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		
		if(event.sensor == proximitySensor){
			dist.setText(String.valueOf(event.values[0]));
		}else if(event.sensor == accelerometerSensor){
			accelX.setText(String.valueOf(event.values[0]));
			accelY.setText(String.valueOf(event.values[1]));
			accelZ.setText(String.valueOf(event.values[2]));
		}else if(event.sensor == tempSensor){
			tempC.setText(String.valueOf(event.values[0]));
		}else if(event.sensor == lightSensor){
			light.setText(String.valueOf(event.values[0]));
		}
		
	}
	
	protected void onResuem(){
		super.onResume();
		sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_FASTEST);
	}
	
	protected void onPause(){
		super.onPause();
		sensorManager.unregisterListener(this);
	}

}
