/**
 * Event class that store event's date, time, and title
 * @author Seongjun Park, Abdullahfaisala Alseddiq, Yan Chen
 * 		   Team Luck 7
 */

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
	/**
	 * Constructor of Event. Convert String date to Date
	 * @param d is a date of an event
	 * @param st is a start time of an event
	 * @param et is a end time of an event
	 * @param t is a title of an event
	 */
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
	/**
	 * Constructor of Event.
	 * @param d is a date of an event
	 * @param t is a title of an event
	 * @param et is a end time of an event 
	 */
	public Event(Date d, String t, String et) {
		this.date = d;
		this.endTime = et;
		this.title = t;
	}
	/**
	 * getter of event's date
	 * @return date of an event
	 */
	public Date getDate() {
		return this.date;
	}
	/**
	 * getter of event's date in string data type
	 * @return date of an event
	 */
	public String getStringDate() {
		return this.stringDate;
	}
	/**
	 * getter of event's date's start time
	 * @return date of an event
	 */
	public String getStartTime() {
		return this.startTime;
	}

	/**
	 * Compare each event.
	 */
	@Override
	public int compareTo(Event o) {
		if(this.date.compareTo(o.date)>0)
			return 1;
		else if (this.date.compareTo(o.date)==0)
			return 0;
		else
			return -1;
	}
	/**
	 * convert a event to the string with certain format
	 * @return this string
	 */
	public String toString() {
		SimpleDateFormat dFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		return " " +dFormat.format(date) + " - " + endTime + " "+ title;
	}

}
