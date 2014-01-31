package com.example.multiclicker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class logActivity extends Activity {
	
	protected ArrayList<Counter> counterLogList = new ArrayList<Counter>();
	protected Counter counterInstance = new Counter(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
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
		for (int i = 0, counter = 0, previous = -1; i < calendarYears.size();i++){
			if (previous == -1){
				//print out the current day
				counter++;
				continue;
			}
			
			if (calendarYears.get(i) == previous){
				counter++;
				previous = calendarYears.get(i);
			}
			else
			{
				//print counter number
				counter = 0;
				//print new day
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
		for (int i = 0, counter = 0, previous = -1; i < calendarWeeks.size();i++){
			if (previous == -1){
				//print out the current day
				counter++;
				continue;
			}
			
			if (calendarWeeks.get(i) == previous){
				counter++;
				previous = calendarWeeks.get(i);
			}
			else
			{
				//print counter number
				counter = 0;
				//print new day
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
		
		for (int i = 0, counter = 0, previous = -1; i < calendarDays.size();i++){
			if (previous == -1){
				//print out the current day
				counter++;
				continue;
			}
			
			if (calendarDays.get(i) == previous){
				counter++;
				previous = calendarDays.get(i);
			}
			else
			{
				//print counter number
				counter = 0;
				//print new day
				counter++;
				previous = calendarDays.get(i);
			}
			
		}
	}
}
