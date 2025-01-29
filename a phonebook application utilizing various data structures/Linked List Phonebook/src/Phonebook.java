

import java.util.*;
public class phonebook {




	public static LinkedList<Contact> contact_list = new LinkedList<Contact> (); 
	public static LinkedList<Event> event_list = new LinkedList<Event> (); 
	public static Scanner input = new Scanner(System.in);

	public static void main(String [] args) {
		System.out.println("Welcome to the Linked List Phonebook!");
		int choice;
		do {
			System.out.println("""
					Please choose an option:
					1.Add a contact
					2.Search for a contact
					3.Delet a contact
					4.Schedule an event
					5.Print event details
					6.Print contact by first name
					7.Print all events alphabetically
					8.Exit""");
			System.out.println("Enter your choice: ");

			choice = input.nextInt();

			switch(choice) {			
			
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

		}while(choice!=8); 



	}



	//---------------------------------------------------------------

	//method 1 

	public static void addContact() {
		System.out.println("Enter the contact's name: ");
		String name =input.nextLine(); 
		name =input.nextLine();
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
		System.out.println("Enter the contact's notes: ");
		String note = input.nextLine();
		

		Contact c = new Contact(name, phone_num, email, address, birth_date, note);

		if(!contact_list.empty()) {
			//check by name
			Contact con = contact_list.search(c);
			if(con != null)
				System.out.println("Contact already exist!");  

			//check by phone number
			contact_list.findFirst();
			while (!contact_list.last()) {
				if(contact_list.retrieve().phonenumber.equals(c.phonenumber)) {
					System.out.println("Contact already exist!");; 
					return;}
				contact_list.findNext();
			}

			
			if(contact_list.retrieve().phonenumber.equals(c.phonenumber)) 
				System.out.println("Contact already exist!");


			contact_list.add(c); 
			System.out.println("Contact added successfully!"); 

		}

		
		contact_list.add(c); 
		System.out.println("Contact added successfully!");
	}

	//---------------------------------------------------------------

	//method 2 
	public static void searchContact() { 
		System.out.println("Enter search criteria: ");
		System.out.println("1.Name\n2.Phone Number\n3.Email Address\n4.Address\n5.Birthday");
		System.out.println("Enter your choice: ");
		int choice2 = input.nextInt();


		switch(choice2) {

		case 1: 

			System.out.println("Enter the contact's name: ");
			String name = input.nextLine();
			name= input.nextLine();
			Contact contact = new Contact(); 
			contact.name = name; 
			Contact contact_found = contact_list.search(contact);

			if(contact_found == null) 
				System.out.println("Contact Not found!"); 

			else {
				System.out.println("Contact found!");
				System.out.println(contact_found); }

			break; 

		case 2:
			input.nextLine();
			System.out.println("Enter the contact's phone number: ");
			String phone_num = input.nextLine();
			input.nextLine();


			
			if(contact_list.empty()) 
				System.out.println("Contact Not found!");


			//check for phone number
			else {
				contact_list.findFirst();
				while (!contact_list.last()) {
					if(contact_list.retrieve().phonenumber.equals(phone_num)) {
						System.out.println("Contact found!");
						System.out.println(contact_list.retrieve());
						break; }

					contact_list.findNext(); }
			}

			//check for phone number for last node	
			if(contact_list.retrieve().phonenumber.equals(phone_num)) {
				System.out.println("Contact found!");
				System.out.println(contact_list.retrieve());
				break; } 

			//if there exist elements but it's not founded
			else 
				System.out.println("Contact Not found!");

			break; 

		case 3:
			input.nextLine();
			System.out.println("Enter the contact's email address: ");
			String email = input.nextLine();
			int count =0; 
			input.nextLine();

			//check first if there exist contact in the list or no

			if(contact_list.empty()) {
				System.out.println("Contact Not found!");
				return; }

			//check for email 
			else {
				contact_list.findFirst();
				while (!contact_list.last()) {
					if(contact_list.retrieve().emailaddress.equals(email)) {
						System.out.println(contact_list.retrieve());
						count++ ; }
					contact_list.findNext(); }

				//check for email for last node
				if(contact_list.retrieve().emailaddress.equals(email)) {
					System.out.println(contact_list.retrieve()); 
					count++ ; } 
			}
			//check if found 
			if(count > 0) {
				System.out.println("Contacts found!");
				return; }
			else {
				System.out.println("Contacts not found!");
				return; }
			

		case 4:
			input.nextLine();
			System.out.println("Enter the contact's address: ");
			String add = input.nextLine();
			int counter =0; 
			input.nextLine();

			//check first if there exist contact in the list or no
			if(contact_list.empty()) {
				System.out.println("Contact Not found!");
				return; }

			//check for address

			else {
				contact_list.findFirst();
				while (!contact_list.last()) {
					if(contact_list.retrieve().address.equals(add)) {
						System.out.println(contact_list.retrieve());
						counter++ ; }
					contact_list.findNext(); } 

				//check for address for last node
				if(contact_list.retrieve().address.equals(add)) {
					System.out.println(contact_list.retrieve()); 
					counter++ ; } 
			}

			//if found
			if(counter > 0) {
				System.out.println("Contacts found!");
				return; }
			else {
				System.out.println("Contacts not found!");
				return; }

			

		case 5:
			input.nextLine();
			System.out.println("Enter the contact's birthday: ");
			String bd = input.nextLine();
			int counting =0; 
			input.nextLine();

			//check first if there exist contact in the list or no
			if(contact_list.empty()) {
				System.out.println("Contact Not found!");
				return; }

			//check for birthday
			else {
				contact_list.findFirst();
				while (!contact_list.last()) {
					if(contact_list.retrieve().birthday.equals(bd)) {
						System.out.println(contact_list.retrieve());
						counting++ ; }
					contact_list.findNext(); }

				//check for birthday for last node
				if(contact_list.retrieve().birthday.equals(bd)) {
					System.out.println(contact_list.retrieve()); 
					counting++ ; } 
			}

			//check if found 
			if(counting > 0) {
				System.out.println("Contacts found!");
				return; }
			else {
				System.out.println("Contacts not found!");
				return; }
			

		}
	}

	//---------------------------------------------------------------

	// method 3 *****also here 
	public static void searchEvent() {
		System.out.println("Enter search criteria: ");
		System.out.println("1.contact name\n2.Event title");
		System.out.println("Enter your choice: ");
		int choice3 = input.nextInt();

		switch(choice3) {

		case 1: 
			System.out.println("Enter the contact's name: ");
			String nm = input.nextLine();
			nm = input.nextLine();
			Contact cc = new Contact();
			cc.name = nm;
			Contact c = contact_list.search(cc);
			if(c == null) 
				System.out.println("Contact not found!");
			else {
				if(c.events.empty())
					System.out.println("Contact not schedueld in any event!");
				else {
					c.events.findFirst();
					while(! c.events.last()) {
						System.out.println(c.events.retrieve());
						c.events.findNext();}
					//for last node 
					System.out.println(c.events.retrieve());  }
			}
			break ;

		case 2: 
			System.out.println("Enter the event title: ");
			String ev_ti = input.nextLine();
			ev_ti = input.nextLine();


			Event e = searchTitle(ev_ti);				
			if(e == null)
				System.out.println("Event not found!");
			else {
				System.out.println("Event found!");
				System.out.println(e); }
			break; 

		}
	}

	//---------------------------------------------------------------

	//method 4
	public static void printEvents() {
		if(event_list.empty())
			System.out.println("No events found!");
		else {
			event_list.findFirst();
			while(!event_list.last()) {
				System.out.println(event_list.retrieve());
				event_list.findNext(); }

			//for last node 
			System.out.println(event_list.retrieve());
		}
	}


	//---------------------------------------------------------------

	//method 5 
	public static void removeContact()
	{
		Contact c = new Contact();
		System.out.print("Enter the contact\'s name: ");
		c.name = input.nextLine();
		c.name = input.nextLine();
		input.nextLine();
		if (contact_list.empty())
			System.out.println("Contact not found!");
		else
		{
			c = contact_list.remove(c);
			if ( c == null)
				System.out.println("Contact not found!");
			else
			{
				if (! c.events.empty())
				{
					c.events.findFirst();
					while(!c.events.last()){
						Event e = c.events.retrieve();
						Event event = event_list.search(e);
						if(event != null ){
							event.removeContact(c.name);
							if(event.contactInvo.empty()){
								event_list.remove(event);
								System.out.println("Delete event, No cantact particapate");
							}
							else
								event_list.update(event);
						}
						c.events.findNext();
					} 
					Event e = c.events.retrieve();
					Event event = event_list.search(e);
					if(event != null ){
						event.removeContact(c.name);
						if(event.contactInvo.empty()){
							event_list.remove(event);
							System.out.println("Delete event, No cantact particapate");
						}
						else
							event_list.update(event);

					}
				}

				System.out.println("Contact Deleted Successfully !");

			}    
		}        
	}




	//---------------------------------------------------------------

	//method 6
	public static void printAll(String title) {
		Event event = searchTitle(title);
		if(event==null)
			System.out.println("There is no created events");
		else {
			if(event.contactInvo.empty())
				System.out.println("there is no contact in this event");
			else {
				event.contactInvo.findFirst();
				for(int i=0;i<event.count;i++) {
					System.out.println(event.contactInvo.retrieve());
					event.contactInvo.findNext();}
			}
		}
	}

	//---------------------------------------------------------------

	//method 7
	public static Event searchTitle(String title) {
		if(event_list.empty()) 
			return null;
		else {
			event_list.findFirst();
			while(!event_list.last()) {
				Event event = event_list.retrieve();
				if(event.title.equalsIgnoreCase(title))
					return event;
				else 
					event_list.findNext(); 
			}
		}

		Event event = event_list.retrieve();	
		if(event.title.equalsIgnoreCase(title))
			return event; 
		else 
			return null; 
	}


	//---------------------------------------------------------------

	//method 8

	public static void printFirstName() {
		System.out.println("Enter first name: ");

		String name1 = input.next();

		if (contact_list.empty()) {
			System.out.println("No contacts found!");
		} else {
			contact_list.findFirst();
			while (!contact_list.last()) {
				String[] nameParts = contact_list.retrieve().name.split(" "); // Split full name into parts

				if (nameParts.length >= 1 && name1.equals(nameParts[0])) {
					System.out.println(contact_list.retrieve());
				}

				contact_list.findNext();
			}
		}


	}  

	//---------------------------------------------------------------

	//method 9
	
	public static void ScheduleEvent()
    {
        Contact c = new Contact();
        Event e = new Event();
        
        boolean event_Updated = false;
        boolean Added_Event_To_Contact = false;
        
        System.out.print("Enter event title: ");
        e.title = input.nextLine();
        e.title = input.nextLine();
        System.out.print("Enter contact name: ");
        c.name = input.nextLine();
        c.name = input.nextLine();
        if ( ! contact_list.empty() && contact_list.search(c) != null)
        {
        	System.out.println("Enter the date of event");
			e.date = input.nextLine();
			e.date = input.nextLine();


			System.out.println("Enter the time of event");
			 e.time= input.nextLine();
			e.time= input.nextLine();
            
            System.out.print("Enter event location: ");
            e.location = input.nextLine();
            
            c = contact_list.retrieve();
            Added_Event_To_Contact = c.addEvent(e);

            if (Added_Event_To_Contact)
            {
               
            	contact_list.update(c);
                if (!event_list.empty() && event_list.search(e)!=null)
                {
                    Event eventFound = event_list.retrieve();
                    if ((eventFound.date.compareTo(e.date)== 0 ) 
                            && (eventFound.time.compareTo(e.time)== 0 ))
                          
                    {
                        eventFound.contactInvo.add(c.name);
                        event_list.update(eventFound);
                        event_Updated = true;
                    }
                }
                if (! event_Updated)  
                {
                    e.contactInvo.add(c.name);
                    event_list.add(e);
                }
                System.out.println("Event scheduled successfully!   ");
            }
            else
                System.out.println("Contact has conflict Event !  ");
        }
        else
            System.out.println("Cantcat not found !");
        
    }
}













