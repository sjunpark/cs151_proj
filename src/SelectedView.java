

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public 	class SelectedView extends JPanel {
	private JTextArea		  textArea;
	private JLabel			  date;
	private GregorianCalendar cal;
	private EventSet		  events;
	
	public SelectedView(Calendar c, EventSet e) {
		cal = (GregorianCalendar) c;
		events = e;
		
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(300,200));
		int d = cal.get(Calendar.MONTH) + 1;
		date = new JLabel(Integer.toString(d)+" / "+cal.get(Calendar.DATE));
		this.add(date,BorderLayout.NORTH);
		textArea = new JTextArea();
		printDayView(cal);
		this.add(textArea);
	}
	
	public void printDayView(GregorianCalendar c) {
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
	
	public void printWeekView(GregorianCalendar c) {
		int i = c.get(Calendar.DAY_OF_WEEK);
		
	}
	
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
	
	public void printAgendaView(GregorianCalendar c) {
		
	}
}