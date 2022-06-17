package application;

public class Event {
	
	private String name;
	private String desc;
	private String classif;
	private String locate;
	private String month;
	private String day;
	private String year;
	private String time;
	private String AMPM;
	private int ID;
	
	//Constructor for events
	//	- Initializes instance fields
	//		- name, desc, classif, locate, month, day, year, time, AMPM
	public Event(
			String theName,
			String theDesc,
			String theClassif,
			String theLocate,
			String theMonth,
			String theDay,
			String theYear,
			String theTime,
			String theAMPM,
			int theID){
		
		name = theName;
		desc  = theDesc;
		classif = theClassif;
		locate = theLocate;
		month = theMonth;
		day = theDay;
		year = theYear;
		time = theTime;
		AMPM = theAMPM;
		ID = theID;
		
	}
	
	
	
	
	//Getters for all events attributes
	
	public String getName() {
		return name;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public String getClassif() {
		return classif;
	}
	
	public String getLocate() {
		return locate;
	}
	
	public String getMonth() {
		return month;
	}
	
	public String getDay() {
		return day;
	}
	
	public String getYear() {
		return year;
	}
	
	public String getTime() {
		return time;
	}
	
	public String getAMPM() {
		return AMPM;
	}
	
	public int getID() {
		return ID;
	}

	//Setter for classification
	public void setClassif(String input) {
		classif = input;
	}
	
}