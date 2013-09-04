package com.wickedsoftwaredesigns.multirotorviewer;

import java.net.MalformedURLException;
import java.net.URL;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.webkit.WebView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Intent intent = getIntent();
		Uri data = intent.getData();
		Log.i("uri data", data.toString());
		
		URL url = null;
		
		try {
			url = new URL(data.getScheme(), data.getHost(), data.getPath());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		WebView multirotorViewer = (WebView) findViewById(R.id.webView);
		multirotorViewer.loadUrl(url.toString());
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
