package com.example.multiclicker;

//import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Toast;

// Original structure based on article by Pete Houston, accessed on Jan. 21st at:
// http://xjaphx.wordpress.com/2011/06/11/create-a-simple-phone-book/
public class CounterAdapter extends BaseAdapter {
	private Context context;
	private List<Counter> counterList;
	public CounterAdapter(Context context, List<Counter> counterList) {
		this.context = context;
		this.counterList = counterList;
	}
	
	@Override
	public int getCount() {
		return counterList.size();
	}
	
	@Override
	public Object getItem(int pos){
		return counterList.get(pos);
	}
	
	@Override
	public long getItemId(int pos) {
		return pos;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final int pos = position;
		final Counter counterInstance = counterList.get(pos);	//Suggestion taken from: http://stackoverflow.com/questions/5997953/cannot-refer-to-a-non-final-variable-i-inside-an-inner-class-defined-in-a-differ
		
		// Inflate list view layout if null
				if(convertView == null) {
					LayoutInflater inflater = LayoutInflater.from(context);
					convertView = inflater.inflate(R.layout.counter_layout, null);
				}
				
		// set counter's textView
		//TextView counterCount = (TextView)convertView.findViewById(R.id.text_view_1);
		counterInstance.createButtonTextView(convertView);
		counterInstance.counterCount.setText(Integer.toString(counterInstance.getCounterValue()));
				
		// set counter's button
		counterInstance.button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v){
				counterInstance.incrementCounter();
				counterInstance.counterCount.setText(Integer.toString(counterInstance.getCounterValue()));
				counterInstance.addCalendarYear();
				counterInstance.addCalendarWeek();
				counterInstance.addCalendarDay();
				notifyDataSetChanged();
				
				//TODO Write to a file here...
			}
		});
		counterInstance.button.setOnLongClickListener(new View.OnLongClickListener() { 
	        @Override
	        public boolean onLongClick(View v) {
	        	AlertDialog.Builder counterOptionsADB = new AlertDialog.Builder(context);
	        	counterOptionsADB.setCancelable(true);
	        	//This button opens a dialog to warn the user before proceeding to erase all counter data
	        	counterOptionsADB.setPositiveButton("Delete Counter", new DialogInterface.OnClickListener(){
	        		public void onClick(DialogInterface dialog, int id){
	        			AlertDialog.Builder deleteConfirmationADB = new AlertDialog.Builder(context);
	        			deleteConfirmationADB.setCancelable(true);
	        			deleteConfirmationADB.setMessage("Are you sure you want to delete this counter?");
	        			deleteConfirmationADB.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
	    	        		public void onClick(DialogInterface dialog, int id) {
	    	        			counterList.remove(pos);
	    	        			notifyDataSetChanged();
	    	        			Toast.makeText(context, "Counter Removed", Toast.LENGTH_SHORT).show();
	    	        		}
	    	        	});
	        			deleteConfirmationADB.setNegativeButton("No", new DialogInterface.OnClickListener(){
	    	        		public void onClick(DialogInterface dialog, int id) {
	    	        			dialog.cancel();
	    	        		}
	    	        	});
	        			AlertDialog deleteDialog = deleteConfirmationADB.create();
	        			deleteDialog.show();
	        		}
	        	});
	        	//End warning dialog
	        	counterOptionsADB.setNeutralButton("Rename", new DialogInterface.OnClickListener(){
	        		// Opens a new dialog, asking for an input from the user
	        		public void onClick(DialogInterface dialog, int id) {
	        			AlertDialog.Builder renameADB = new AlertDialog.Builder(context);
	        			final EditText renameInput = new EditText(context);
	        			renameADB.setView(renameInput);
	        			renameADB.setCancelable(true);
	        			renameADB.setPositiveButton("Submit", new DialogInterface.OnClickListener(){
	        				public void onClick(DialogInterface dialog, int id){
	        					String newName = renameInput.getText().toString();
	        					// TODO Check if this name already exists in the list of counters and if the name is blank.
	        					counterInstance.setCounterName(newName);
	        					notifyDataSetChanged();
	        					Toast.makeText(context, "Counter Renamed", Toast.LENGTH_SHORT).show();
	        				}
	        			});
	        			renameADB.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
	        				public void onClick(DialogInterface dialog, int id){
	        					dialog.cancel();
	        				}
	        			});
	        			AlertDialog renameDialog = renameADB.create();
	        			renameDialog.show();
	        		}
	        	});
	        	// End rename dialog
	        	counterOptionsADB.setNegativeButton("Access Data Log", new DialogInterface.OnClickListener(){
	        		public void onClick(DialogInterface dialog, int id) {
	        			Toast.makeText(context, "Log Access Requested", Toast.LENGTH_SHORT).show();
	        		}
	        	});
	        	
	        	AlertDialog aDialog = counterOptionsADB.create();
	        	aDialog.show();
	        	
	            return true;
	        }
	    });


		
		return convertView;
	}
	
}
