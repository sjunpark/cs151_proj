

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.Date;
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
    private int[] lastDates ={31,29,31,30,31,30,31,31,30,31,30,31};
	
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
//		else if (selection == 4)
//			printAgendaView(c);
	}
	
	public void printView(GregorianCalendar c, int n) {
		selection = n;
		if (selection == 1)
			printDayView(c);
		else if (selection == 2) 
			printWeekView(c);
		else if (selection == 3)
			printMonthView(c);
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
		GregorianCalendar c2 = new GregorianCalendar(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DATE));
//		c2.setTime(c.getInstance().getTime());//cal 이 계속 
		System.out.println("Cur mon "+c2.get(Calendar.MONTH));
		System.out.println("Cur day" +c2.get(Calendar.DATE));
		int d = c.get(Calendar.DAY_OF_WEEK)-1;
		System.out.println("d: "+d);

		date.setText(c.get(Calendar.MONTH)+1+" / "+(c.get(Calendar.DATE)-d) + " ~ " 
					+ c.get(Calendar.MONTH)+1+" / "+(c.get(Calendar.DATE)-d+7));
		Calendar temp = new GregorianCalendar();
		String str = "";
		boolean changed = false;
		
		for(Event e : events) {
			temp.setTime(e.getDate());
			for(int i = 0; i<7; i++ ){
				if(c2.get(Calendar.DATE) - d + i > lastDates[c2.get(Calendar.MONTH)]){
//					c2.add(Calendar.MONTH, 1);
					c2.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH)+1,1);
					System.out.println("here " + c2.get(Calendar.MONTH));
				}
				if(c2.get(Calendar.DATE) - d + i < 1)
					c2.add(Calendar.MONTH, -1);
				if(temp.get(Calendar.YEAR)==c2.get(Calendar.YEAR))
					if(temp.get(Calendar.MONTH)==c2.get(Calendar.MONTH)){
						if(temp.get(Calendar.DATE)==c2.get(Calendar.DATE)-d+i){
							System.out.println("here");
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
	
	public void printAgendaView(Date f, Date t) {
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
					if(temp.get(Calendar.DATE)>= fromCal.get(Calendar.DATE) && temp.get(Calendar.DATE)<= toCal.get(Calendar.DATE)) {
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