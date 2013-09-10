package com.wickedsoftwaredesign.whenwherehow;

import java.util.List;


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
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener, LocationListener{

	TextView networkInfo;
	TextView networkName;
	TextView provider;
	TextView longitude;
	TextView latitude;
	TextView accuracy;
	Spinner spinner;
	Button locationButton;
	Button playerButton;
	Button sensorButton;
	LocationManager lm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Requesting no title bar for app view
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		//setting app orientation to landscape
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				
		setContentView(R.layout.activity_main);
		
		longitude = (TextView) findViewById(R.id.longCoord);
		latitude = (TextView) findViewById(R.id.latCoord);
		provider = (TextView) findViewById(R.id.providerType);
		accuracy = (TextView) findViewById(R.id.accuracyMeters);
		locationButton = (Button) findViewById(R.id.locationButton);
		playerButton = (Button) findViewById(R.id.launchPlayerActivity);
		sensorButton = (Button) findViewById(R.id.launchSensorActivity);
		
		IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
		NetworkReceiver receiver = new NetworkReceiver();
		this.registerReceiver(receiver, filter);
		
		lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		
		if(lm == null){
			this.alertMessage("No Location Manager Available");
		}else{
			spinner = (Spinner) findViewById(R.id.spinner);
			List<String> providers = this.lm.getAllProviders();
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, providers);
			spinner.setAdapter(adapter);
			
			this.locationButton.setOnClickListener(this);
			this.playerButton.setOnClickListener(this);
			this.sensorButton.setOnClickListener(this);
		
		}
		
		
	}

	public class NetworkReceiver extends BroadcastReceiver{
		
		@Override
		public void onReceive(Context context, Intent intent){
			ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo ni = cm.getActiveNetworkInfo();
			networkInfo = (TextView) findViewById(R.id.networkType);
			networkName = (TextView) findViewById(R.id.networkName);
			
				if (ni != null && ni.isAvailable()) {
					Log.i("Network Info", ni.toString());
					Log.i("Network Status", ni.getTypeName());
					networkInfo.setText(ni.getTypeName());
					networkName.setText(ni.getExtraInfo().toString());
				}else{
					networkInfo.setText("Not Connected");
					networkInfo.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
				}
			
		}
	}
	
	private void alertMessage(String message){
		AlertDialog.Builder alert = new AlertDialog.Builder(this).setMessage(message).setCancelable(true);
		alert.create().show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.equals(this.locationButton)){
			
			String provider = this.spinner.getSelectedItem().toString();
			
			if(lm.isProviderEnabled(provider) == false){
				this.alertMessage(provider + " is unavalable");
			}else{
				lm.requestLocationUpdates(provider, 2*1000/*Milliseconds*/, 0/*meters*/, this);
			}
		}else if (v.equals(this.playerButton)){
			Intent intent = new Intent(this, MoviePlayer.class);
			startActivity(intent);
		}else if (v.equals(this.sensorButton)){
			Intent intent = new Intent(this, SensorActivity.class);
			startActivity(intent);
		}
		
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		this.provider.setText(location.getProvider());
		this.longitude.setText(String.valueOf(location.getLongitude()));
		this.latitude.setText(String.valueOf(location.getLatitude()));
		
		if(location.hasAccuracy() == false){
			this.accuracy.setText("unavalable");
		}else{
			this.accuracy.setText(String.valueOf(location.getAccuracy()));
		}
		
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
