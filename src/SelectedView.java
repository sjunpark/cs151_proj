

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
	private int 			  selection;
	
	public SelectedView(Calendar c, EventSet e) {
		cal = (GregorianCalendar) c;
		events = e;
		selection = 1;
		
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(300,200));
		int d = cal.get(Calendar.MONTH) + 1;
		date = new JLabel(Integer.toString(d)+" / "+cal.get(Calendar.DATE));
		this.add(date,BorderLayout.NORTH);
		textArea = new JTextArea();
		printDayView(cal);
		this.add(textArea);
	}
	
	public void printView(GregorianCalendar c) {
		if (selection == 1)
			printDayView(c);
		else if (selection == 2) 
			printWeekView(c);
		else if (selection == 3)
			printMonthView(c);
		else if (selection == 4)
			printAgendaView(c);
	}
	
	public void printView(GregorianCalendar c, int n) {
		selection = n;
		if (selection == 1)
			printDayView(c);
		else if (selection == 2) 
			printWeekView(c);
		else if (selection == 3)
			printMonthView(c);
		else if (selection == 4)
			printAgendaView(c);
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
		int d = c.get(Calendar.DAY_OF_WEEK)-1;
		date.setText(c.get(Calendar.MONTH)+1+" / "+(c.get(Calendar.DATE)-d) + " ~ " 
					+ c.get(Calendar.MONTH)+1+" / "+(c.get(Calendar.DATE)-d+7));
		Calendar temp = new GregorianCalendar();
		String str = "";
		boolean changed = false;
		
		for(Event e : events) {
			for(int i = 0; i<7; i++ ){
				temp.setTime(e.getDate());
				if(temp.get(Calendar.YEAR)==c.get(Calendar.YEAR))
					if(temp.get(Calendar.MONTH)==c.get(Calendar.MONTH)){
						if(temp.get(Calendar.DATE)==c.get(Calendar.DATE)-d+i){
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
	
	public void printMonthView(GregorianCalendar c) {
		date.setText(c.get(Calendar.MONTH)+1+"");
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