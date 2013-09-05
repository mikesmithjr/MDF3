/*
 * project     MultiRotorViewer
 * 
 * package		com.wickedsoftwaredesigns.multirotorviewer
 * 
 * @author     Michael R. Smith Jr
 * 
 * date			Sep 4, 2013
 */
package com.wickedsoftwaredesigns.multirotorviewer;

import java.net.MalformedURLException;
import java.net.URL;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;

// TODO: Auto-generated Javadoc
/**
 * The Class MainActivity.
 */
public class MainActivity extends Activity {

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Requesting no title bar for app view
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//setting app view to fullscreen
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//setting app orientation to landscape
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		setContentView(R.layout.activity_main);
		
		
		//getting intent info sent from calling application
		Intent intent = getIntent();
		//pulling uri data from the intent
		Uri data = intent.getData();
		//checking to see if there is data
		if (data != null) {
			
			Log.i("uri data", data.toString());
			//creating empty URL object
			URL url = null;
			try {
				//crating the url by pulling the uri data from the intent data
				url = new URL(data.getScheme(), data.getHost(), data.getPath());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//creating an instance of the webview
			WebView multirotorViewer = (WebView) findViewById(R.id.webView);
			//adding the url information to the webview to pull the data from the internet
			multirotorViewer.loadUrl(url.toString());
			//resizing the images to help fit the larger ones in the screen
			multirotorViewer.getSettings().setLayoutAlgorithm(
					LayoutAlgorithm.SINGLE_COLUMN);
		}else{
			//if no data in the intent the app closes
			Log.i("Viewer App", "No Intent Data");
			finish();
		}
		
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
