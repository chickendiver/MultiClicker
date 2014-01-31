package com.example.multiclicker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Counter implements Serializable{
	
	protected String counterName;
    protected int counterValue;
    protected Button button;
    protected Context context;
    protected TextView counterCount;
    //Next line taken from: http://www.dreamincode.net/forums/topic/248522-serialization-in-android/
    private static final long serialVersionUID = 41052542;
    protected ArrayList<Integer> calendarYearList = new ArrayList<Integer>();
    protected ArrayList<Integer> calendarWeekList = new ArrayList<Integer>(); 
    protected ArrayList<Integer> calendarDayList = new ArrayList<Integer>();
    protected ArrayList<Integer> calendarHourList = new ArrayList<Integer>();
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
	
	public void addCalendarYear(){
		int calendarYear = Calendar.getInstance().get(Calendar.YEAR);
		calendarYearList.add(calendarYear);
	}
	
	public void addCalendarWeek(){
		int calendarWeek = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
		calendarYearList.add(calendarWeek);
	}
	
	public void addCalendarDay(){
		int calendarDay = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
		calendarYearList.add(calendarDay);
	}
	
	public void addCalendarHour(){
		int calendarHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		calendarHourList.add(calendarHour);
	}
	
	public ArrayList<Integer> getCalendarYears(){
		return calendarYearList;
	}
	
	public ArrayList<Integer> getCalendarWeeks(){
		return calendarWeekList;
	}
	
	public ArrayList<Integer> getCalendarDays(){
		return calendarDayList;
	}
	
	public ArrayList<Integer> getCalendarHours(){
		return calendarHourList;
	}
}
