package com.example.bigspeedwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.widget.RemoteViews;

public class SpeedWidgetProvider extends AppWidgetProvider{
	
	LocationManager lm;
	
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds){
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		
		
		
		for(int i=0; i<appWidgetIds.length; i++){
			int appWidgetId= appWidgetIds[i];
			
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.rczone.biz"));
			PendingIntent pending = PendingIntent.getActivity(context, 0, intent, 0);
			
			RemoteViews	rvs = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
			rvs.setOnClickPendingIntent(R.id.mapsButton, pending);
			
			appWidgetManager.updateAppWidget(appWidgetId, rvs);
		}
	}


	
	public void onDeleted(Context context, int[] appWidgetIds){
		
		SpeedUtility su = new SpeedUtility(context);
		su.clearWidgetSettings(context);
	}

	
}
