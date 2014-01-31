package com.example.multiclicker;

import java.io.BufferedReader;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JSONFiler {

	String storageFile = "JSONfile.txt";
	Context context;
	Type listofCounters = new TypeToken<List<Counter>>() {}.getType();
	Gson gson = new Gson();
	
	public void writeObjectsToFile(Context context, List<Counter> counterList){
		this.context = context;
		
		try{
			Writer osWriter = new OutputStreamWriter(new FileOutputStream(storageFile));
			List<Counter> outputCounterList = Collections.synchronizedList(new ArrayList<Counter>());
			for (int i=0; i<counterList.size(); i++){
				outputCounterList.add(counterList.get(i));
			}
			
			gson.toJson(outputCounterList, osWriter);
			osWriter.close();
		}
		catch (Exception fileException) {
			fileException.printStackTrace();
		}
		
	}
	
	public List<Counter> readObjectsFromFile(Context context){
		this.context = context;
		List<Counter> counters = new ArrayList<Counter>();
		List<Counter> inputCounterList = new ArrayList<Counter>();
		try{
			Reader isReader = new InputStreamReader(new FileInputStream((storageFile)));
			inputCounterList = Collections.synchronizedList((List<Counter>) gson.fromJson(isReader, listofCounters));
			isReader.close();
		}
		catch (Exception fileException){
			fileException.printStackTrace();
		}

		//ArrayList<Counter> counterList = new ArrayList<Counter>();
		/*try{
			FileInputStream fis = context.openFileInput(storageFile);
			InputStreamReader isReader = new InputStreamReader(fis);
			BufferedReader buffRead = new BufferedReader(isReader);
			StringBuilder sBuilder = new StringBuilder();
			String line;
			while ((line = buffRead.readLine()) != null){
				sBuilder.append(line);
			}
			Gson gson = new Gson();
			Type listType = new TypeToken<List<Counter>>(){}.getType();
			counters = (List<Counter>) gson.fromJson(storageFile, listType);
			fis.close();
			
		}
		catch (Exception fileException){
			fileException.printStackTrace();
		}*/
		
		return inputCounterList;
	}
}
