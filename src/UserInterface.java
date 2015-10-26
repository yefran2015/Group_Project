import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.Double;
import java.lang.Integer;
import java.util.GregorianCalendar;
import java.util.Iterator;
/**
 * Testing of changes by Andrey
 * /
 


/**
 * This class allows for the user interaction and implementation
 * of a theater. User will be able to add a customer,client,show
 * and a credit card. User will also be able to manipulate data
 * as they add information along with loading and storing data.
 * 
 *  @author Taylor, Andrey, Chelsea, Jean
 */
public class UserInterface {
    
    /**
     * Declared variables
     */
    private BufferedReader userInput = new BufferedReader
                    (new InputStreamReader(System.in));
    
    private final File THEATER_SAVE_FILE = new File("theater.dat");
    private Theater theater;
    private Customer customer;
    private Client client;
    private CreditCard creditCard;
    private Show show;
    
    /**
     * Constructor that instantiates a Theater object
     * and also contains the entire user interface
     * @throws NumberFormatException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public UserInterface() throws NumberFormatException,IOException
                                 ,ClassNotFoundException {
        
        int input = -1;
        
        theater = Theater.getInstance("CJTA Group Theater",350);
        
        checkIfFileExists();
        help();
        //Loops a switch statement for user commands
        while(input != 0) {
            
            System.out.print("Enter a command: ");
            input = new Integer(userInput.readLine());
            
            switch(input) {
                
                case 1: {
                    addClient();
                    break;
                }
                
                case 2: {
                    removeClient();
                    break;
                }
                
                case 3: {
                    listAllClients();
                    break;
                }
                
                case 4: {
                    addCustomer();
                    break;
                }
                
                case 5: {
                    removeCustomer();
                    break;
                }
                
                case 6: {
                    addCreditCard();
                    break;
                }
                
                case 7: {
                    removeCreditCard();
                    break;
                }
                
                case 8: {
                    listAllCustomers();
                    break;
                }
                
                case 9: {
                    addShow();
                    break;
                }
                
                case 10: {
                    listAllShows();
                    break;
                }
                
                case 11: {
                    storeData();
                    break;
                }
                
                case 12: {
                    retrieveData();
                    break;
                }

                case 13: {
                    sellRegularTickets();
                    break;
                }

                case 14: {
                    sellAdvanceTickets();
                    break;
                }

                case 15: {
                    sellStudentAdvanceTickets();
                    break;
                }

                case 16: {
                    payClient();
                    break;
                }

                case 17: {
                    printAllTickets();
                    break;
                }
                
                case 18: {
                    help();
                    break;
                } 
            }
        }
        
       System.out.print("\nThe program has ended.");
       storeData();
    }
    
    /**
     * This method shows the command list
     */
    private void help() {
        System.out.print("0:  Exit\n"
                        +"1:  Add Client\n"
                        +"2:  Remove Client\n"
                        +"3:  List All Clients\n"
                        +"4:  Add Customer\n"
                        +"5:  RemoveCustomer\n"
                        +"6:  Add Credit Card\n"
                        +"7:  Remove Credit Card\n"
                        +"8:  List All Customers\n"
                        +"9:  Add Show\n"
                        +"10: List All Shows\n"
                        +"11: Store Data\n"
                        +"12: Retrieve Data\n"
                        +"13: Help\n\n");
    }

    /**
     * This method loads serialized data from file
     * @throws IOException 
     * @throws FileNotFoundException 
     * @throws ClassNotFoundException 
     */
    @SuppressWarnings("resource")
	private void retrieveData() throws FileNotFoundException,IOException
                                ,ClassNotFoundException {
        
    	if(THEATER_SAVE_FILE.exists()) {
	        ObjectInputStream inFile;
	        Object object;
	        inFile = new ObjectInputStream(new FileInputStream(THEATER_SAVE_FILE));
	        
	        while((object = inFile.readObject())!= null) {
	            if(object.getClass().isInstance(theater)) {
	                theater = (Theater)object;
	                break;
	            }
	        }
	        
	        theater.loadData();
	        
	        if(theater.getClientList()!=null && theater.getClientList().size()>0)
	        	Client.setClientId(theater.getClientList().get(theater
	        			.getClientList().size()-1).getClientID()+1);
	        
	        if(theater.getCustomerList()!=null && theater.getCustomerList().size()>0)
	        	Customer.setCustomersId(theater.getCustomerList().get(theater
	        			.getCustomerList().size()-1).getCustomerID()+1);
	        
	        System.out.println("The data was loaded successfully.\n");
    	}else{
    		System.out.print("\nThere is no saved data.\n");
    	}
    }
    /**
     * This method store serialized data to file
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    @SuppressWarnings("resource")
	private void storeData() throws FileNotFoundException, IOException {
        ObjectOutputStream out;
        theater.storeData();
        out = new ObjectOutputStream(new FileOutputStream(THEATER_SAVE_FILE));
        out.writeObject(theater);
        System.out.print("\nThe data was successfully stored.\n");
    }

    /**
     * This method lists all of the shows 
     */
    private void listAllShows() {
        System.out.println();
        theater.listAllShows();
    }

