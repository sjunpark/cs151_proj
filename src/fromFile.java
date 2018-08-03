import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FromFile {
	private String title;
	private String year;
	private String startMonth;
	private String endMonth;
	private String days;
	private String startTime;
	private String endTime;
    private int[] lastDates ={31,28,31,30,31,30,31,31,30,31,30,31};
    private EventSet events;

	public FromFile(EventSet e) throws IOException {
		events = e;
		FileReader fr = new FileReader("input.txt");
		BufferedReader br = new BufferedReader(fr);
		
		String[] date = new String[6];
		String st = br.readLine();
		while(st != null) {
			date = st.split(";");
			title = date[0];
			year= date[1];
			startMonth = date[2];
			endMonth =date[3];
			days =date[4];
			startTime = date[5];
			startTime += ":00";
			endTime =date[6];
			endTime += ":00";
			saveEvents();
			st = br.readLine();
		}	
	}
	
	public ArrayList<Integer> convertDaysToInt(String d) {
		int n = d.length();
		ArrayList<Integer> days = new ArrayList<Integer>();
		for(int i=0; i<n;i++){
			if(d.charAt(i)=='M')
				days.add(Calendar.MONDAY);
			else if(d.charAt(i) == 'T')
				days.add(Calendar.TUESDAY);
			else if(d.charAt(i) == 'W')
				days.add(Calendar.WEDNESDAY);
			else if(d.charAt(i) == 'H')
				days.add(Calendar.THURSDAY);
			else if(d.charAt(i) == 'F')
				days.add(Calendar.FRIDAY);
			else if(d.charAt(i) == 'A')
				days.add(Calendar.SATURDAY);
			else if(d.charAt(i) == 'S')
				days.add(Calendar.SUNDAY);
		}
		return days;
	}
	
	public void saveEvents() {
		GregorianCalendar cal = new GregorianCalendar(Integer.parseInt(year),Integer.parseInt(startMonth)-1,1);

		ArrayList<Integer> daysOfInt = convertDaysToInt(days);
//		for(int i : daysOfInt)
//			System.out.println(i);
		while (cal.get(Calendar.MONTH) <= Integer.parseInt(endMonth)-1 && cal.get(Calendar.YEAR)==Integer.parseInt(year)) {
			System.out.println(+cal.get(Calendar.MONTH)+" d: "+cal.get(Calendar.DATE)+" " +cal.get(Calendar.DAY_OF_WEEK));
			if(daysOfInt.contains(cal.get(Calendar.DAY_OF_WEEK))){
				try {
					Date d = new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(cal.get(Calendar.MONTH)+1+"/"
							+cal.get(Calendar.DATE)+"/"+cal.get(Calendar.YEAR)+" "+startTime);
					events.addEvent(new Event(d,title,endTime));
				System.out.println("added");
				} catch (ParseException a) {
					a.printStackTrace();
				}
			}
			cal.add(Calendar.DATE, 1);
		}
	}
}
