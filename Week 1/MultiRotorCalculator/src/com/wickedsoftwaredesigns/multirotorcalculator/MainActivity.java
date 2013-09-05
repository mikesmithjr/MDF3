/*
 * project     MultiRotorCalculator
 * 
 * package		com.wickedsoftwaredesigns.multirotorcalculator
 * 
 * @author     Michael R. Smith Jr
 * 
 * date			Sep 5, 2013
 */
package com.wickedsoftwaredesigns.multirotorcalculator;

import java.text.DecimalFormat;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

// TODO: Auto-generated Javadoc
/**
 * The Class MainActivity.
 */
public class MainActivity extends Activity {

	
	String[] optionsList;
	String spinnerText;
	String rotorCountImageURI;
	int rotors;
    
    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //setting app orientation to landscape
      		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        
    	// Creating Array Adapter for spinner view
        Resources res = getResources();
        optionsList = res.getStringArray(R.array.rotor_number);
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
    		rotors = Integer.parseInt(spinnerText);
    		}

    		@Override
    		public void onNothingSelected(AdapterView<?> arg0) {

    		}


    		});
        //creating the calculate button
        Button calculateButton = (Button) findViewById(R.id.button1);
        //onclick listener for button
        calculateButton.setOnClickListener(new OnClickListener() {
			
			

			@Override
			public void onClick(View v) {
				//creating needed views for the calculation
				EditText weightField = (EditText) findViewById(R.id.editText1);
				TextView ozweightfieldLabel = (TextView) findViewById(R.id.ozweightlabel);
				TextView ozweightfield = (TextView) findViewById(R.id.ozweight);
				TextView thrustAmnt = (TextView) findViewById(R.id.finalThrust);
				//converting the number entered in the weight field into a float
				float weight = Float.valueOf(weightField.getText().toString());
				//multiplying the weight in pounds by 16 to get ounces
				float weightOz = weight*16;
				//creating the weight in ounces label
				ozweightfieldLabel.setText("Copter Weight in Ounces.");
				//creating the weight in ounces field
				ozweightfield.setText(String.valueOf(weightOz));
				//setting color of ounces number
				ozweightfield.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
				//calculating duty cycle of the motors to get just below half stick hover
				double dutyCycle = weightOz*2.25;
				//getting the thrust per motor needed to maintain the hover
				double thrustPerMtr = dutyCycle/rotors;
				//converting the number into two decimal places
				double thrustPerMtrAdj = roundTwoDecimals(thrustPerMtr);
				//setting the text value and color for the required thrust number
				thrustAmnt.setText(String.valueOf(thrustPerMtrAdj));
				thrustAmnt.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
				//hiding the keyboard
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
			}
		});
        
        
        //creating the launch intent button
        Button viewerLaunchButton = (Button) findViewById(R.id.launchViewApp);
        //building on click listener
        viewerLaunchButton.setOnClickListener(new OnClickListener() {
    		
    		@Override
    		public void onClick(View v) {
    			//determining the url for the image based on rotor count
    			if (rotors == 8) {
    				rotorCountImageURI = "http://www.robotshop.com/Images/xbig/en/oktokopter-2-fully-loaded-octocopter-uav-B.jpg";
    			}else if (rotors == 6){
    				rotorCountImageURI = "http://www.dji.com/wp-content/uploads/2013/06/s800_evo_001.jpg";
    			}else if (rotors == 4){
    				rotorCountImageURI = "http://www.dji.com/wp-content/themes/dji/store/products/gallery/phantom/Phantom08.jpg";
    			}else if (rotors == 3){
    				rotorCountImageURI = "http://www.suasnews.com/wp-content/uploads/2011/02/scorpion-complete-1.jpg";
    			}
    			
    			Log.i("Passed URL", rotorCountImageURI);
    			
    			//create intent to launch browser
    			Intent intent = new Intent(Intent.ACTION_VIEW);
    			//parse uri from string
    			Uri uri = Uri.parse(rotorCountImageURI);
    			//set uri string as data for intent
    			intent.setData(uri);
    			//start intent
    			startActivity(intent);
    			
    		}
    	});
    }

    
    
    
    
    //two decimal rounding function
    /**
     * Round two decimals.
     *
     * @param d the d
     * @return the double
     */
    double roundTwoDecimals(double d) {
    	//Setting the two decimal format
    	DecimalFormat twoDForm = new DecimalFormat("#.##");
    	//returning the passed number reduced to two decimals
    	return Double.valueOf(twoDForm.format(d));
    }


    /* (non-Javadoc)
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
