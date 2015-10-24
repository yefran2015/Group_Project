import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

/**
 *  This class will allow for the collection of  all 
 *  Customers,Clients and Shows. The class will also
 *  output the objects that are serialized to be stored
 *  into a file called theater.dat
 *  
 *  @author Taylor, Andrey, Chelsea, Jean
 */
public class Theater implements Serializable {
    /**
     * Declared variables
     */
    private String nameOfTheater;
    private int seatingCapacity;
    private static final long serialVersionUID = 1L;
    private CollectionList<Customer> customerList;
    private CollectionList<Client> clientList;
    private CollectionList<Show> showList;
    private final File THEATER_SAVE_FILE = new File("theater.dat");
    
    private static Theater theater = null;
    

    public CollectionList<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(CollectionList<Customer> customerList) {
		this.customerList = customerList;
	}

	public CollectionList<Client> getClientList() {
		return clientList;
	}

	public void setClientList(CollectionList<Client> clientList) {
		this.clientList = clientList;
	}

	/**
     * This constructor takes nameOfTheater and seatingCapacity as input
     * and creates Theater object.
     * @param nameOfTheater -- initial name of theater
     * @param seatingCapacity -- initial seating capacity
     */
    private Theater(String nameOfTheater,int seatingCapacity) {
        this.nameOfTheater = nameOfTheater;
        this.seatingCapacity = seatingCapacity;
        customerList = new CollectionList<Customer>();
        clientList = new CollectionList<Client>();
        showList = new CollectionList<Show>();
    }
    
    /**
     * This method is checking if instance of this object exists,
     * and if not then return one.
     * Basically this method works as object creator aka constructor.
     * @return -- instance of this object
     */
    public static Theater getInstance(String nameOfTheater,int seatingCapacity) {
        if(theater == null){
            theater = new Theater(nameOfTheater,seatingCapacity);
        }
        return theater;
    }
    
    /**
     *This method is for terminate singleton object,
     * or better to say kill it for ability later to create new one. 
     */
    public static void killInstance(){
        theater=null;
    }
    
    /**
     * This constructor is checking if object from this class already was created,
     * and if yes then throw an exception.
     * @throws Exception -- exception if instance of this class already exists
     */
    protected Theater() throws Exception{
        if(getClass().getName().equals("Theater")){
            throw new Exception();
        }
    }
    
    /**
     * This method sets the name of the theater
     * @param nameOfTheater -- new Name of theater
     */
    public void setName(String nameOfTheater) {
        this.nameOfTheater = nameOfTheater;
    }
    
