package com.example.multiclicker;

public class Counter {
	
	protected String counterName;
    protected int counterValue;
 
    // Constructor
    public Counter(String counterName, int counterValue) {
        this.counterName = counterName;
        this.counterValue= counterValue;
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

}
