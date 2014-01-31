package com.example.multiclicker;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import android.content.Context;

import com.google.gson.Gson;

public class JSONFiler {

	String storageFile = "JSONfile.txt";
	Gson gson = new Gson();
	Context context;
	
	public void writeObjectsToFile(Context context, ArrayList<Counter> counterList){
		this.context = context;
		try{
			FileOutputStream fos = context.openFileOutput(storageFile, Context.MODE_PRIVATE);
			for(int i = 0; i < counterList.size(); i++){
				String JSONString = gson.toJson(counterList.get(i));
				fos.write(JSONString.getBytes());
			}
			fos.close();
		}
		catch (Exception fileException) {
			fileException.printStackTrace();
		}
		
	}
	
	public void readObjectsFromFile(Context context){
		this.context = context;
		try{
			//FileInputStream fis = context.openFileInput(storageFile, Context.MODE_PRIVATE);
			
		}
		catch (Exception fileException){
			fileException.printStackTrace();
		}
	}
}
