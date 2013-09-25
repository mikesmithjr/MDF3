package com.wickedsoftwaredesigns.freeweather;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.webkit.JavascriptInterface;

public class WebInterface {

	Context context;
	LocationManager lm;
	LocationListener ll;
	
	WebInterface(Context _context){
		context = _context;
	}
	
	@JavascriptInterface
	public void onLocationRequest(){
		lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		ll = new LocationListener() {
			
			
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
}
