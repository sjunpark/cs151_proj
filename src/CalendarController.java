

import java.awt.BorderLayout;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JPanel;
/**
 * CalendarController class that control SelectedView and SelectedView
 * @author Seongjun Park, Abdullahfaisala Alseddiq, Yan Chen
 * 		   Team Luck 7
 */
public class CalendarController extends JPanel{
	private SelectedView selectedView;
	private MonthView month;
	private MyCalendar mc;
	
	/**
	 * Constructor of CalendarController
	 * @param myc to get reference of EventSet and GregorianCalendar
	 */
	public CalendarController(MyCalendar myc) {
		this.mc = myc;
		month = new MonthView(mc);
		selectedView = new SelectedView(mc.getCalendar(), mc.getEventSet());
		this.setLayout(new BorderLayout());
		this.add(month,BorderLayout.WEST);
		this.add(selectedView,BorderLayout.EAST);
	}
	
	/**
	 * modifier of view part. This is called when EventSet is mutated
	 */
	public void modify() {
		selectedView.printCalendar(mc.getCalendar());
	}
	/**
	 * Whenever date of a calendar is changed by controller, this method is called
	 * @param c Calendar with a new date
	 */
	public void changeDate(GregorianCalendar c) {
		month.printCalendar(c);
		selectedView.printView(c);
	}
	/**
	 * this method is to change SelectedView 
	 * @param c reference of Calendar to display
	 * @param n choice of view by user
	 */
	public void changeView(GregorianCalendar c, int n) {
		selectedView.printView(c, n);
	}
	/**
	 * to change SelectedView to print agenda
	 * @param f starting date of agenda
	 * @param t ending date of agenda
	 */
	public void agendaView(Date f, Date t, GregorianCalendar c) {
		selectedView.printAgendaView(f, t, c);
	}	
}
