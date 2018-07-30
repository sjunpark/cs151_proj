

import java.util.ArrayList;
import java.util.TreeSet;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EventSet extends TreeSet<Event> {
	private ArrayList<ChangeListener> listeners;
	
	public EventSet() {
		listeners = new ArrayList<ChangeListener>();
	}
	
	public void attach(ChangeListener l) {
		listeners.add(l);
	}
	
	public void addEvent(Event e) {
		this.add(e);
		ChangeEvent event = new ChangeEvent(this);
		for(ChangeListener listener : listeners){
			listener.stateChanged(event);
		}
	}
	
	public TreeSet<Event> getEvents() {
		return this;
	}

}
