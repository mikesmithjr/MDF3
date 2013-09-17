/*
 * project     WhenWhereHow
 * 
 * package		com.wickedsoftwaredesign.whenwherehow
 * 
 * @author     Michael R. Smith Jr
 * 
 * date			Sep 11, 2013
 */
package com.wickedsoftwaredesign.whenwherehowactionbar;

import java.util.List;

import com.wickedsoftwaredesign.whenwherehowactionbar.R;


import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

// TODO: Auto-generated Javadoc
/**
 * The Class MainActivity.
 */
public class MainActivity extends Activity implements OnClickListener, LocationListener{

	TextView networkInfo;
	TextView networkName;
	TextView provider;
	TextView longitude;
	TextView latitude;
	TextView accuracy;
	Spinner spinner;
	Button locationButton;
	LocationManager lm;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		//setting app orientation to landscape
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				
		setContentView(R.layout.activity_main);
		//setting up fields
		networkInfo = (TextView) findViewById(R.id.networkType);
		networkName = (TextView) findViewById(R.id.networkName);
		longitude = (TextView) findViewById(R.id.longCoord);
		latitude = (TextView) findViewById(R.id.latCoord);
		provider = (TextView) findViewById(R.id.providerType);
		accuracy = (TextView) findViewById(R.id.accuracyMeters);
		locationButton = (Button) findViewById(R.id.locationButton);
		
		//starting the intent and network receiver for the connection status info
		IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
		NetworkReceiver receiver = new NetworkReceiver();
		this.registerReceiver(receiver, filter);
		//setting up the location manager for location status
		lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		//checking if the location manager is null
		if(lm == null){
			this.alertMessage("No Location Manager Available");
		}else{
			//building the spinner and spinner adapter for the provider list from the location manager
			spinner = (Spinner) findViewById(R.id.spinner);
			List<String> providers = this.lm.getAllProviders();
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, providers);
			spinner.setAdapter(adapter);
			//setting the on click listeners only after the location manager has data
			this.locationButton.setOnClickListener(this);
			
		
		}
		
		
	}

	/**
	 * The Class NetworkReceiver.
	 */
	public class NetworkReceiver extends BroadcastReceiver{
		
		/* (non-Javadoc)
		 * @see android.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
		 */
		@Override
		public void onReceive(Context context, Intent intent){
			//creates connectivity manager
			ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			//pulls the network info from the connectivity manager
			NetworkInfo ni = cm.getActiveNetworkInfo();
				//if the network info is not null and is able to connect 
				if (ni != null && ni.isAvailable()) {
					Log.i("Network Info", ni.toString());
					Log.i("Network Status", ni.getTypeName());
					//the network type and name are displayed
					networkInfo.setText(ni.getTypeName());
					networkName.setText(ni.getExtraInfo().toString());
				}else{
					//else field displays not connected in red
					networkInfo.setText("Not Connected");
					networkInfo.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
				}
			
		}
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
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		if (item.getItemId() == R.id.action_location) {
			
			alertMessage("You are already on that screen");
			
		}else if (item.getItemId() == R.id.action_video_player) {
			
			Intent intent = new Intent(this, MoviePlayer.class);
			startActivity(intent);
			
		}else if (item.getItemId() == R.id.action_sensors){
			
			Intent intent = new Intent(this, SensorActivity.class);
			startActivity(intent);
		}
		
		return super.onOptionsItemSelected(item);
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//checks what button is pushed and performs the selected action
		if(v.equals(this.locationButton)){
			//pulls the string from the provider spinner
			String provider = this.spinner.getSelectedItem().toString();
			//if the selected provider is not available user gets an alert message
			if(lm.isProviderEnabled(provider) == false){
				this.alertMessage(provider + " is unavalable");
			}else{
				//if the provider is available the provider is updated every 2000 milliseconds only and not with movement
				lm.requestLocationUpdates(provider, 2*1000/*Milliseconds*/, 0/*meters*/, this);
			}
			
		}
		
	}

	/* (non-Javadoc)
	 * @see android.location.LocationListener#onLocationChanged(android.location.Location)
	 */
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		//when the location change is triggered every 2000 milliseconds the provider, longitude, and latitude is displayed
		this.provider.setText(location.getProvider());
		this.longitude.setText(String.valueOf(location.getLongitude()));
		this.latitude.setText(String.valueOf(location.getLatitude()));
		//if the accuracy reading is not available then the text is changed to unavailable
		if(location.hasAccuracy() == false){
			this.accuracy.setText("unavailable");
		}else{
			//if accuracy is available then the accuracy text is displayed
			this.accuracy.setText(String.valueOf(location.getAccuracy()));
		}
		
	}

	/* (non-Javadoc)
	 * @see android.location.LocationListener#onProviderDisabled(java.lang.String)
	 */
	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see android.location.LocationListener#onProviderEnabled(java.lang.String)
	 */
	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see android.location.LocationListener#onStatusChanged(java.lang.String, int, android.os.Bundle)
	 */
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}
