package com.example.multiclicker;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
//import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
//import android.widget.Button;
//import android.widget.TextView;

// Based largely on article by Pete Houston, accessed on Jan. 21st at:
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
		// Use the proper counter
		final int pos = position;
		final Counter counterInstance = counterList.get(pos); // Not sure if final is appropriate here.
		//Suggestion taken from: http://stackoverflow.com/questions/5997953/cannot-refer-to-a-non-final-variable-i-inside-an-inner-class-defined-in-a-differ
		
		// Inflate list view layout if null (copied exactly)
				if(convertView == null) {
					LayoutInflater inflater = LayoutInflater.from(context);
					convertView = inflater.inflate(R.layout.counter_layout, null);
				}
				
		// set counter's textView
		//TextView counterCount = (TextView)convertView.findViewById(R.id.text_view_1);
		counterInstance.createButtonTextView(convertView);
		counterInstance.counterCount.setText(Integer.toString(counterInstance.getCounterValue()));
				
		// set counter's button
		// MAKE THIS ATTACHED TO COUNTER CLASS CONSTRUCTOR, NOT HERE
		//Button counterbutton = (Button) convertView.findViewById(R.id.button_text);
		counterInstance.button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v){
				//SetResult(RESULT_OK);
				counterInstance.incrementCounter();
				counterInstance.counterCount.setText(Integer.toString(counterInstance.getCounterValue()));
				notifyDataSetChanged();
			}
		});
		counterInstance.button.setOnLongClickListener(new View.OnLongClickListener() { 
	        @Override
	        public boolean onLongClick(View v) {
	        	AlertDialog.Builder adb = new AlertDialog.Builder(context);
	        	adb.setCancelable(true);
	        	adb.setPositiveButton("Delete Counter", new DialogInterface.OnClickListener(){
	        		public void onClick(DialogInterface dialog, int id){
	        			// TODO don't forget to add an "are you sure" dialog.
	        			counterList.remove(pos);
	        			notifyDataSetChanged();
	        		}
	        	});
	        	
	        	adb.setNeutralButton("Rename", new DialogInterface.OnClickListener(){
	        		public void onClick(DialogInterface dialog, int id) {
	        			AlertDialog.Builder adb2 = new AlertDialog.Builder(context);
	        			final EditText renameInput = new EditText(context);
	        			adb2.setView(renameInput);
	        			adb2.setCancelable(true);
	        			
	        			adb2.setNegativeButton("Submit", new DialogInterface.OnClickListener(){
	        				public void onClick(DialogInterface dialog, int id){
	        					String newName = renameInput.getText().toString();
	        					counterInstance.setCounterName(newName);
	        					notifyDataSetChanged();
	        				}
	        			});
	        			
	        			adb2.setPositiveButton("Cancel", new DialogInterface.OnClickListener(){
	        				public void onClick(DialogInterface dialog, int id){
	        					dialog.cancel();
	        				}
	        			});
	        			AlertDialog aDialog2 = adb2.create();
	        			aDialog2.show();
	        		}
	        	});
	        	
	        	adb.setNegativeButton("Reset", new DialogInterface.OnClickListener(){
	        		public void onClick(DialogInterface dialog, int id) {
	        			counterInstance.setCounterValue(0);
	        			notifyDataSetChanged();
	        		}
	        	});
	        	
	        	AlertDialog aDialog = adb.create();
	        	aDialog.show();
	        	
	            return true;
	        }
	    });


		
		return convertView;
	}
	
	/*@Override
	public void onClick(View v) {
		//TODO Figure out if this is the right place for button clicks
		// AlertDialog example to allow user to use custom name accessed on Jan 23:
		// http://www.androidsnippets.com/prompt-user-input-with-an-alertdialog
		
	}*/
}
