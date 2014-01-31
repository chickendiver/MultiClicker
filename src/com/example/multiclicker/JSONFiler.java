package com.example.multiclicker;

//import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class JSONFiler {

	String storageFile = "JSONfile.sav";
	Context context;
	Type listofCounters = new TypeToken<List<Counter>>() {}.getType();
	Gson gson = new Gson();
	

	public void writeObjectsToFile(Context context, List<Counter> counterList){
		this.context = context;
		SharedPreferences prefs = context.getSharedPreferences("jsonObjects", Context.MODE_PRIVATE);
		
		Type type = new TypeToken<ArrayList<Counter>>(){}.getType();
		String value = gson.toJson(counterList, type);
		
		Editor e = prefs.edit();
		e.putString("list", value);
		e.commit();
		
		////
		/*
		try{
			FileOutputStream fos = context.openFileOutput(storageFile, Context.MODE_PRIVATE);
			Writer osWriter = new OutputStreamWriter(fos);
			//List<Counter> outputCounterList = Collections.synchronizedList(new ArrayList<Counter>());
			Type type = new TypeToken<ArrayList<Counter>>(){}.getType();
			
			osWriter.write(gson.toJson(counterList, type));
			osWriter.close();
			fos.close();
		}
		catch (Exception fileException) {
			fileException.printStackTrace();
		}
		*/
	}
	
	public List<Counter> readObjectsFromFile(Context context){
		this.context = context;
		List<Counter> inputCounterList = new ArrayList<Counter>();
		Type type = new TypeToken<ArrayList<Counter>>(){}.getType();
		
		SharedPreferences prefs = context.getSharedPreferences("jsonObjects", Context.MODE_PRIVATE);
		String value = prefs.getString("list", null);
		GsonBuilder gsonb = new GsonBuilder();
		Gson gson = gsonb.create();
		inputCounterList = gson.fromJson(value, type);
		
		//
		/*
		File file = context.getFileStreamPath(storageFile);
		if(file.exists()){
			try{
				FileInputStream fiStream = context.openFileInput(storageFile);
				Reader isReader = new InputStreamReader(fiStream);
				Type type = new TypeToken<ArrayList<Counter>>(){}.getType();
				inputCounterList = gson.fromJson(isReader, type);
				isReader.close();
				fiStream.close();
			}
			catch (Exception fileException){
				fileException.printStackTrace();
			}
		}*/
		return inputCounterList;
		
	}
}