    /**
     * This method adds a show to the theater
     * 
     * @throws NumberFormatException
     * @throws IOException
     */
    private void addShow() throws NumberFormatException, IOException {
        
        GregorianCalendar startDate;
        GregorianCalendar endDate;
        String showName;
        int clientID;
        int year;
        int month;
        int date;
        double regularTicketPrice;
        
        System.out.println();
        System.out.print("Enter the client's id: ");
        clientID = new Integer(userInput.readLine());
        
        System.out.print("Enter the name of the show: ");
        showName = userInput.readLine();
        
        System.out.println("\nEnter the start date.");
        System.out.print("Year: ");
        year = new Integer(userInput.readLine());
        System.out.print("Month(numeric 1-12): ");
        boolean done = false;
        startDate = null;
        
        while(!done){
        	try{
        		month = new Integer(userInput.readLine());
        		
        		System.out.print("Date: ");
                date = new Integer(userInput.readLine());
                
                startDate = new GregorianCalendar(year,month,date);
                done = true;
        	}catch(Exception ex){
        		System.out.println("Month should be numeric...");
        	}
        }
        
        
        System.out.println("\nEnter the end date.");
        System.out.print("Year: ");
        year = new Integer(userInput.readLine());
        done=false;
        endDate = null;
        while(!done){
        	try{
        		System.out.print("Month(numeric 1-12): ");
        		month = new Integer(userInput.readLine());
        		System.out.print("Date: ");
        		date = new Integer(userInput.readLine());
        		System.out.println();
        
        		endDate = new GregorianCalendar(year,month,date);
        		done = true;
        		
        	}catch(Exception ex){
        		System.out.println("Month should be numeric...");
        	}
        }


        System.out.println("\nEnter the regular ticket price: $");
        regularTicketPrice = new Double(userInput.readLine());


        show = new Show(showName, clientID, startDate, endDate, regularTicketPrice);
        theater.addShow(show);



    }

    /**
     * This method lists all of the customers
     */
    private void listAllCustomers() {
        System.out.println();
        theater.listAllCustomers();
    }

    /**
     * This method gets a customer ID, and if its a valid customer,
     * the customer class is called to add a credit card to that customer.
     *
     */
    private void addCreditCard(){
        System.out.println();
        System.out.print("Enter the customer's ID: ");
        customer = theater.findCustomer(new
                Integer(userInput.readLine()));

        if(customer != null){
            customer.addCreditCard(addCreditCard());
        }
        else {
            System.out.println("\nThat customer is "
                    + "not in the database.\n");
        }
    }

    /**
     * This method removes a credit card
     * 
     * @throws NumberFormatException
     * @throws IOException
     */
    private void removeCreditCard() throws NumberFormatException, IOException  {
        
        System.out.print("\nEnter the customers id to pull up their account:");
        customer = theater.findCustomer(new Integer(userInput.readLine()));
        
        if(customer != null) {
            System.out.print("Enter the card number that is to be removed:");
            customer.removeCard(new Long(userInput.readLine()));
            System.out.println();
        }
        else {
            System.out.println("That customer is not in the database.\n");
        }
    }

    /**
     * This method adds a credit card to a specific customer
     * 
     * @return
     * @throws NumberFormatException
     * @throws IOException
     */
    private CreditCard addCreditCard() throws NumberFormatException, IOException {
        
        GregorianCalendar gregorianCalendar;
        Iterator<Customer> customer;
        Iterator<CreditCard> card;
        CollectionList<Customer> customerList;
        
        boolean cardExists = false;
        long cardNumber;
        int year;
        int month;
        String nameOnCard;
        
        gregorianCalendar = new GregorianCalendar();
        
        System.out.print("Card number:");
        cardNumber = new Long(userInput.readLine());
        
        customerList = theater.customerList();
        customer = customerList.iterator();
        
        while(customer.hasNext()) {
            
            card = customer.next().cardList().iterator();
            
            while(card.hasNext()) {
                if(card.next().getCardNumber() == cardNumber) {
                    cardExists = true;
                    break;
                }
            }
        }
        
        if(cardExists == false) {
            System.out.print("Name on card:");
            nameOnCard = userInput.readLine();
        
            System.out.println("\nEnter the expiration of the card.");
            System.out.print("Year:");
            year = new Integer(userInput.readLine());
        
            System.out.print("Month:");
            month = new Integer(userInput.readLine());
        
            gregorianCalendar.set(year,month,0);
        
            creditCard = new CreditCard(cardNumber,nameOnCard,gregorianCalendar);
        
            System.out.println();
        
            return creditCard;
        }
        else {
            System.out.println("\nUnable to add card, another customer has the "
                            + "same card.\n\nPlease enter a valid credit card. ");
            
            return addCreditCard();
        }
    }

    /**
     * This method removes a specific customer
     * 
     * @throws NumberFormatException
     * @throws IOException
     */
    private void removeCustomer() throws NumberFormatException, IOException {
        int userId;
        System.out.println();
        System.out.print("Id:");

        userId = new Integer(userInput.readLine());
        customer = theater.findCustomer(userId);
        
        if(customer == null) {
            System.out.println("\nThat customer is not in the database.\n");
        }
        else {
            theater.removeCustomer(userId);
            System.out.println("\nThe customer was removed.");
            System.out.println();
        }
    }

