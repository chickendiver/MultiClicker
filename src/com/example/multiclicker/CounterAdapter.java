package com.example.multiclicker;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

// Based largely on article by Pete Houston, accessed on Jan. 21st at:
// http://xjaphx.wordpress.com/2011/06/11/create-a-simple-phone-book/
public class CounterAdapter extends BaseAdapter implements OnClickListener{
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
	public View getView(int pos, View convertView, ViewGroup parent) {
		// Use the proper counter
		Counter counterInstance = counterList.get(pos);
		
		// Inflate list view layout if null (copied exactly)
		if(convertView == null){
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.counter_layout, null);
		}
		
		// set counter's button
		Button counterbutton = (Button) convertView.findViewById(R.id.button_text);
		counterbutton.setOnClickListener(this);

		
		// set counter's textView
		TextView counterCount = (TextView)convertView.findViewById(R.id.text_view_1);
		counterCount.setText(counterInstance.getCounterValue());
		
		return convertView;
	}
	@Override
	public void onClick(View v) {
		//TODO Figure out if this is the right place for button clicks
	}
}
