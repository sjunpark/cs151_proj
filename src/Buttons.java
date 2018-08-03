

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


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
			mc.changeButtonColors(day);
		}
		else if(e.getSource() == week) {
			mc.getCalendarController().changeView(cal, 2);
			mc.changeButtonColors(week);
		}
		else if(e.getSource() == month) {
			GregorianCalendar temp = cal;
			mc.getCalendarController().changeView(temp, 3);
			mc.changeButtonColors(month);
		}
		else if(e.getSource() == agenda) {
			new Agenda(mc).setVisible(true);
			mc.changeButtonColors(agenda);
		}
		else if(e.getSource() == fromFile) {
			try {
				new FromFile(mc.getEventSet());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void setColor(JButton b) {
		day.setBackground(Color.WHITE);
		week.setBackground(Color.WHITE);
		month.setBackground(Color.WHITE);
		agenda.setBackground(Color.WHITE);
		
		b.setBackground(Color.GRAY);
		
		day.repaint();
		week.repaint();
	}
	
	

}
