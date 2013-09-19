package com.example.bigspeedwidget;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.appwidget.AppWidgetManager;
import android.content.res.Resources;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class SpeedWidgetConfig extends Activity implements OnClickListener{

	String[] optionsList;
	String spinnerText;
	Button doneButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_speed_widget_config);
		
		// Creating Array Adapter for spinner view
        Resources res = getResources();
        optionsList = res.getStringArray(R.array.Units);
        //creating spinner
        Spinner typeSpinner = (Spinner) findViewById(R.id.spinner);
        //on spinner item selected
    	typeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

    		@Override
    		public void onItemSelected(AdapterView<?> parent, View view,
    		int position, long id) {
    		// Saving the text from the spinner list to a string
    		spinnerText = optionsList[position].toString();
    		//parsing the spinner text to an integer
    		
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
		AlertDialog.Builder alert = new AlertDialog.Builder(this).setMessage(message).setCancelable(true);
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
			int widgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
			
			if (widgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
				
				SpeedUtility su = new SpeedUtility(this);
				su.Save(spinnerText);
			}
		}
	}

}
