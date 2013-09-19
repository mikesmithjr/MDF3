package com.example.bigspeedwidget;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SpeedUtility {

	private static final String PREFS_NAME = null;
	private static final String UNIT = null;
	SharedPreferences settings;
	Editor editor;
	Context _this;
	
	public SpeedUtility(Context context){
		_this = context;
	}
	
	public void Save(String unit){
		
		settings = _this.getSharedPreferences(PREFS_NAME, 0);
		editor = settings.edit();
		editor.putString(UNIT, unit);
		editor.apply();
	}
	
	
	
}

