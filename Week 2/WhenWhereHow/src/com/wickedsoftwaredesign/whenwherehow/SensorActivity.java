/*
 * project     WhenWhereHow
 * 
 * package		com.wickedsoftwaredesign.whenwherehow
 * 
 * @author     Michael R. Smith Jr
 * 
 * date			Sep 11, 2013
 */
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

// TODO: Auto-generated Javadoc
/**
 * The Class SensorActivity.
 */
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
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Requesting no title bar for app view
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		//setting app orientation to landscape
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		setContentView(R.layout.activity_sensor);
		//building the text views for the data
		dist = (TextView) findViewById(R.id.proximityDist);
		accelX = (TextView) findViewById(R.id.accelXval);
		accelY = (TextView) findViewById(R.id.accelYval);
		accelZ = (TextView) findViewById(R.id.accelZval);
		tempC = (TextView) findViewById(R.id.tempVal);
		light = (TextView) findViewById(R.id.lightVal);
		//starting the sensor manager service
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		//connecting to the sensors
		proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
		lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
		//checking to see if the sensors are present
		if(proximitySensor == null){
			//if not text is set to sensor not found
			dist.setText("Sensor Not Found");
		}else{
			//if it is then the listener is attached to the sensor
			sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
		}
		
		if(accelerometerSensor == null){
			//if not text is set to sensor not found
			accelX.setText("Sensor Not Found");
			accelY.setText("Sensor Not Found");
			accelZ.setText("Sensor Not Found");
		}else{
			//if it is then the listener is attached to the sensor
			sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
		}
		
		if(tempSensor == null){
			//if not text is set to sensor not found
			tempC.setText("Sensor Not Found");
		}else{
			//if it is then the listener is attached to the sensor
			sensorManager.registerListener(this, tempSensor, SensorManager.SENSOR_DELAY_NORMAL);
		}
		
		if(lightSensor == null){
			//if not text is set to sensor not found
			light.setText("Sensor Not Found");
		}else{
			//if it is then the listener is attached to the sensor
			sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
		}
		
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sensor, menu);
		return true;
	}

	/* (non-Javadoc)
	 * @see android.hardware.SensorEventListener#onAccuracyChanged(android.hardware.Sensor, int)
	 */
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see android.hardware.SensorEventListener#onSensorChanged(android.hardware.SensorEvent)
	 * when the listener sees a change
	 */
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		//checking which sensor is changing and setting the appropriate text
		if(event.sensor == proximitySensor){
			//setting value for the distance on the proximity sensor
			dist.setText(String.valueOf(event.values[0]));
		}else if(event.sensor == accelerometerSensor){
			//setting the X, Y, Z values for the accelerometer
			accelX.setText(String.valueOf(event.values[0]));
			accelY.setText(String.valueOf(event.values[1]));
			accelZ.setText(String.valueOf(event.values[2]));
		}else if(event.sensor == tempSensor){
			//setting the temp reading from the thermometer
			tempC.setText(String.valueOf(event.values[0]));
		}else if(event.sensor == lightSensor){
			//setting value from light meter
			light.setText(String.valueOf(event.values[0]));
		}
		
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	protected void onResume(){
		super.onResume();
		//restarts the sensor listener setting the delay to fastest
		sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_FASTEST);
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	protected void onPause(){
		super.onPause();
		//unregistering the listener on all sensors
		sensorManager.unregisterListener(this);
	}

}
