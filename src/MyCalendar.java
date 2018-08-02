

import java.awt.BorderLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class MyCalendar extends JFrame {
	private EventSet			eventSet;
	private CalendarController	calControl;
	private GregorianCalendar 	cal;
	private Buttons				btns;
//	private JPanel btnPanel;

	
	public MyCalendar() {
		cal = new GregorianCalendar();
		eventSet = new EventSet();
		calControl = new CalendarController(this);
		btns = new Buttons(this);
		ChangeListener l = new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				calControl.modify();
			}
		};
		eventSet.attach(l);
		
//		eventSet.addEvent(new Event("1/1/2018","5:00","12:00","Homework for CS 151"));
//		eventSet.addEvent(new Event("1/1/2019","5:00","12:00","take a nap"));
//		eventSet.addEvent(new Event("1/2/2018","5:00","12:00","Homewor for CHAD 102"));	
//		eventSet.addEvent(new Event("6/23/2018","3:00","12:00","Cook for friends"));
//		eventSet.addEvent(new Event("6/24/2018","3:00","12:00","Go to the church"));
//		eventSet.addEvent(new Event("6/23/2018","6:00","12:00","hahahahahahahaha"));
//		eventSet.addEvent(new Event("7/21/2018","4:00","12:20","good"));
//		eventSet.addEvent(new Event("7/21/2018","20:00","12:20","fine"));
//		eventSet.addEvent(new Event("7/22/2018","4:00","12:20","bad"));

		
		
		loadEvents();

		print();
	}
	
	public void print() {
		this.add(calControl, BorderLayout.CENTER);
		this.add(btns, BorderLayout.NORTH);
		
		this.pack();
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public boolean checkFile() {
		File f = new File("events.txt");
		if(!f.exists() && !f.isDirectory()){
			try {
			System.out.println("This is your first run");
			System.out.println("File has been created");
			f.createNewFile();
			return false;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public void loadEvents() {
		if(!checkFile())
			return;
		try {
			FileInputStream fs = new FileInputStream("events.txt");
			ObjectInputStream os = new ObjectInputStream(fs);
			TreeSet<Event> set = (TreeSet<Event>)os.readObject();
			for(Event e :set)
				eventSet.add(e);

			os.close();
			fs.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void saveEvents() {
		try {
			FileOutputStream fs = new FileOutputStream("events.txt");
			ObjectOutputStream os = new ObjectOutputStream(fs);
			TreeSet<Event> set = new TreeSet();
			for(Event e : eventSet)
				set.add(e);
			os.writeObject(set);
			
			os.close();
			fs.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.dispose();
	}
	
	public void changeDate(GregorianCalendar c) {
		calControl.changeDate(c);
	}
	
	public EventSet getEventSet() {
		return this.eventSet;
	}
	
	public GregorianCalendar getCalendar() {
		return this.cal;
	}
	
	public CalendarController getCalendarController() {
		return this.calControl;
	}
	public void changeButtonColors (JButton b) {
		btns.setColor(b);
		btns.repaint();
	}
	public void closeProgram() {
		saveEvents();
		this.setVisible(false);
	}
	
}