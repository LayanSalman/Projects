


public class Event implements Comparable<Event> {

	String title;
	String date;
	String time;
	String location;
	int count=0;
	LinkedList<String> contactInvo;

	public Event() {
		this.title = "";
		this.date = "";
		this.time = "";
		this.location = "";
		this.contactInvo = new LinkedList<String>(); 
	}

	public Event(String title, String date, String time, String location, String contact_name) {
		this.title = title;
		this.date = date;
		this.time = time;
		this.location = location;
		this.contactInvo = new LinkedList<String>();
		addContactName(contact_name); 
	}

	public boolean addContactName(String c) {
		String found = contactInvo.search(c);
		if(found != null) {
			System.out.println("Contact already exist!");
			return  false; }
		else {
			contactInvo.add(c);
			count++;
			return true; }
	}

	public boolean removeContact(String contact)
	{
		String name = contactInvo.remove(contact);
		if ( name != null)
			return true; 
		return false;
	}


	@Override
	public String toString() {
		String str = "\nEvent title: " + title +
				"\nContacts names: ";
		contactInvo.findFirst();
		while(!contactInvo.last()) {
			str += contactInvo.retrieve()+"\n";
			contactInvo.findNext();
		}
		str+= contactInvo.retrieve()+"\n" +
				"\nEvent date and time (MM/DD/YYYY HH:MM): " + date +"\t" + time +
				"\nEvent location: " + location ; 
		return str;
	}



	@Override
	public int compareTo(Event event) {
		return (this.title.compareTo(event.title));
	}

}
