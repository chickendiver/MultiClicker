package com.example.multiclicker;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

	protected ListView counterListView; 
	protected List<Counter> counterList = new ArrayList<Counter>();
	protected CounterAdapter adapter = new CounterAdapter (this, counterList);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainlistview);

		counterListView = (ListView) findViewById(R.id.listmain);
		
		counterList.add(new Counter(this));
		
		counterListView.setAdapter(adapter);
		
	}
	
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
            adapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this, "All Counters Reset", Toast.LENGTH_SHORT).show();
            return true;
           
        case R.id.menu_log:
            Toast.makeText(MainActivity.this, "Log Access Requested", Toast.LENGTH_SHORT).show();
            return true;
 
        default:
            return super.onOptionsItemSelected(item);
        }
    }    
}
