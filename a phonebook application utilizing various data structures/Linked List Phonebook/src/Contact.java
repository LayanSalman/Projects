
public class Contact implements Comparable<Contact> {

	public String name;
	public String phonenumber;
	public String emailaddress;
	public String address;
	public String birthday;
	public String notes;
	public LinkedList<Event> events;
	
	

	public Contact() {
		name = "";
		phonenumber = "";
		emailaddress = "";
		address = "";
		birthday = "";
		notes = "";
		events = new LinkedList<Event>();

	}

	public Contact(String name, String phonenumber, String emailaddress, String address, String birthday, String notes) {
		this.name = name;
		this.phonenumber = phonenumber;
		this.emailaddress = emailaddress;
		this.address = address;
		this.birthday = birthday;
		this.notes = notes;
		this.events = new LinkedList<Event>();

	}

	public boolean addEvent(Event e ){ 
		
		if(events.empty()) {
			events.add(e);
		return true;}

		if(events.search(e) != null) {
			System.out.println("Event already exist!");
			return false; }
		
			
		else {
			events.findFirst();
			while(!events.last()){
				if(events.retrieve().date.equals(e.date) && events.retrieve().time.equals(e.time)) {
					return false; }
				else
					events.findNext();
			}
			if(events.retrieve().date.equals(e.date) && events.retrieve().time.equals(e.time)) {
				return false; }
			else
				events.findNext();

			events.add(e);
			return true; 
		}
	}
	
	

	@Override
	public String toString() {
		return      "\nName: " + name +
				"\nPhone Number: " + phonenumber +
				"\nEmail Address: " + emailaddress +
				"\nAddress: " +  address +
				"\nBirthday: " + birthday +
				"\nNotes: " + notes + "\n";


	}

	@Override
	public int compareTo(Contact contact) {
		return this.name.compareTo(contact.name);
	}

}
