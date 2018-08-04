

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/**
 * SelectedView class to display events daily, weekly, monthly, and agenda
 * @author Seongjun Park, Abdullahfaisala Alseddiq, Yan Chen
 * 		   Team Luck 7
 */
public 	class SelectedView extends JPanel implements CalendarLayout{
	private JTextArea		  textArea;
	private JLabel			  date;
	private GregorianCalendar cal;
	private EventSet		  events;
	private int 			  selection;
    private int[] lastDates ={31,28,31,30,31,30,31,31,30,31,30,31};
    private Date			  from;
    private Date			  to;
	
    /**
     * Constructor of SelectedView
     * @param c is current calendar
     * @param e is EventSet of the program
     */
	public SelectedView(Calendar c, EventSet e) {
		cal = (GregorianCalendar) c;
		events = e;
		selection = 1;
		from = new Date();
		to = new Date();
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(300,200));
		int d = cal.get(Calendar.MONTH) + 1;
		date = new JLabel(Integer.toString(d)+" / "+cal.get(Calendar.DATE));
		this.add(date,BorderLayout.NORTH);
		textArea = new JTextArea();
		printCalendar(cal);
		
	    JScrollPane vertical = new JScrollPane(textArea);
	    vertical.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(vertical);

	}
	
	/**
	 * call prints methods depending on what a user chose previously
	 * @param c current date
	 */
	public void printView(GregorianCalendar c) {
		if (selection == 1)
			printCalendar(c);
		else if (selection == 2) 
			printWeekView(c);
		else if (selection == 3)
			printMonthView(c);
		else if (selection == 4)
			printAgendaView(from,to,c);
	}
	
	/**
	 * takes a user input to display views and calls methods
	 * @param c
	 * @param n
	 */
	public void printView(GregorianCalendar c, int n) {
		selection = n;
		if (selection == 1)
			printCalendar(c);
		else if (selection == 2) 
			printWeekView(c);
		else if (selection == 3)
			printMonthView(c);
		else if (selection == 4)
			printAgendaView(from,to,c);
	}
	
	/**
	 * Overridden method from CalendarLayout.
	 * displays daily view which is default view of the program
	 */
	@Override
	public void printCalendar(GregorianCalendar c) {
		date.setText(c.get(Calendar.MONTH)+1+" / "+c.get(Calendar.DATE));
		Calendar temp = new GregorianCalendar();
		String str = "";
		boolean changed = false;
		
		for(Event e : events) {
			temp.setTime(e.getDate());
			if(temp.get(Calendar.YEAR)==c.get(Calendar.YEAR))
				if(temp.get(Calendar.MONTH)==c.get(Calendar.MONTH))
					if(temp.get(Calendar.DATE)==c.get(Calendar.DATE)){
						str += e.toString()+"\n";
						changed = true;
					}
		}
		if(!changed)
			textArea.setText("");
		else
			textArea.setText(str);
	}
	
	/**
	 * Prints events on current's date's week
	 * @param c stores a current date
	 */
	public void printWeekView(GregorianCalendar c) {
		date.setText(c.get(Calendar.MONTH)+1+" / "+c.get(Calendar.DATE));
		int d = c.get(Calendar.DAY_OF_WEEK)-1;

		GregorianCalendar temp = new GregorianCalendar();
		String str = "";
		boolean changed = false;
		
		for(Event e : events) {
			temp.setTime(e.getDate());
			for(int i = 0; i<7; i++ ){
				GregorianCalendar c2 = new GregorianCalendar();
				int curDate = c.get(Calendar.DATE) - d + i;

				if(curDate > lastDates[c.get(Calendar.MONTH)])
					c2.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH)+1,curDate -lastDates[c.get(Calendar.MONTH)]);
				else if(curDate < 1)
					c2.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH)-1,curDate + lastDates[c.get(Calendar.MONTH)-1]);
				else
					c2.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),curDate);
				if(temp.get(Calendar.YEAR)==c2.get(Calendar.YEAR))
					if(temp.get(Calendar.MONTH)==c2.get(Calendar.MONTH)){
						if(temp.get(Calendar.DATE)==c2.get(Calendar.DATE)){
							str += e.toString()+"\n";
							changed = true;
						}
					}
			}
		}
		if(!changed)
			textArea.setText("");
		else
			textArea.setText(str);
	}
	
	/**
	 * Prints events on current's date's month
	 * @param c stores a current date
	 */
	public void printMonthView(GregorianCalendar c) {
		date.setText(c.get(Calendar.MONTH)+1+" / "+c.get(Calendar.DATE));
		Calendar temp = new GregorianCalendar();
		String str = "";
		boolean changed = false;
		
		for(Event e : events) {
			temp.setTime(e.getDate());
			if(temp.get(Calendar.YEAR)==c.get(Calendar.YEAR))
				if(temp.get(Calendar.MONTH)==c.get(Calendar.MONTH)){
					str += e.toString()+"\n";
					changed = true;
				}
		}
		if(!changed)
			textArea.setText("");
		else
			textArea.setText(str);
	}
	
	/**
	 * print all events in a range that a user determines
	 * @param f begining date of agenda
	 * @param t end date of agenda
	 */
	public void printAgendaView(Date f, Date t, GregorianCalendar c) {
		date.setText(c.get(Calendar.MONTH)+1+" / "+c.get(Calendar.DATE));
		from = f;
		to = t;
		
		selection = 4;
		GregorianCalendar fromCal = new GregorianCalendar();
		GregorianCalendar toCal = new GregorianCalendar();
		fromCal.setTime(f);
		toCal.setTime(t);
//		date.setText(f.getTime() + " - " t.getTime());
		
		Calendar temp = new GregorianCalendar();
		String str = "";
		boolean changed = false;
		
		for(Event e : events) {
			temp.setTime(e.getDate());
			if(temp.get(Calendar.YEAR)>= fromCal.get(Calendar.YEAR) && temp.get(Calendar.YEAR)<= toCal.get(Calendar.YEAR))
				if(temp.get(Calendar.MONTH)>= fromCal.get(Calendar.MONTH) && temp.get(Calendar.MONTH)<= toCal.get(Calendar.MONTH))
					if(temp.get(Calendar.MONTH)== fromCal.get(Calendar.MONTH) && temp.get(Calendar.DATE)< fromCal.get(Calendar.DATE))
						continue;
					else if(temp.get(Calendar.MONTH) == toCal.get(Calendar.MONTH) && temp.get(Calendar.DATE) > toCal.get(Calendar.DATE))
						continue;
					else {
						str += e.toString()+"\n";
						changed = true;
					}
		}
		if(!changed)
			textArea.setText("");
		else
			textArea.setText(str);
	}
}