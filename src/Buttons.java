

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JPanel;


public class Buttons extends JPanel implements ActionListener {
	private JButton 			previousMonthBtn;
	private JButton				nextMonthBtn;
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
	
	public Buttons(MyCalendar myc) {		
		this.mc = myc;
		cal = mc.getCalendar();
		
		today = new JButton("Today");
		previousMonthBtn = new JButton("<");
		nextMonthBtn = new JButton(">");
		createBtn = new JButton("CREATE");
		quitBtn = new JButton("QUIT");
		day = new JButton("Day");
		week = new JButton("Week");
		month = new JButton("Month");
		agenda = new JButton("Agenda");
		fromFile = new JButton("From File");

		today.addActionListener(this);
		previousMonthBtn.addActionListener(this);
		nextMonthBtn.addActionListener(this);
		createBtn.addActionListener(this);
		quitBtn.addActionListener(this);
		day.addActionListener(this);
		week.addActionListener(this);
		month.addActionListener(this);
		agenda.addActionListener(this);
		fromFile.addActionListener(this);
		
		this.add(today);
		this.add(previousMonthBtn);
		this.add(nextMonthBtn);
		this.add(createBtn);
		this.add(quitBtn);
		this.add(day);
		this.add(week);
		this.add(month);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==previousMonthBtn){
			GregorianCalendar temp = cal;
			temp.add(Calendar.DATE,-1);
			mc.getCalendarController().changeDate(temp);
		}
		else if(e.getSource()==nextMonthBtn){
			GregorianCalendar temp = cal;
			temp.add(Calendar.DATE,1);
			mc.getCalendarController().changeDate(temp);
		}
		else if(e.getSource()==createBtn){
			Create c = new Create(mc.getEventSet(), mc.getCalendar());
			c.setVisible(true);
		}
		else if(e.getSource() == quitBtn)
			mc.closeProgram();
		else if(e.getSource() == today){
			cal= new GregorianCalendar();
			mc.getCalendarController().changeDate(cal);
		}
		else if(e.getSource() == day) {
			GregorianCalendar temp = cal;
			mc.getCalendarController().changeView(temp, 1);
		}
		else if(e.getSource() == week) {
			GregorianCalendar temp = cal;
			mc.getCalendarController().changeView(temp, 2);
		}
		else if(e.getSource() == month) {
			GregorianCalendar temp = cal;
			mc.getCalendarController().changeView(temp, 3);
		}
	}
}
