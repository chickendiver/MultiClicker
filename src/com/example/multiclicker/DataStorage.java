package com.example.multiclicker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class DataStorage {

	// This class was a struggle. Eventually lead to using this as a reference, using ObjectOutputStream:
	// http://stackoverflow.com/questions/15428304/saving-object-to-file-via-objectoutputstream on Jan. 31
	protected String storageFile = "/saveFile";
	protected Context context;
	protected File file;

	public void writeObjectsToFile(Context context, List<Counter> counterList){
		this.context = context;
		try
		{   
			storageFile = context.getFilesDir().getPath().toString() + storageFile;
		    file = new File(storageFile);
		    if (!file.exists()) {
		    	file.mkdirs();
		        if (!file.createNewFile()) {
		           throw new IOException("Unable to create file");
		        }
		    }
		    //FileOutputStream fileout = context.openFileOutput(storageFile, Context.MODE_PRIVATE);
		    FileOutputStream fileout = new FileOutputStream(file);
		    ObjectOutputStream out = new ObjectOutputStream(fileout);
		    for (int i=0; i<counterList.size();i++){
		    	out.writeObject(counterList.get(i));
		    }
		    fileout.close();
		    out.close();
		} 
		catch (Exception ex)
		{
				ex.printStackTrace();
		}
		
	}
	
	public ArrayList<Counter> readObjectsFromFile(Context context){
		this.context = context;
		ArrayList<Counter> inputCounterList = new ArrayList<Counter>();
		try
		{
			File file = new File(storageFile);
			if (!file.exists()) {
				return null;
			}
			else
			{
				FileInputStream filein = context.openFileInput(storageFile);
			    ObjectInputStream in = new ObjectInputStream(filein);
			    int objectCount = in.readInt();
			    for (int i=0; i<objectCount; i++){
			    	inputCounterList.add((Counter) in.readObject());
			    }
			    filein.close();
			    in.close();
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		Collections.sort(inputCounterList, new Comparator<Counter>(){
			@Override public int compare(Counter c1, Counter c2){
				return c2.getCounterValue() - c1.getCounterValue();
			}
		});
		
		return inputCounterList;
		
	}
}
