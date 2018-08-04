

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Button class to display buttons and for a controller
 * @author Seongjun Park, Abdullahfaisala Alseddiq, Yan Chen
 * 		   Team Luck 7
 */
public class Buttons extends JPanel implements ActionListener {
	private JButton 			previousDayBtn;
	private JButton				nextDayBtn;
	private JButton				createBtn;
	private JButton 			quitBtn;	
	private JButton				today;
	private JButton				day;
	private JButton				week;
	private JButton				month;
	private JButton				agenda;
	private JButton				fromFile;
	private GregorianCalendar 	cal;
	private MyCalendar			mc;
	
	/**
	 * Constructor of Buttons. Initialize each buttons and add action listener
	 * @param myc to get reference of EventSet and Calendar
	 */
	public Buttons(MyCalendar myc) {		
		this.mc = myc;
		cal = mc.getCalendar();
		
		today = new JButton("Today");
		previousDayBtn = new JButton("<");
		nextDayBtn = new JButton(">");
		createBtn = new JButton("CREATE");
		quitBtn = new JButton("QUIT");
		day = new JButton("Day");
		week = new JButton("Week");
		month = new JButton("Month");
		agenda = new JButton("Agenda");
		fromFile = new JButton("From File");

		today.addActionListener(this);
		previousDayBtn.addActionListener(this);
		nextDayBtn.addActionListener(this);
		createBtn.addActionListener(this);
		quitBtn.addActionListener(this);
		day.addActionListener(this);
		week.addActionListener(this);
		month.addActionListener(this);
		agenda.addActionListener(this);
		fromFile.addActionListener(this);
		createBtn.setForeground(Color.RED);
//		createBtn.setOpaque(true);
		
		day.setBackground(Color.GRAY);
		week.setBackground(Color.GRAY);
		month.setBackground(Color.GRAY);
		agenda.setBackground(Color.GRAY);
		
		this.setPreferredSize(new Dimension(10,70));
		
		this.add(today);
		this.add(previousDayBtn);
		this.add(nextDayBtn);
		this.add(createBtn);
		this.add(quitBtn);
		this.add(day);
		this.add(week);
		this.add(month);
		this.add(agenda);
		this.add(fromFile);
	}
	
	/**
	 * Controller of the project. 
	 * This method is called by clicking each button.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==previousDayBtn){
			cal.add(Calendar.DATE,-1);
			mc.getCalendarController().changeDate(cal);
		}
		else if(e.getSource()==nextDayBtn){
			cal.add(Calendar.DATE,1);
			mc.getCalendarController().changeDate(cal);
		}
		else if(e.getSource()==createBtn){
			new Create(mc.getEventSet(), mc.getCalendar()).setVisible(true);
		}
		else if(e.getSource() == quitBtn)
			mc.closeProgram();
		else if(e.getSource() == today){
			cal= new GregorianCalendar();
			mc.getCalendarController().changeDate(cal);
		}
		else if(e.getSource() == day) {
			mc.getCalendarController().changeView(cal, 1);
			setColor(day);
		}
		else if(e.getSource() == week) {
			mc.getCalendarController().changeView(cal, 2);
			setColor(week);
		}
		else if(e.getSource() == month) {
			GregorianCalendar temp = cal;
			mc.getCalendarController().changeView(temp, 3);
			setColor(month);
		}
		else if(e.getSource() == agenda) {
			new Agenda(mc).setVisible(true);
			setColor(agenda);
		}
		else if(e.getSource() == fromFile) {
			try {
				new FromFile(mc.getEventSet());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * Change a color of a background of buttons
	 */
	public void setColor(JButton b) {
		day.setOpaque(false);
		week.setOpaque(false);
		month.setOpaque(false);
		agenda.setOpaque(false);
		
		b.setOpaque(true);
		this.repaint();
	}
}
