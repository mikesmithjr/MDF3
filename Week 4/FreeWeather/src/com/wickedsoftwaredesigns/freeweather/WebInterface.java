package com.wickedsoftwaredesigns.freeweather;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;

public class WebInterface {

	Context context;
	
	
	WebInterface(Context _context){
		context = _context;
	}
	
	@JavascriptInterface
	public void storeUsername(String string){
		Log.i("storeUsername", string);
		SharedPreferences prefs = context.getSharedPreferences("userPrefs", 0);
		SharedPreferences.Editor editPrefs = prefs.edit();
		editPrefs.putString("UserName", string);
		editPrefs.commit();
		
	}
	@JavascriptInterface
	public String getUsername(){
		String userName = "No Username Saved";
		SharedPreferences prefs = context.getSharedPreferences("userPrefs", 0);
		userName = prefs.getString("UserName", "No Username Saved.");
		Log.i("getUsername", userName);
		
		return userName;
	}
}