    /**
     * This method adds a customer to the theater
     * 
     * @throws NumberFormatException
     * @throws IOException
     */
    private void addCustomer() throws NumberFormatException, IOException {
        String firstName;
        String lastName;
        String address;
        String phone;
        
        System.out.println();
        System.out.print("First name:");
        firstName = userInput.readLine();
        
        System.out.print("Last name:");
        lastName = userInput.readLine();
        
        System.out.print("Address:");
        address = userInput.readLine();
        
        System.out.print("Phone Number:");
        phone = userInput.readLine();
        
        customer = new Customer(Customer.createCustomerID(),firstName,lastName,address
                        ,phone,addCreditCard());
        
        theater.addCustomer(customer);
    }

    /**
     *This method lists all of the clients
     */
    private void listAllClients() {
        theater.listAllClients();
    }

    /**
     * This method removes a specific client
     * 
     * @throws NumberFormatException
     * @throws IOException
     */
    private void removeClient() throws NumberFormatException, IOException {
        System.out.println();
        System.out.print("Id:");
        theater.removeClient(new Integer(userInput.readLine()));
    }

    /**
     * This method adds a client
     * 
     * @throws IOException
     */
    private void addClient() throws IOException {
        
        String name;
        String address;
        String phoneNumber;
        
        System.out.println();
        System.out.print("Name:");
        name = userInput.readLine();
        
        System.out.print("Address:");
        address = userInput.readLine();
        
        System.out.print("Phone number:");
        phoneNumber = userInput.readLine();
        
        System.out.println();
        
        Client client = new Client(Client.createClientID(),name,address,phoneNumber);
        theater.addClient(client);
    }

    private void sellRegularTickets() {


    }

    private void sellAdvanceTickets() {


    }

    private void sellStudentAdvanceTickets() {


    }

    /**
     * This method attempts to pay the client
     * @throws IOException 
     * @throws NumberFormatException 
     *
     */
    private void payClient() throws NumberFormatException, IOException {
        System.out.print("Client ID: ");
        int clientID = new Integer(userInput.readLine());
        Client client = theater.findClient(clientID);
        double payment = -1;

        if (client != null){
            System.out.print("Current Ballance: $" + client.getBalance());
            System.out.print("Amount to be paid to client: $");
            payment = new Double(userInput.readLine());
            while (!(payment > 0 && payment < client.getBalance())) {
                System.out.print("Amount must be less than the total balance: $");
                payment = new Double(userInput.readLine());
            }
            theater.payClient(client, payment);
        }
        else {
            System.out.println("This client does not exist in the system.");
        }
    }

    /**
     * This method gets a valid date from the user and sends it
     * to the Theater class to print all tickets for that
     * specified date.
     * @throws IOException 
     * @throws NumberFormatException 
     *
     */
    private void printAllTickets() throws NumberFormatException, IOException {
        GregorianCalendar dateToPrint;
        System.out.println("Enter a date to print tickets for.");
        dateToPrint = getDate();
        theater.printAllTickets(dateToPrint);
    }

    /**
     * This method is a helper method for all other methods
     * requiring the user to input a date.
     *
     * @return a valid formatted date
     * @throws IOException 
     * @throws NumberFormatException 
     */
    private GregorianCalendar getDate() throws NumberFormatException, IOException {
        boolean done = false;
        int year;
        int month = 0;
        int date = 0;

        System.out.print("Year: ");
        year = new Integer(userInput.readLine());
        System.out.print("Month(numeric 1-12): ");

        while(!done){
            try{
                month = new Integer(userInput.readLine());
                System.out.print("Date: ");
                date = new Integer(userInput.readLine());

                return new GregorianCalendar(year,month,date);
            }
            catch(Exception ex){
                System.out.println("Month should be numeric...");
            }
        }
        return null;
    }

    /**
     * This method checks if a file with the name theater.dat
     * exists and if it does, it will ask the user
     * if they would like to use the stored data.
     * 
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    private void checkIfFileExists() throws IOException, ClassNotFoundException {
        
        if(THEATER_SAVE_FILE.exists()) {
           System.out.print("There is some data that already exists. Would you "
                           + "like to load it? \n\nCommand(y/n):");
           
           switch(userInput.readLine().charAt(0)) {
               case 'y': {
                   retrieveData();
                   System.out.println("\nWelcome to "+theater.getTheaterName());
                   System.out.println("Seating capacity "+theater
                		             .getSeatingCapacity()+"\n");
                   break;
               }
               
               case 'n': {
                   System.out.println("\nWarning! Previous data will be "
                                   + "erased if you do not load it.\n");
                   break;
               }
           }
        } 
    }
    
    /**
     * Main method
     * @param args
     * @throws NumberFormatException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public static void main(String args[]) throws NumberFormatException, 
                                   IOException, ClassNotFoundException {
         new UserInterface();
    }
    //End
}
