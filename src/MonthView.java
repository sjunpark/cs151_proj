


import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 * MonthView class is implementing CalendarLayout and display a calendar
 * @author Seongjun Park, Abdullahfaisala Alseddiq, Yan Chen
 * 		   Team Luck 7
 */
enum MONTHS
{
	Jan, Feb, March, April, May, June, July, Aug, Sep, Oct, Novr, Dec;
}
enum DAYS
{
	Sun, Mon, Tue, Wed, Thr, Fri, Sat ;
}


public class MonthView extends JPanel implements MouseListener, CalendarLayout {
	public final static MONTHS[] arrayOfMonths = MONTHS.values();
    public final static DAYS[] arrayOfDays = DAYS.values();
    private int[] lastDates ={31,28,31,30,31,30,31,31,30,31,30,31};
	private GregorianCalendar cal;
	private EventSet		  events;
	private ArrayList<JLabel> dates;
	private JPanel 			  calPanel;
	private JLabel	 		  preMonth;
	private JLabel	 		  nextMonth;
	private MyCalendar		  mc;
	
	/**
	 * Constructor of MonthView.
	 * @param myc to get reference of EventSet and GregorianCalendar
	 */
	public MonthView(MyCalendar myc) {
		this.mc = myc;
		events = mc.getEventSet();

		cal = mc.getCalendar();
		this.setLayout(new BorderLayout());
		
		dates = new ArrayList<JLabel>();
		
		printCalendar(cal);

	}
	
	/**
	 * Overrides a method from CalendarLayout.
	 * displays a monthly calendar and indicates a day where user is looking at.
	 */
	@Override
	public void printCalendar(GregorianCalendar c) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JPanel northPanel = new JPanel();
		JLabel monthAndYear = new JLabel(arrayOfMonths[c.get(Calendar.MONTH)].toString()+ " " + Integer.toString(c.get(Calendar.YEAR)));
		preMonth = new JLabel("<");
		nextMonth = new JLabel(">");
		
		preMonth.addMouseListener(this);
		nextMonth.addMouseListener(this);
		northPanel.add(monthAndYear);
		northPanel.add(preMonth);
		northPanel.add(nextMonth);
		panel.add(northPanel, BorderLayout.NORTH);

		calPanel = new JPanel();
		calPanel.setLayout(new GridLayout(7,7));
		calPanel.setMinimumSize(new Dimension(400,400));
		
		for(int i = 0; i <7; i++) {
			JLabel label = new JLabel(arrayOfDays[i]+"");
			calPanel.add(label);
		}
		
		for(JLabel l: dates)
			l.removeMouseListener(this);
		
		GregorianCalendar temp = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
		int startDate = temp.get(Calendar.DAY_OF_WEEK);
		int numOfDays = lastDates[c.get(Calendar.MONTH)];
		for(int i =1; i<=42; i++) {
			JLabel date = new JLabel();
			if(i<startDate) {
				temp.add(Calendar.MONTH, -1);
				int preNumOfDays = lastDates[c.get(Calendar.MONTH)];
				date.setText(Integer.toString(preNumOfDays+i-startDate+1));
				date.setForeground(Color.GRAY);
			}
			else if(startDate<=i&&i<=numOfDays+startDate-1) {
				date.setText((Integer.toString(i-startDate+1)));
				date.addMouseListener(this);
				dates.add(date);
			}
			else{
				date.setText(Integer.toString(i-numOfDays-startDate+1));
				date.setForeground(Color.GRAY);
			}
			if(i-startDate+1 == c.get(Calendar.DATE))
				date.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
			calPanel.add(date);
		}
		panel.add(calPanel, BorderLayout.CENTER);
		this.add(panel,BorderLayout.WEST);
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {}
	/**
	 * When a user presses a date in the calendar, SelectedView would be changed to different date that I pressed. 
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		JLabel d = (JLabel) e.getSource();
		if(d.getText().equals(preMonth.getText())) {
			GregorianCalendar temp = cal;
			temp.add(Calendar.MONTH,-1);
			mc.getCalendarController().changeDate(temp);
		}
		else if (d.getText().equals(nextMonth.getText())) {
			GregorianCalendar temp = cal;
			temp.add(Calendar.MONTH,1);
			mc.getCalendarController().changeDate(temp);
		}
		else {
			GregorianCalendar temp = cal;
			int date = Integer.valueOf(d.getText());
			temp.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), date);
			mc.getCalendarController().changeDate(temp);
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}

