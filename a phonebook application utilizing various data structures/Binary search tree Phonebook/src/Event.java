public class Event implements Comparable<Event> {

	String title;
	String date;
	String time;
	String location;
	int count=0;
	boolean EventType; // event = true, appointment = false; 
	LinkedList<String> contactInvo;

	public Event() {
		this.title = "";
		this.date = "";
		this.time = "";
		this.location = "";
		this.EventType = true; 
		this.contactInvo = new LinkedList<String>(); 
	}

	public Event(String title, String date, String time, String location, boolean EventType, String contact_name) {
		this.title = title;
		this.date = date;
		this.time = time;
		this.location = location;
		this.EventType = EventType; 
		this.contactInvo = new LinkedList<String>();
		addContactName(contact_name); 
	}

	public boolean addContactName (String contact) {
		if (this.EventType == true) {
			String found = contactInvo.search(contact);
			if(found != null) {
				System.out.println("Contact already exist!");
				return false; }
			else {
				contactInvo.add(contact);
				count++;
				return true;
			}
		}
		else if((this.EventType == false)&&(contactInvo.empty())){
			contactInvo.add(contact);
			count++;
			return true;
		}else {
			return false;
		}
	}

	public boolean removeContact(String contact)
	{
		String name = contactInvo.remove(contact);
		if ( name != null)
			return true; 
		return false;
	}

	public String toString() {
		String EventT = (this.EventType == true)? "Event ": "Appoinment ";     
		String str = "\n" + EventT + "title: " + title +
				"\n" + EventT + "date and time (MM/DD/YYYY HH:MM): " + date +" "+ time +
				"\n" + EventT + "location: " + location +
				"\nType: " + EventT +
				"\nContacts names:   \n" + contactInvo.toString();

		return str;
	}

	public int compareTo(Event event) {
		return (this.title.compareTo(event.title));
	}


} 


