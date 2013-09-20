package com.example.bigspeedwidget;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.LocationManager;

public class SpeedUtility {

	private static final String PREFS_NAME = null;
	private static final String UNIT = null;
	SharedPreferences settings;
	Editor editor;
	Context _this;
	LocationManager lm;
	
	public SpeedUtility(Context context){
		_this = context;
	}
	
	public void Save(String unit){
		
		settings = _this.getSharedPreferences(PREFS_NAME, 0);
		editor = settings.edit();
		editor.putString(UNIT, unit);
		editor.apply();
	}
	
	public void clearWidgetSettings(Context context){
		
		settings = _this.getSharedPreferences(PREFS_NAME, 0);
		editor = settings.edit();
		editor.clear();
		editor.commit();
	}

	public float speedInMph(Float speedInMils){
		
		
		float speedInMiles = (float) (speedInMils/(1609.44/3600));
		
		return speedInMiles;
	}
	
	public float speedInKph(Float speedInMils){
		float speedInKph = (float) (speedInMils/3.6);
		return speedInKph;
	}
	
}

