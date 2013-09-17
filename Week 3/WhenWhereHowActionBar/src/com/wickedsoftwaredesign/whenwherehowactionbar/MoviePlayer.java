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


import com.wickedsoftwaredesign.whenwherehowactionbar.R;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

// TODO: Auto-generated Javadoc
/**
 * The Class MoviePlayer.
 */
public class MoviePlayer extends Activity implements OnClickListener{

	
	VideoView viewer;
	Button vidButton;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
		setContentView(R.layout.activity_movie_player);
		//building the button from the xml file
		vidButton = (Button) findViewById(R.id.playVideoButton);
		//setting the on click listener
		vidButton.setOnClickListener(this);
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
			
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			
		}else if (item.getItemId() == R.id.action_video_player) {
			
			alertMessage("You are already on that screen");
			
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
		//when the button is clicked the video view is built
		viewer = (VideoView) findViewById(R.id.videoPlayer);
		//the file path string is created
		String path = "android.resource://" + getPackageName() + "/" + R.raw.mdf3video;
		//the path uri is parsed and passed to the video viewer
		viewer.setVideoURI(Uri.parse(path));
		//player control buttons are enabled
		viewer.setMediaController(new MediaController(this));
		//video is started
		viewer.start();
	}

}
