// CLEAN ME!!!!!!!!

package com.example.multiclicker;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
//import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
import android.widget.ListView;
//import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity { //implements OnClickListener {
	
	// As a basic starting point, I accessed this resource on Jan. 17th:
	// http://simpledeveloper.com/how-to-build-simple-counter-android-app/
	
	//static int counter = 0;
	//protected Button btn1;
	//protected TextView counterText;
	protected ListView counterListView; 
	protected List<Counter> counterList = new ArrayList<Counter>();
	protected CounterAdapter adapter = new CounterAdapter (this, counterList);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainlistview);
		
		/*
		btn1 = (Button)findViewById(R.id.button_text);
		counterText = (TextView)findViewById(R.id.text_view_1);
		btn1.setOnClickListener(this);*/
		
		counterListView = (ListView) findViewById(R.id.listmain);
		
		counterList.add(new Counter(this));
		
		counterListView.setAdapter(adapter);
		/*
		// Not sure if needed...
		if (savedInstanceState != null) {
			counter = savedInstanceState.getInt("counter");
		}
		counterText.setText(Integer.toString(counter));
		*/
	}
	
	/*
	// The following handles what happens when a button is clicked
	@Override
	public void onClick(View v) {
		if (v == btn1){ 
			counter++;
			counterText.setText(Integer.toString(counter));
		}
	}
	*/
	
	// The following handles how variables are saved
	// For help implementing this, I accessed the following resource on Jan. 19th
	// http://stackoverflow.com/questions/151777/saving-activity-state-in-android
	/*@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		//savedInstanceState.putInt("counter", counter);
	}*/
	
	// The following takes care of inflating the menu when pressed
	// Resources are stored in res/menu/main.xml
	@Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }

	// The following takes care of what to do with menu items
	@Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
         
        switch (item.getItemId())
        {
 
        case R.id.menu_add:
        	counterList.add(new Counter(this));
        	adapter.notifyDataSetChanged ();
            Toast.makeText(MainActivity.this, "Counter Added", Toast.LENGTH_SHORT).show();
            return true;
 
        case R.id.menu_reset:
            Toast.makeText(MainActivity.this, "All Counters Reset", Toast.LENGTH_SHORT).show();
           // counter = 0;
           // counterText.setText(Integer.toString(counter));
            return true;
           
        case R.id.menu_log:
            Toast.makeText(MainActivity.this, "Log Access Requested", Toast.LENGTH_SHORT).show();
            return true;
 
        default:
            return super.onOptionsItemSelected(item);
        }
    }    
}
