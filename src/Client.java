import java.io.Serializable;

/**
 * Serves as a specific client that has
 * an id,name,address and a phone number.
 * There is also a uniquely auto-generated
 * id.
 * 
 *  @author Taylor, Andrey, Chelsea, Jean
 */
public class Client implements Comparable<Client>, Serializable {

    /**
     * Declared variables
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private int clientID;
    private String address;
    private String phone;
    private double balance;
    private static int nextClientId=2000;
    
    /**
     * Constructor which takes as input clientId, name,
     *  address, phone, and creates client object from it.
     * @param clientID -- initial client ID
     * @param name -- initial name
     * @param address -- initial address
     * @param phone -- initial phone
     */
    public Client(int clientID,String name
                 ,String address,String phone) {
        
        this.name = name;
        this.clientID = clientID;
        this.address = address;
        this.phone = phone;
        this.balance = 0.0;
    }
    
    /**
     * Sets the client's id
     * @param clientID - new client ID
     */
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }
    
    /**
     * Sets the client's name
     * @param name - new Name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Sets the client's address
     * @param address - new Address
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
     * Sets the client's phone number
     * @param phone - new Phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    /**
     * Sets the client's balance
     * @param balance - new Balance
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    /**
     * Returns the auto-generated id
     * @return -- unique id for this client
     */
    public static int createClientID() {
        return nextClientId++;
    }
    
    
    
    public static int getClientId() {
		return nextClientId;
	}

	public static void setClientId(int nextClientId) {
		Client.nextClientId = nextClientId;
	}

	/**
     * Returns the client's name
     * @return -- name of client
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns the client's id
     * @return -- client id
     */
    public int getClientID() {
        return clientID;
    }
    
    /**
     * Returns the client's address
     * @return -- address of client
     */
    public String getAddress() {
        return address;
    }
   
    /**
     * Return the client's phone number
     * @return -- phone number of client
     */
    public String getPhone() {
        return phone;
    }
    
    /**
     * Returns the client's balance
     * @return -- balance client has
     */
    public double getBalance() {
        return balance;
    }
    
    /**
     * Returns 0 if an input Client is equal to the current Client
     * otherwise it will return -1
     */
    public int compareTo(Client client) {
        if(this.clientID == client.getClientID()) {
            return 0;
        }else if(this.clientID < client.getClientID()) {
            return -1;
        }else{
            return 1;
        }
    }
    
    /**
     * This method is comparing input object with this client and return true if they are same or false if not.
     * 
     * @return true if passing in object is equal to our customer and false otherwise
     */
    @Override
    public boolean equals (Object object) {    
        if(object instanceof Client) {
            Client inputClnt = (Client)object;
            return (this.compareTo(inputClnt)==0);
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "\nClient ID: "+this.clientID+"\n"
               +"Client Name: "+this.name+"\n"
               +"Address: "+this.address+ "\n"
               +"Phone: "+this.phone +"\n"
               +"Balance: "+ this.balance+"\n";          
    }
}
