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

public class MoviePlayer extends Activity implements OnClickListener{

	
	VideoView viewer;
	Button vidButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Requesting no title bar for app view
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_movie_player);
		
		vidButton = (Button) findViewById(R.id.playVideoButton);
		vidButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.movie_player, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		viewer = (VideoView) findViewById(R.id.videoPlayer);
		String path = "android.resource://" + getPackageName() + "/" + R.raw.mdf3video;
		viewer.setVideoURI(Uri.parse(path));
		viewer.setMediaController(new MediaController(this));
		viewer.start();
	}

}
