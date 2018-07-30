

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Create extends JDialog implements ActionListener {
	private JTextField 		event;
	private JTextField 		date;
	private JTextField 		startTime;
	private JTextField 		endTime;
	private JButton			create;
	private EventSet		events;
	
	public Create(EventSet e) {
		events = e;

		event = new JTextField(20);
		date = new JTextField(15);
		startTime = new JTextField(10);
		endTime = new JTextField(10);
		create = new JButton("Create");
		
		event.setText("Title");
		date.setText("Date (MM/DD/YYYY)");
		startTime.setText("Start time(HH:MM)");
		endTime.setText("End time(HH:MM)");
		event.addActionListener(this);
		date.addActionListener(this);
		startTime.addActionListener(this);
		endTime.addActionListener(this);
		create.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.add(event);
		panel.add(date);
		panel.add(startTime);
		panel.add(endTime);
		panel.add(create);
		
		this.add(panel);
		pack();
		setModal(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==create) {
			String title = event.getText();
			String d = date.getText();
			String sTime = startTime.getText();
			String eTime = endTime.getText();
			for(Event v : events){
				if(d.equals(v.getStringDate())) {
					if(sTime.equals(v.getStartTime())){
						System.out.println("im here");
						JOptionPane optionPane = new JOptionPane("Time Conflict", JOptionPane.ERROR_MESSAGE);    
						JDialog dialog = optionPane.createDialog("Time Conflict");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);
					}
				}
			}
			events.addEvent(new Event(d,sTime,eTime,title));
			setVisible(false);
		}
	}
}
