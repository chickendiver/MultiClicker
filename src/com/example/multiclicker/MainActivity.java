package com.example.multiclicker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity { //implements OnClickListener {
	
	// As a basic starting point, I accessed this resource on Jan. 17th:
	// http://simpledeveloper.com/how-to-build-simple-counter-android-app/
	
	/* This code does not implement any form of data persistence. This is currently being worked on.*/
	
	


	protected ListView counterListView; 
	protected List<Counter> counterList = new ArrayList<Counter>();
	protected CounterAdapter adapter = new CounterAdapter (this, counterList);
	protected DataStorage dataController = new DataStorage();
	protected static int firstTime;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainlistview);
		

		counterListView = (ListView) findViewById(R.id.listmain);
		counterListView.setAdapter(adapter);
		

	}
	
	@Override
	public void onResume(){
		super.onResume();
		ArrayList<Counter> tempCounterList = new ArrayList<Counter>();
		tempCounterList = dataController.readObjectsFromFile(MainActivity.this);
		
		if (tempCounterList == null){
			askForFirstTime();
		}
		else{
			counterList.clear();
			for (int i=0;i<tempCounterList.size();i++){
				counterList.add(tempCounterList.get(i));
			}
			adapter.notifyDataSetChanged ();
		}

			
	}
	
	protected void askForFirstTime(){
		final EditText addNameInput = new EditText(MainActivity.this);
    	AlertDialog.Builder addCounterADB = new AlertDialog.Builder(MainActivity.this);
		addCounterADB.setCancelable(false);
		addCounterADB.setMessage("Add a name for the first counter");
		addCounterADB.setView(addNameInput);
		addCounterADB.setPositiveButton("Submit", new DialogInterface.OnClickListener(){
    		public void onClick(DialogInterface dialog, int id) {
    			String name = addNameInput.getText().toString();
    			// TODO Check if this name already exists in the list of counters and if the name is blank.
            	counterList.add(new Counter(MainActivity.this, name, 0));
            	dataController.writeObjectsToFile(MainActivity.this, counterList);
            	adapter.notifyDataSetChanged ();
                Toast.makeText(MainActivity.this, "Counter Added", Toast.LENGTH_SHORT).show();
    		}
    	});
		addCounterADB.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
    		public void onClick(DialogInterface dialog, int id) {
    			dialog.cancel();
    		}
    	});
		AlertDialog deleteDialog = addCounterADB.create();
		deleteDialog.show();
	}
	
	//Called when the user requests the logs for counters.
	public void logActivity(){
		Intent intent = new Intent(this, logActivity.class);
	    startActivity(intent);

	}
		
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
        	final EditText addNameInput = new EditText(MainActivity.this);
        	AlertDialog.Builder addCounterADB = new AlertDialog.Builder(MainActivity.this);
			addCounterADB.setCancelable(false);
			addCounterADB.setMessage("Add a name for this counter");
			addCounterADB.setView(addNameInput);
			addCounterADB.setPositiveButton("Submit", new DialogInterface.OnClickListener(){
        		public void onClick(DialogInterface dialog, int id) {
        			String name = addNameInput.getText().toString();
        			// TODO Check if this name already exists in the list of counters and if the name is blank.
                	counterList.add(new Counter(MainActivity.this, name, 0));
                	dataController.writeObjectsToFile(MainActivity.this, counterList);
                	adapter.notifyDataSetChanged ();
                    Toast.makeText(MainActivity.this, "Counter Added", Toast.LENGTH_SHORT).show();
        		}
        	});
			addCounterADB.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
        		public void onClick(DialogInterface dialog, int id) {
        			dialog.cancel();
        		}
        	});
			AlertDialog deleteDialog = addCounterADB.create();
			deleteDialog.show();
            return true;
 
        case R.id.menu_reset:
            for (int i = 0; i < counterList.size(); i++)
            {
            	counterList.get(i).setCounterValue(0);
            }
            dataController.writeObjectsToFile(MainActivity.this, counterList);
            adapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this, "All Counters Reset", Toast.LENGTH_SHORT).show();
            return true;
           
        case R.id.menu_log:
            Toast.makeText(MainActivity.this, "Log Access Requested", Toast.LENGTH_SHORT).show();
            logActivity();
            return true;
            
        case R.id.menu_order:
        	
        	// To order the counters as they're incremented, utilized Collections with custom Comparator, as suggested here:
			// http://stackoverflow.com/questions/2535124/how-to-sort-an-arraylist-of-objects-by-a-property
			Collections.sort(counterList, new Comparator<Counter>(){
				@Override public int compare(Counter c1, Counter c2){
					return c2.getCounterValue() - c1.getCounterValue();
				}
			});
			dataController.writeObjectsToFile(MainActivity.this, counterList);
			adapter.notifyDataSetChanged();
        	Toast.makeText(MainActivity.this, "Instituted a New World Order", Toast.LENGTH_SHORT).show();
 
        default:
            return super.onOptionsItemSelected(item);
        }
    }    
}