    /**
     * This method sets the seating capacity for the theater
     * @param seatingCapacity -- new Seating capacity
     */
    public void setCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }
     
    /**
     * This method adds a client to the CollecitionList<Client>
     * 
     * @param client -- client to add
     */
    public void addClient(Client client) {
        
        clientList.add(client);
    }
    
    /**
     * This method adds a customer to the CollecitionList<Customer>
     * @param customer -- customer to add
     */
    public void addCustomer(Customer customer) {
        
        if(customerList.search(customer) != null) {
            System.out.println("This customer is already registered.");
        }
        else{
            customerList.add(customer);
        }
    }
    
    /**
     * This method adds a Show to the CollecitionList<Show>
     * @param show -- show to add
     */
    public void addShow(Show show) {
        boolean validDates = true;
        
        if(clientList.search(new Client(show.getClientID(),null,null,null)) != null) {
        	if(showList.search(show) != null){
        		System.out.println("This show already exists on that date.");
        	}
        	else {
        		Iterator<Show> iterator = showList.iterator();
        		while (iterator.hasNext() && validDates){
        			Show test = iterator.next();
        			if(show.getStartDate().compareTo(test.getStartDate()) > 0 && 
        					show.getStartDate().compareTo(test.getEndDate()) < 0 ){
        				validDates = false;
        			}
        			else if(show.getEndDate().compareTo(test.getStartDate()) > 0 && 
        					show.getEndDate().compareTo(test.getEndDate()) < 0){
        				validDates = false;
        			}
        		}
        		if(validDates){
        			showList.add(show);
        		}
        		else{
        			System.out.println("This show could not be added. "
        					+"The dates conflict with another show.");
            }
          }
       }
        else{
        	System.out.println("Unable to add show, client does not exist\n");
        }
    }
    
    /**
     * Removes a client that is associated with the given ID
     * Removes all shows related to the client unless a show
     * is scheduled on the current date or a date in the future
     * 
     * @param clientID -- ID of client who will be remove
     */
    public void removeClient(int clientID) {
        Iterator<Show> iterator;
        Show show;
        Client client;
        
        GregorianCalendar currentDate;
        boolean futureShow = false;
        
        client = new Client(clientID,null,null,null);
        currentDate = new GregorianCalendar();
        iterator = showList.iterator();
        
        if(clientList.search(client) != null) {
            
            while(iterator.hasNext()) {
                
               show = iterator.next();
               
               if(show.getClientID() == clientID) {
                   
                   if(show.getEndDate().get(Calendar.YEAR) > currentDate
                                            .get(Calendar.YEAR)) {
                       
                       System.out.print("\nUnable to remove client; there exists"
                                       + " a scheduled show.\n\n");
                       futureShow = true;
                       break;
                   }
                   else if(show.getEndDate().get(Calendar.YEAR) == 
                                   currentDate.get(Calendar.YEAR)) {
                       
                       if(show.getEndDate().get(Calendar.MONTH)>= 
                                   currentDate.get(Calendar.MONTH)) {
                           
                           System.out.print("\nUnable to remove client; there "
                                           + "exists a scheduled show.\n\n");
                           
                           futureShow = true;
                           break;
                       }
                       else if(show.getEndDate().get(Calendar.MONTH) == 
                                       currentDate.get(Calendar.MONTH)) {
                       
                           if(show.getEndDate().get(Calendar.DATE) >= 
                                           currentDate.get(Calendar.DATE)) {
                               
                           System.out.print("\nUnable to remove client; there "
                                           +"exists a scheduled show.\n\n");
                               
                           futureShow = true;
                           break; 
                       }
                       else if(show.getEndDate().get(Calendar.DATE) == currentDate.get(Calendar.DATE)) {
 
                           if(show.getEndDate().get(Calendar.DATE) >= currentDate.get(Calendar.DATE)) {
                               System.out.print("\nUnable to remove client; there exists a scheduled show.\n\n");
                               futureShow = true;
                               break; 
                           }
                       }
                   }
               }
            }
         } 
            if(futureShow == false) {
                iterator = showList.iterator();
                while(iterator.hasNext()) {
                    show = iterator.next();
                    if(show.getClientID() == clientID) {
                        showList.remove(show);
                    }
                }
               
                this.clientList.remove(client);
                System.out.print("\nThe client was removed.\n\n");
            }
        }
        else {
            System.out.println("The client with that ID is not in the database.");
        }
    }
    
    /**
     * Removes a specific customer and all credit cards that 
     * the customer may have
     * 
     * @param customerID -- -- id of customer who will be remove
     */
    public void removeCustomer(int customerID) {
        
        Customer customer = customerList.search(new Customer(customerID
                            ,null,null,null,null,null));
        
        if(customerList.search(customer) != null) {
           customer.removeAllCreditCards();
           this.customerList.remove(customer);
        }
        else {
            System.out.println("\nThat customer is not in the database.\n");
        }
    }
    
    /**
     * Removes a show by its name
     * @param showName-- shows name which  will be remove
     */
    public void removeShow(String showName) {
        
        Show show = new Show(showName,0,null,null,0);
        
        if(showList.search(show) != null) {
            this.showList.remove(show);
        }
        else {
            System.out.println("This show does not exist in the database.");
        }
    }
    
    /**
     *This method displays all of the clients stored in a CollectionList<Client>
     */
    public void listAllClients() {
        Iterator<Client> iterator = clientList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next().toString());
        }
    }
    
    /**
     * This method displays all of the clients stored in a CollectionList<Customer>
     */
    public void listAllCustomers() {
        Iterator<Customer> iterator = customerList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
    }
    
    /**
     * This method displays all of the clients stored in a CollectionList<Show>
     */
    public void listAllShows() {
        Iterator<Show> iterator = showList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next().toString());
        }
    }
    
    /**
     *This method loads the serialized data from the file
     */
    @SuppressWarnings("unchecked")
	public void loadData() {
        try {
            ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(THEATER_SAVE_FILE));
            Object object;
            
            while((object = inFile.readObject()) != null) {
                
                if(object.getClass().isInstance(clientList)) {
                    this.clientList = (CollectionList<Client>)object;
                    break;
                }
                else if(object.getClass().isInstance(customerList)) {
                    this.customerList = (CollectionList<Customer>)object;
                    break;
                }
                else if(object.getClass().isInstance(showList)) {
                    this.showList = (CollectionList<Show>)object;
                    break;
                } 
            }
            
            this.clientList = (CollectionList<Client>)inFile.readObject(); 
            this.customerList = (CollectionList<Customer>) inFile.readObject();
            this.showList = (CollectionList<Show>) inFile.readObject();
            inFile.close();
        } catch (FileNotFoundException error) {
            System.out.println("File not Exists");
            
        } catch (IOException error) {
        } catch (ClassNotFoundException error) {
            error.printStackTrace();
        }   
    }
   
    /**
     * This method stores any object that has implemented Serializable into the file
     */
    public void storeData() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(THEATER_SAVE_FILE));
            out.writeObject(this.clientList);
            out.writeObject(this.customerList);
            out.writeObject(this.showList);
            out.close();
        } catch (FileNotFoundException error) {
            System.out.println("File not Found "+error.getMessage());
        } catch (IOException error) {
            System.out.println("IO Exception error: "+ error.getMessage()); 
        }
    }

    /**
     * This method attempts to pay the client
     *
     */
    public void payClient(Client client, double payment){
        client.setBalance(client.getBalance() - payment);
    }

    /**
     * This method prints a list of all tickets sold for a
     * given date
     * @param dateToPrint - the date for which to print tickets for
     */
    public void printAllTickets(GregorianCalendar dateToPrint) {

    }

    /**
     * This method returns the name of the theater
     * @return name of the theater
     */
    public String getTheaterName() {
        return this.nameOfTheater;
    }
    
    /**
     * This method returns the seating capacity for the theater.
     * @return seating capacity
     */
    public int getSeatingCapacity() {
        return this.seatingCapacity;
    }
    
    /**
     * This method searches for a customer with a given ID number and returns the object
     * 
     * @param customerId -- ID of customer to search
     * @return found customer or null if customer not exists
     */
    public Customer findCustomer(int customerId) {
        return this.customerList.search(new Customer(customerId,"","","","",null));
    }
   
    /**
     * This method searches for a client with a given ID number and returns the object
     * 
     * @param clientId -- id of client whom to search
     * @return found client or null if client not found
     */
    public Client findClient(int clientId) {
    	return this.clientList.search(new Client(clientId,"","",""));
    }
    
    /**
     * This method searches for a show with a given name and returns the object
     * 
     * @param showName -- name of show to search
     * @return found show or null if show not found 
     */
    public Show findShow(String showName) {
    	return this.showList.search(new Show(showName,0,null,null,0));
    }
    
    /**
     * Returns the CollectionList of all customers
     * 
     * @return customers list collection
     */
    public CollectionList<Customer> customerList() {
        return customerList;
    }
    
    @Override
    public String toString() {
        return "Theater apps created by Chelsea, Jean, Taylor, and Andrey\n"+"Theater name: "+this.nameOfTheater+"\nNumber of Seats: "+this.seatingCapacity;
    }
}