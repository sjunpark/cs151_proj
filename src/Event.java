

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event implements Comparable<Event>, Serializable {
	private Date 				date;
	private String				stringDate;
	private String				startTime;
	private String				endTime;
	private String 				title;
	
	public Event(String d, String st, String et, String t) {
		try {
			date =  new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(d+" "+st);
		} catch (ParseException e) {
			System.out.println("You typed it with wrong format");
			e.printStackTrace();
		}
		this.stringDate = d;
		this.startTime = st;
		this.endTime = et;
		this.title = t;
	}
	
	public Event(Date d, String t, String et) {
		this.date = d;
		this.endTime = et;
		this.title = t;
	}
	public Date getDate() {
		return this.date;
	}
	public String getStringDate() {
		return this.stringDate;
	}
	public String getStartTime() {
		return this.startTime;
	}
	
	public String getEndTime() {
		return this.endTime;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	@Override
	public int compareTo(Event o) {
		if(this.date.compareTo(o.date)>0)
			return 1;
		else if (this.date.compareTo(o.date)==0)
			return 0;
		else
			return -1;
	}
	
	public String toString() {
		SimpleDateFormat dFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		return " " +dFormat.format(date) + " - " + endTime + " "+ title;
	}

}
