import java.util.*;

public class Phonebook {
	public static Scanner input = new Scanner(System.in);
	public static Contact_BST<String, Contact> contact_BST = new Contact_BST<String, Contact>();
	public static LinkedList<Event> event_list = new LinkedList<Event>();

	public static void main(String[] args) {
		System.out.println("Welcome to the BST Phonebook!");
		int choice;
		do {
			System.out.println("""
					\nPlease choose an option:
					1.Add a contact
					2.Search for a contact
					3.Delete a contact
					4.Schedule an event/appointment
					5.Print event details
					6.Print contact by first name
					7.Print all events alphabetically
					8.Exit""");
			System.out.println("Enter your choice: ");

			choice = input.nextInt();

			switch (choice) {

			case 1:
				addContact();
				break;

			case 2:
				searchContact();
				break;

			case 3:
				removeContact();
				break;

			case 4:
				ScheduleEvent();
				break;

			case 5:
				searchEvent();
				break;

			case 6:
				printFirstName();
				break;

			case 7:
				printEvents();
				break;

			case 8:
				System.out.println("Good bye!");
				break;

			}

		} while (choice != 8);

	}

	// ---------------------------------------------------------------
	// method 1

	public static void addContact() {
		System.out.println("Enter the contact's name: ");
		input.nextLine();
		String name= input.nextLine();

		System.out.println("Enter the contact's phone number: ");
		String phone_num = input.nextLine();

		System.out.println("Enter the contact's email address: ");
		String email = input.nextLine();

		System.out.println("Enter the contact's address: ");
		String address;
		address = input.nextLine();

		System.out.println("Enter the contact's birthday: ");
		String birth_date;
		birth_date = input.nextLine();
		System.out.println("Enter any notes for the contact: ");
		String note = input.nextLine();

		Contact c = new Contact(name, phone_num, email, address, birth_date, note);

		if (!contact_BST.empty()) {
			// check by name
			if (contact_BST.findkey(c.name)) {
				System.out.println("Contact found!");
				return;
			}
			// check by phone number
			Contact cc = contact_BST.searchPhone(phone_num);
			if (cc != null) {
				System.out.println("Contact found!");
				return;
			}
		}

		if (contact_BST.insert(name, c))
			System.out.println("Contact added successfully!");
	}

	// ---------------------------------------------------------------
	// method 2
	public static void searchContact() {
		if (contact_BST.empty())
			System.out.println("Contact_BST is empty!");
		else {
			System.out.println("Enter search criteria: ");
			System.out.println("1.Name\n2.Phone Number\n3.Email Address\n4.Address\n5.Birthday");
			System.out.println("Enter your choice: ");
			int choice2 = input.nextInt();

			switch (choice2) {
			case 1:
				System.out.println("Enter the contact's name: ");
				input.nextLine();
				String name = input.nextLine();
				if (contact_BST.findkey(name)) {
					System.out.println("Contact found!");

					System.out.println(contact_BST.retrieve().toString());
					break;
				}
				System.out.println("Contact Not found!");
				break;

			case 2:
				System.out.println("Enter the contact's phone number: ");
				input.nextLine();
				String phone_num = input.nextLine();
				Contact c = contact_BST.searchPhone(phone_num);
				if (c != null) {
					System.out.println("Contact found!");

					System.out.println(c.toString());
					break;
				}
				System.out.println("Contact Not found!");

				break;

			case 3:
				System.out.println("Enter the contact's email address: ");
				input.nextLine();
				String email = input.nextLine();
				if (contact_BST.SearchEmail(email))
					System.out.println("Contact found!");

				else
					System.out.println("Contact Not found!");

				break;

			case 4:
				System.out.println("Enter the contact's address: ");
				input.nextLine();
				String add = input.nextLine();
				if (contact_BST.SearchADS(add))
					System.out.println("Contact found!");

				else
					System.out.println("Contact Not found!");

				break;

			case 5:
				System.out.println("Enter the contact's birthday: ");
				input.nextLine();
				String bd = input.nextLine();
				if (contact_BST.SearchDOB(bd))
					System.out.println("Contact found!");

				else
					System.out.println("Contact Not found!");

				break;

			}
		}
	}

	// ---------------------------------------------------------------
	// method 3
	public static void removeContact() {
		System.out.print("Enter the contact\'s name:\n ");
		input.nextLine();
		String name = input.nextLine();
		Contact c = new Contact();
		c.name = name;
		if (contact_BST.empty())
			System.out.println("Contact_BST is empty!");
		else {
			if (!contact_BST.findkey(c.name))
				System.out.println("Contact not found!");
			else {
				c = contact_BST.retrieve();
				if (!c.events.empty()) {
					c.events.findFirst();
					while (!c.events.last()) {
						Event e = c.events.retrieve();
						Event event = event_list.search(e);
						if (event != null) {
							event.removeContact(c.name);
							if (event.contactInvo.empty()) {
								event_list.remove(event);
								System.out.println("Delete event, No cantact particapate");
							} else
								event_list.update(event);
						}
						c.events.findNext();
					}
					Event e = c.events.retrieve();
					Event event = event_list.search(e);
					if (event != null) {
						event.removeContact(c.name);
						if (event.contactInvo.empty()) {
							event_list.remove(event);
							System.out.println("Delete event, No cantact particapate");
						} else
							event_list.update(event);

					}
				}

				if (contact_BST.removeKey(c.name))
					System.out.println("Contact Deleted Successfully !");

			}
		}
	}

