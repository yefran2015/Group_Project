import java.io.Serializable;
import java.util.Iterator;

/**
 *Serves as a specific customer that will have a
 *first and last name as well as a customer id,
 *address along with a phone number. This class
 *will also hold a CollectionList of type CreditCard 
 *to hold the credit cards that each customer may
 *have.
 *
 * @author Taylor, Andrey, Chelsea, Jean
 */
public class Customer implements Comparable<Customer>,Serializable {

    /**
     * Declared variables
     */
    private static final long serialVersionUID = 1L;
    private String firstName;
    private String lastName;
    private int customerID;
    private String address;
    private String phone;
    private static int nextCustomersId=1000;
    private CollectionList<CreditCard> cards;
    
    /**
     * This constructor is taking CustomerId, first name, last name, address, phone, and credit card as input
     * and creates customer object. 
     * @param customerID -- initial Customer id
     * @param firstName -- initial first name of customer
     * @param lastName -- initial  last name of customer
     * @param address -- initial address of customer
     * @param phone -- initial phone of customer
     * @param card -- initial credit card of customer
     */
    public Customer(int customerID, String firstName,String lastName,String address,
                    String phone, CreditCard card) {
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerID = customerID;
        this.address = address;
        this.phone = phone;
        this.cards = new CollectionList<CreditCard>();
        this.cards.add(card);
    }
    
    public static int getCustomersId() {
		return nextCustomersId;
	}

	public static void setCustomersId(int nextCustomersId) {
		Customer.nextCustomersId = nextCustomersId;
	}

	/**
     * This method is setting new first name for customer.
     * @param firstName -- new first name of customer
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * This method is setting new last name for customer.
     * @param lastName -- new last name of customer
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    /**
     * This method is setting new customer ID for customer.
     * @param customerId -- new customer id for customer
     */
    public void setCustomerID(int customerId) {
        this.customerID = customerId;
    }
    
    /**
     * This method is setting new address for customer.
     * @param address -- new customers address
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
     * This method is setting new phone for customer.
     * @param phone -- new customers phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    /**
     * Adds a credit card to this customer
     * @param card -- additional credit card for customer
     */
    public void addCreditCard(CreditCard card) {
        this.cards.add(card);
    }
    
    /**
     * Removes all credit cards from customer 
     */
    public void removeAllCreditCards() {
        Iterator<CreditCard> iterator = cards.iterator();
        
        cards.remove(new CreditCard(iterator.next().getCardNumber(),"",null));
        
        if(cards.size() > 0) {
            removeAllCreditCards();
        }
   }
    
    /**
     * Returns a unique id that is auto generated
     * @return unique id
     */
    public static int createCustomerID() {
        return nextCustomersId++;
    }
    
    /**
     * Returns the  first name of the customer
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }
   
    /**
     * Returns the  last name of the customer
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }
    
    /** 
     * Returns the customers id
     * @return customer id
     */
    public int getCustomerID() {
        return customerID;
    }
    
    /**
     * Returns the customer's address
     * @return address
     */
    public String getAddress() {
        return address;
    }
    
    /**
     * Returns the customer's phone number
     * @return phone number
     */
    public String getPhone() {
        return phone;
    }
   
    /** 
     * Remove a credit card unless there is only one card that exists
     * or that the user inputs a wrong card number.
     * 
     * @param number -- number of card which to delete from customer
     * 
     * @return if the customer only has one card or enters an incorrect 
     * card number it will return false, otherwise true.
     */
    public boolean removeCard(long number) {
        if(cards.search(new CreditCard(number,null,null)) == null){
            System.out.print("\nThat card does not exist in the database.\n\n");
            return false;
            
        }    
        else if(cards.size() <= 1) {
    	    System.out.println("\nUnable to delete: One card must remain "
    	                      +"on the account.\n");
    		return false;
    	}else {
    		cards.remove(new CreditCard(number,"",null));
    		System.out.println("The card was removed.\n");
    		return true;
    	}
    }
    
    /** 
     * This method is return all customer cards as collectionlist
     * @return returns a CollectionsList of Customer Credit cards
     */
    public CollectionList<CreditCard> cardList() {
        return cards;
    }
    
    /**
     * Compares two customers by their id's and tests if they are similar.
     * @return 0 for equality 1 if customers are not equal
     */
    public int compareTo(Customer customer) {
        if(this.customerID == customer.getCustomerID()) {
            return 0;
        }else if(this.customerID < customer.getCustomerID()) {
            return -1;
        }else{
            return 1;
        }
    }
    
    /**
     * Compares if input object its same customer as this.
     * @return true if Object is equal to this customer and false otherwise
     */
    @Override
    public boolean equals (Object object) {    
        if(object instanceof Customer) {
            Customer inputCus = (Customer)object;
            return (this.compareTo(inputCus)==0);
        }
        return false;
    }
    
    @Override
    public String toString() {
        String out="Customer ID: "+this.customerID + "\n"+ this.firstName+ " "
                              +this.lastName+"\nAddress: "+this.address
                              +"\nPhone: "+this.phone+"\n";
        
        for(int iterate = 0;iterate<this.cards.size();iterate++) {
            out+=cards.get(iterate)+"\n";
        }
        return out;
    }  
}
