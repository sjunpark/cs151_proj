

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		this.add(agenda);
		
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
			GregorianCalendar temp = cal;
			cal= new GregorianCalendar();
			mc.getCalendarController().changeDate(temp);
		}
		else if(e.getSource() == day) {
			GregorianCalendar temp = cal;
			mc.getCalendarController().changeView(temp, 1);
			setColor(day);
		}
		else if(e.getSource() == week) {
			GregorianCalendar temp = cal;
			mc.getCalendarController().changeView(temp, 2);
			setColor(week);
		}
		else if(e.getSource() == month) {
			GregorianCalendar temp = cal;
			mc.getCalendarController().changeView(temp, 3);
			setColor(month);
		}
		else if(e.getSource() == agenda) {
			new Agenda().setVisible(true);;
			setColor(agenda);
		}
	}
	
	public void setColor(JButton b) {
		day.setBackground(Color.WHITE);
		week.setBackground(Color.WHITE);
		month.setBackground(Color.WHITE);
		agenda.setBackground(Color.WHITE);
		
		b.setBackground(Color.GRAY);
	}
	
	private class Agenda extends JDialog {
		public Agenda() {
			JPanel panel = new JPanel();
			JLabel fromLabel = new JLabel("From");
			JLabel toLabel = new JLabel("to");
			JTextField fromText = new JTextField(8);
			JTextField toText = new JTextField(8);
			JButton btn = new JButton("save");
			panel.add(fromLabel);
			panel.add(fromText);
			panel.add(toLabel);
			panel.add(toText);
			panel.add(btn);
			
			btn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String f = fromText.getText();
					String t = toText.getText();
					SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
					Date from = new Date();
					Date to = new Date();
					try {
						from = format.parse(f);
						to = format.parse(t);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					mc.getCalendarController().agendaView(from, to);
					setVisible(false);
				}
			});
			
			
			this.add(panel);
			pack();
			setModal(true);
		}
	}
}
