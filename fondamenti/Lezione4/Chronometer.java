import java.lang.*;

public class Chronometer{

	private long timestart;
	private double measure;
	private int state;                 //0:off  1:running  2:ready
	private final String[] stateString = {"off", "running", "ready"};

	public Chronometer(){
		state = 0;
	}

	public void reset(){
		measure = 0;
		state = 0;
	}

	public void start(){
		state = 1;
		timestart = System.currentTimeMillis();
	}


	public void stop(){
		measure = (System.currentTimeMillis()-timestart)/1000.0;
		state = 2;
	}

	public boolean isRunning(){
		return (state==1);
	}

	public double getElapsedTime(){
		if(state==2)
			return measure;
		return 0;
	}

	public boolean isTimeReady(){
		return (state==2);

	}

	public String toString(){
		return String.format("Chronometer %s %.4f", stateString[state], measure);

	}



}