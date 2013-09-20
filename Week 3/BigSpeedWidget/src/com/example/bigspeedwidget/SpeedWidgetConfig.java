package com.example.bigspeedwidget;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

public class SpeedWidgetConfig extends Activity implements OnClickListener, LocationListener{

	String[] optionsList;
	String spinnerText;
	TextView speed;
	Button doneButton;
	Spinner spinner;
	Context context;
	AppWidgetManager widgetManager;
	RemoteViews rvs;
	int widgetId;
	LocationManager lm;
	float speedInMils;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_speed_widget_config);
		setResult(RESULT_CANCELED);
		context = this;
		speed = (TextView) findViewById(R.id.speed);
		spinner = (Spinner) findViewById(R.id.spinner);
		//setting up the location manager for location status
		lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2*1000/*Milliseconds*/, 0/*meters*/, this);
		// Creating Array Adapter for spinner view
				Resources res = getResources();
				optionsList = res.getStringArray(R.array.Units);
				
				spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent, View view,
							int position, long id) {
						// Saving the text from the spinner list to a string
						spinnerText = optionsList[position].toString();
						
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {

					}
				});
        //creating the done button
        doneButton = (Button) findViewById(R.id.doneButton);
        //onclick listener for button
        doneButton.setOnClickListener(this);
		
	}

	
	/**
	 * Alert message.
	 * Funciton to build an alert dialog message from anywhere in the class
	 * @param message the message
	 */
	private void alertMessage(String message){
		AlertDialog.Builder alert = new AlertDialog.Builder(context).setMessage(message).setCancelable(true);
		alert.create().show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.speed_widget_config, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		Bundle extras = getIntent().getExtras();
		if(extras != null){
			widgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
			
			
			if (widgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
				
				SpeedUtility su = new SpeedUtility(context);
				su.Save(spinnerText);
				
				widgetManager = AppWidgetManager.getInstance(context);
				rvs = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
				
				if (spinnerText == "Mph") {
					rvs.setTextViewText(R.id.speed, String.valueOf(su.speedInMph(speedInMils)));
				}else if (spinnerText == "Kph") {
					rvs.setTextViewText(R.id.speed, String.valueOf(su.speedInKph(speedInMils)));
				}
				
			}
		}
		
		
		Log.i("spinner Text", spinnerText);
		widgetManager.updateAppWidget(widgetId, rvs);
		
		Intent resultValue = new Intent();
		resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
		setResult(RESULT_OK, resultValue);
		finish();
	}


	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		speedInMils = location.getSpeed();
	}


	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}
