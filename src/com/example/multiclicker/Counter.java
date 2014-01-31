package com.example.multiclicker;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Counter {
	
	protected String counterName;
    int counterValue;
    Button button;
    Context context;
    TextView counterCount;
    
    // Constructor
    public Counter(Context context, String counterName, int counterValue) {
        this.counterName = counterName;
        this.counterValue= counterValue;
        this.context = context;
    }
    
    //Default constructor
    public Counter(Context context) {
    	this.counterName = "Counter";
    	this.counterValue = 0;
    	this.context = context;
    }
    
    public void createButtonTextView(View view){
    	this.button = (Button) view.findViewById(R.id.button_text);
    	this.counterCount = (TextView) view.findViewById(R.id.text_view_1);
    	this.button.setText(counterName);
    }
    
    public String getCounterName() {
		return counterName;
	}

	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}

	public int getCounterValue() {
		return counterValue;
	}

	public void setCounterValue(int counterValue) {
		this.counterValue = counterValue;
	}
	
	public void resetCounter() {
		this.counterValue = 0;
		Toast.makeText(context, "Counter '" + counterName + "' Reset", Toast.LENGTH_SHORT).show();
	}

	public void incrementCounter(){
		this.counterValue++;
	}
}
