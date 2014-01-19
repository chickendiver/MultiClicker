package com.example.multiclicker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	
	static int counter = 0;
	Button btn1;
	TextView counterText;
	
	//Jan. 17th : http://simpledeveloper.com/how-to-build-simple-counter-android-app/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn1 = (Button)findViewById(R.id.button_text);
		counterText = (TextView)findViewById(R.id.text_view_1);
		btn1.setOnClickListener(this);
		if (savedInstanceState == null) {
			counterText.setText(Integer.toString(counter));
		}
		else {
			int counter = savedInstanceState.getInt("counter");
			counterText.setText(Integer.toString(counter));
		}
		
	}
	
	// Jan. 19th : http://stackoverflow.com/questions/151777/saving-activity-state-in-android

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/
	
	@Override
	public void onClick(View v) {
		if (v == btn1){ 
			counter++;
			counterText.setText(Integer.toString(counter));
		}
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putInt("counter", counter);
	}

}
