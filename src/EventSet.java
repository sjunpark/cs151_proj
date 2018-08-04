

import java.util.ArrayList;
import java.util.TreeSet;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * EventSet class is a TreeSet of events
 * @author Seongjun Park, Abdullahfaisala Alseddiq, Yan Chen
 * 		   Team Luck 7
 */
public class EventSet extends TreeSet<Event> {
	private ArrayList<ChangeListener> listeners;
	/**
	 * constructor
	 */
	public EventSet() {
		listeners = new ArrayList<ChangeListener>();
	}
	/**
	 * Adds a change listener to the EventSet.
     * @param l the change listener to add
	 */
	public void attach(ChangeListener l) {
		listeners.add(l);
	}
	/**
	 * Adds a Event to the EventSet
	 * @param e the Event to add
	 */
	public void addEvent(Event e) {
		this.add(e);
		ChangeEvent event = new ChangeEvent(this);
		for(ChangeListener listener : listeners){
			listener.stateChanged(event);
		}
	}
	/**
	 * @return TreeSet<Event>
	 */
	public TreeSet<Event> getEvents() {
		return this;
	}

}
