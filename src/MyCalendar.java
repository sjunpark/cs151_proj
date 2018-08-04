

import java.awt.BorderLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.GregorianCalendar;
import java.util.TreeSet;

import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * MyCalendar class is a frame of all views.
 * @author Seongjun Park, Abdullahfaisala Alseddiq, Yan Chen
 * 		   Team Luck 7
 */
public class MyCalendar extends JFrame {
	private EventSet			eventSet;
	private CalendarController	calControl;
	private GregorianCalendar 	cal;
	private Buttons				btns;
	
	/**
	 * Constructor of MyCalendar.
	 */
	public MyCalendar() {
		cal = new GregorianCalendar();
		eventSet = new EventSet();
		loadEvents();
		calControl = new CalendarController(this);
		btns = new Buttons(this);
		ChangeListener l = new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				calControl.modify();
			}
		};
		eventSet.attach(l);
		
		print();
	}
	
	/**
	 * add panels to MyCalendar
	 */
	public void print() {
		this.add(calControl, BorderLayout.CENTER);
		this.add(btns, BorderLayout.NORTH);
		
		this.pack();
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	/**
	 * check a file whether it exists or not
	 * @return true if a file is existed
	 * 		   false if a file is not existed
	 */
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
	
	/**
	 * load events from a file when a program is started
	 */
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
	
	/**
	 * save all events in EvenSet when program is closed
	 */
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
	

//	public void changeDate(GregorianCalendar c) {
//		calControl.changeDate(c);
//	}
	/**
	 * Getter of EventSet
	 * @return EventSet
	 */
	public EventSet getEventSet() {
		return this.eventSet;
	}
	/**
	 * Getter of GregorianCalendar
	 * @return GregorianCalendar
	 */
	public GregorianCalendar getCalendar() {
		return this.cal;
	}
	
	/**
	 * Getter of CalendarController
	 * @return CalendarController
	 */
	public CalendarController getCalendarController() {
		return this.calControl;
	}

	/**
	 * calls a saveEvents and close MyCalendar
	 */
	public void closeProgram() {
		saveEvents();
		this.setVisible(false);
	}
	
}