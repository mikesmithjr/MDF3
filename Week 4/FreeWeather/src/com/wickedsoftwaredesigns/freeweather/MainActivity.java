package com.wickedsoftwaredesigns.freeweather;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.Menu;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.GeolocationPermissions;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//setting app orientation to landscape
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		setContentView(R.layout.activity_main);
		
		//Building the WebView and setting up for JavaScript
		WebView freeWeatherView = (WebView) findViewById(R.id.webview);
		freeWeatherView.loadUrl("http://www.wickedsoftwaredesigns.com/FreeWeather/index.html");
		freeWeatherView.setWebViewClient(new WebViewClient());
		WebSettings webSettings = freeWeatherView.getSettings();
		webSettings.setGeolocationEnabled(true);
		webSettings.setGeolocationDatabasePath("data/data/FreeWeather");
		webSettings.setJavaScriptEnabled(true);
		freeWeatherView.setWebChromeClient(new WebChromeClient(){
			@Override
		     public void onGeolocationPermissionsShowPrompt(final String origin, final GeolocationPermissions.Callback callback) {
		        Log.i("geolocation", "onGeolocationPermissionsShowPrompt()");

		        final boolean remember = false;
		        AlertDialog.Builder builder = new AlertDialog.Builder(this);
		        builder.setTitle("Locations");
		        builder.setMessage("Would like to use your Current Location ")
		        .setCancelable(true).setPositiveButton("Allow", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		              // origin, allow, remember
		              callback.invoke(origin, true, remember);
		           }
		        }).setNegativeButton("Don't Allow", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		              // origin, allow, remember
		              callback.invoke(origin, false, remember);
		           }
		        });
		        AlertDialog alert = builder.create();
		        alert.show();
		     }
		});
		
		LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		LocationListener ll = new LocationListener() {
			
			
			@Override
			public void onLocationChanged(Location location) {
				// TODO Auto-generated method stub
				
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
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				// TODO Auto-generated method stub
				
			}
		};
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, ll);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
