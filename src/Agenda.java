
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Agenda class to pop-up a new window to get user's input
 * @author Seongjun Park, Abdullahfaisala Alseddiq, Yan Chen
 * 		   Team Luck 7
 */
public class Agenda extends JDialog {
	private MyCalendar mc;
	
	/**
	 * Constructor of Agenda.
	 * Creates a new window, and gets user input for agenda
	 * Saves user input and call a CalendarController's method to change SelectedView
	 * @param myCalendar
	 */
	public Agenda(MyCalendar myCalendar) {
		mc = myCalendar;
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
				mc.getCalendarController().agendaView(from, to, mc.getCalendar());
				setVisible(false);
			}
		});
		
		
		this.add(panel);
		pack();
		setModal(true);
	}
}	