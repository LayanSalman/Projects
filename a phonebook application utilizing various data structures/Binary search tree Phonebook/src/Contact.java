
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

		if(!events.empty()) {
			if(e.EventType || !e.EventType && e.contactInvo.empty()){
				events.findFirst();
				while(!events.last()){
					if(events.retrieve().date.equals(e.date) && events.retrieve().time.equals(e.time)) {
						return false; }
					else
						events.findNext();
				}
				if(events.retrieve().date.equals(e.date) && events.retrieve().time.equals(e.time)) {                     
					return false; }

				events.add(e);
				return true;}
			else {
				if (!e.EventType && e.count !=0){
					return false;}

				events.add(e);
				return true; } 
		}

		events.add(e);
		return true; 

	}



	public int compareTo(Contact contact) {
		return this.name.compareTo(contact.name);
	}

	public int compareToPhone(String Phone) {
		return (this.phonenumber.compareTo(Phone));
	}

	public int compareToEmail(String emailaddress) {
		return (this.emailaddress.compareToIgnoreCase(emailaddress));
	}

	public int compareToAddress(String address) {
		return (this.address.compareToIgnoreCase(address));
	}

	public int compareToBirthday(String birthday) {
		return (this.birthday.compareTo(birthday) ) ;
	}

	public int compareFirstName(String n) {
		String [] nameParts = this.name.split(" ");
		return (nameParts[0].compareToIgnoreCase(n) ) ;
	}

	public String toString() {
		return          "\nName: " + name +
				"\nPhone Number: " + phonenumber +
				"\nEmail Address: " + emailaddress +
				"\nAddress: " +  address +
				"\nBirthday: " + birthday +
				"\nNotes: " + notes + "\n";


	}
}

