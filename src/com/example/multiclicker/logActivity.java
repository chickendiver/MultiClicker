package com.example.multiclicker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class logActivity extends Activity {
	
	//Used to show the user logs about a counter. This is an incomplete class as it stands right now.
	
	//The default functionality is to display logs per day. This can be adjusted by using the menu options.
	
	protected ArrayList<Counter> counterLogList = new ArrayList<Counter>();
	protected Counter counterInstance = new Counter(this);
	protected TextView data;
	protected String dataString = ""; 
	
	@SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activitylog);
        Intent intent = getIntent();

        // Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        displayDailyLog();
    }

	
	private void displayYearlyLog(){
		ArrayList<Integer> calendarYears = new ArrayList<Integer>();
		calendarYears = counterInstance.getCalendarYears();
		Collections.sort(calendarYears, new Comparator<Integer>(){
			@Override public int compare(Integer i1, Integer i2){
				return (int) i2 - i1;
			}
		});
		int counter = 0;
		for (int i = 0, previous = -1; i < calendarYears.size();i++){
			if (previous == -1){
				dataString += "Log for Year" + calendarYears.get(i) + ": \n";
				counter++;
				continue;
			}
			
			if (calendarYears.get(i) == previous){
				counter++;
				previous = calendarYears.get(i);
			}
			else
			{
				dataString += Integer.toString(counter) + "\n";
				counter = 0;
				dataString += "Log for Year" + calendarYears.get(i) + ": \n";
				counter++;
				previous = calendarYears.get(i);
			}
		}
	}
	
	private void displayWeeklyLog(){
		ArrayList<Integer> calendarWeeks = new ArrayList<Integer>();
		calendarWeeks = counterInstance.getCalendarWeeks();
		Collections.sort(calendarWeeks, new Comparator<Integer>(){
			@Override public int compare(Integer i1, Integer i2){
				return (int) i2 - i1;
			}
		});
		int counter = 0;
		for (int i = 0, previous = -1; i < calendarWeeks.size();i++){
			if (previous == -1){
				dataString += "Log for Week" + calendarWeeks.get(i) + ": \n";
				counter++;
				continue;
			}
			
			if (calendarWeeks.get(i) == previous){
				counter++;
				previous = calendarWeeks.get(i);
			}
			else
			{
				dataString += Integer.toString(counter) + "\n";
				counter = 0;
				dataString += "Log for Week" + calendarWeeks.get(i) + ": \n";
				counter++;
				previous = calendarWeeks.get(i);
			}
		}
	}
	
	private void displayDailyLog(){
		ArrayList<Integer> calendarDays = new ArrayList<Integer>();
		calendarDays = counterInstance.getCalendarDays();
		Collections.sort(calendarDays, new Comparator<Integer>(){
			@Override public int compare(Integer i1, Integer i2){
				return (int) i2 - i1;
			}
		});
		int counter = 0;
		for (int i = 0, previous = -1; i < calendarDays.size();i++){
			if (previous == -1){
				dataString += "Log for Day" + calendarDays.get(i) + ": \n";
				counter++;
				continue;
			}
			
			if (calendarDays.get(i) == previous){
				counter++;
				previous = calendarDays.get(i);
			}
			else
			{
				dataString += Integer.toString(counter) + "\n";
				counter = 0;
				dataString += "Log for Day" + calendarDays.get(i) + ": \n";
				counter++;
				previous = calendarDays.get(i);
			}
			
		}
	}
	
	private void displayHourlyLog(){
		ArrayList<Integer> calendarHours = new ArrayList<Integer>();
		calendarHours = counterInstance.getCalendarDays();
		Collections.sort(calendarHours, new Comparator<Integer>(){
			@Override public int compare(Integer i1, Integer i2){
				return (int) i2 - i1;
			}
		});
		int counter = 0;
		for (int i = 0, previous = -1; i < calendarHours.size();i++){
			if (previous == -1){
				dataString += "Log for Hour" + calendarHours.get(i) + ": \n";
				counter++;
				continue;
			}
			
			if (calendarHours.get(i) == previous){
				counter++;
				previous = calendarHours.get(i);
			}
			else
			{
				dataString += Integer.toString(counter) + "\n";
				counter = 0;
				dataString += "Log for Hour" + calendarHours.get(i) + ": \n";
				counter++;
				previous = calendarHours.get(i);
			}
			
		}
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.logmenu, menu);
        return true;
    }
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
         
        switch (item.getItemId())
        {
 
        case R.id.menu_yearly:
        
        		displayYearlyLog();
                Toast.makeText(logActivity.this, "Log ordered by year", Toast.LENGTH_SHORT).show();
            return true;
 
        case R.id.menu_weekly:
          
        	displayWeeklyLog();
            Toast.makeText(logActivity.this, "Log ordered by week", Toast.LENGTH_SHORT).show();
            return true;
           
        case R.id.menu_daily:
        	
        	displayDailyLog();
        	Toast.makeText(logActivity.this, "Log ordered by day", Toast.LENGTH_SHORT).show();
            return true;
            
        case R.id.menu_hourly:
        	
        	displayHourlyLog();
        	Toast.makeText(logActivity.this, "Log ordered by hour", Toast.LENGTH_SHORT).show();
        	return true;
 
        default:
            return super.onOptionsItemSelected(item);
        }
    }
	
}