	// ---------------------------------------------------------------
	// method 4
	public static void ScheduleEvent() {
		System.out.println("Enter type:\n1.event\n2.appointment");
		int choice = input.nextInt();

		Event event = new Event();
		boolean event_Updated=false;
		boolean Added_Event_To_Contact = false;

		if(choice==1){
			System.out.print("Enter event title: ");
			input.nextLine();
			String title = input.nextLine();
			event.title = title;
			event.EventType =true;
		}

		if(choice==2){
			System.out.print("Enter appointment title: ");
			input.nextLine();
			String title = input.nextLine();
			event.title = title;
			event.EventType =false;
		}

		if (event.EventType) {  // Event schedule

			System.out.print("Enter contacts name separated by a comma: ");
			String names_line = input.nextLine();
			String [] names = names_line.split(",");

			System.out.println("Enter the date of event");
			String date = input.nextLine();
			event.date = date;

			System.out.println("Enter the time of event");
			String time = input.nextLine();
			event.time = time;

			System.out.print("Enter event location: ");
			event.location = input.nextLine(); 


			for( int i = 0 ; i < names.length ; i++){
				Contact c = new Contact();
				c.name = names[i];

				if (!contact_BST.empty() && contact_BST.findkey(c.name)) {

					c = contact_BST.retrieve();

					Added_Event_To_Contact = c.addEvent(event);

					if (Added_Event_To_Contact) {

						Event e = event_list.search(event);
						if ( (!event_list.empty()) && e!=null
								&& (e.date.compareTo(event.date)==0)
								&& (e.time.compareTo(event.time)==0)
								&& (e.location.compareTo(event.location)==0)
								&& (e.EventType==event.EventType)) {

							e.addContactName(c.name);

							event_list.update(e);

							event_Updated = true;

						}

						if (!event_Updated) {
							event.addContactName(c.name);
							event_list.add(event);

						}

						System.out.println("Event scheduled successfully for " + c.name + "!");

					} else {

						System.out.println("Contact has conflict event for " + c.name + "!");

					}

				} else {

					System.out.println("Contact " + c.name + " not found!");

				}

			}

		} else {  // Appointment schedule

			Contact c = new Contact();

			System.out.print("Enter contact name: ");

			c.name = input.nextLine();



			if (!contact_BST.empty() && contact_BST.findkey(c.name)) {

				c = contact_BST.retrieve();

				System.out.println("Enter the date of appointment");
				String date = input.nextLine();
				event.date = date;

				System.out.println("Enter the time of appointment");
				String time = input.nextLine();
				event.time = time;

				System.out.print("Enter appointment location: ");
				event.location = input.nextLine();

				Event e = event_list.search(event);
				if ( (!event_list.empty()) && e!=null
						&& (e.date.compareTo(event.date)==0)
						&& (e.time.compareTo(event.time)==0)
						&& (e.location.compareTo(event.location)==0)
						&& (e.EventType==event.EventType)) {

					System.out.println("Appointment had been scheduled previously, could not add more contacts ");

				} else {

					Added_Event_To_Contact = c.addEvent(event);

					if (Added_Event_To_Contact) {

						event.addContactName(c.name);

						event_list.add(event);

						System.out.println("Appointment scheduled successfully!");

					} else {

						System.out.println("Contact has conflict Event/Appointment!");

					}

				}

			} else {

				System.out.println("Contact not found!");

			}

		}

	}



	// ---------------------------------------------------------------
	// method 5
	public static void searchEvent() {
		System.out.println("Enter search criteria: ");
		System.out.println("1.contact name\n2.Event title");
		System.out.println("Enter your choice: ");
		int choice3 = input.nextInt();

		switch (choice3) {

		case 1:
			System.out.println("Enter the contact's name: ");
			input.nextLine();
			String name = input.nextLine();
			Contact c = new Contact();
			c.name = name;
			if (contact_BST.empty())
				System.out.println("Contact_BST is empty!");
			else {
				if (!contact_BST.findkey(c.name))
					System.out.println("Contact not found!");
				else {
					System.out.println("Contact found!");
					c = contact_BST.retrieve();
					if (c.events.empty())
						System.out.println("Contact not schedueld in any event!");
					else {
						c.events.findFirst();
						while (!c.events.last()) {
							System.out.println(c.events.retrieve().toString());
							c.events.findNext();
						}
						// for last node
						System.out.println(c.events.retrieve().toString());
					}
				}
			}
			break;

		case 2:
			System.out.println("Enter the Event title: ");
			input.nextLine();
			String ev_ti = input.nextLine();
			Event e = new Event();
			e.title = ev_ti;
			if (event_list.empty())
				System.out.println("No Events/Appoinment found!");
			else {
				event_list.findFirst();
				while (!event_list.last()) {
					Event ee = event_list.retrieve();
					if (ee.compareTo(e) == 0) {
						if (ee.EventType == true)
							System.out.println("Event found!");
						else
							System.out.println("Appoinment found!");

						System.out.println(ee.toString());
					}
					event_list.findNext();
				}
				// for last node
				Event ee = event_list.retrieve();
				if (ee.compareTo(e) == 0) {
					if (ee.EventType == true)
						System.out.println("Event found!");
					else
						System.out.println("Appoinment found!");

					System.out.println(ee.toString());
				}
			}

			break;

		}
	}

	// ---------------------------------------------------------------
	// method 6
	public static void printFirstName() {
		System.out.println("Enter first name: ");
		String name = input.next();
		if (!contact_BST.empty() && contact_BST.SearchFisrt_name(name))
			System.out.println("Contact found!");
		else
			System.out.println("Contact Not found!");

	}

	// ---------------------------------------------------------------
	// method 7
	public static void printEvents() {
		if (event_list.empty())
			System.out.println("No events found!");
		else
			System.out.println(event_list.toString());
	}



}
