/*
 * project     WhenWhereHow
 * 
 * package		com.wickedsoftwaredesign.whenwherehow
 * 
 * @author     Michael R. Smith Jr
 * 
 * date			Sep 11, 2013
 */
package com.wickedsoftwaredesign.whenwherehow;


import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
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
		
		//Requesting no title bar for app view
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_movie_player);
		//building the button from the xml file
		vidButton = (Button) findViewById(R.id.playVideoButton);
		//setting the on click listener
		vidButton.setOnClickListener(this);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.movie_player, menu);
		return true;
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
