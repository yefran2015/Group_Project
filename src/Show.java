import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/** 
 * Serves as a specific Show that has a name,client id,
 * a start and end date for the showing.
 *  
 *  @author Taylor, Andrey, Chelsea, Jean
 */
public class Show implements Comparable<Show>, Serializable {
    
    /**
     * Declared variables
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private int clientID;
    private Calendar[] dateRange;
    private double regularTicketPrice;
    
    /**
     * Constructor with the following parameters
     * @param showsName -- initial show name
     * @param clientID -- initial shows client ID
     * @param startDate -- initial start Date
     * @param endDate -- initial end Date
     * @param regularTicketPrice -- initial ticket price
     */
    public Show(String showsName, int clientID, GregorianCalendar startDate
               ,GregorianCalendar endDate, double regularTicketPrice) {
        
        this.name = showsName;
        this.clientID = clientID;
        dateRange = new GregorianCalendar[2];
        dateRange[0] = startDate;
        dateRange[1] = endDate;
        this.regularTicketPrice = regularTicketPrice;
    }

    /**
     * Sets the name of the show
     * @param name -- new name of show
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the id of the client associated with a specific show
     * @param clientID -- new Client id for show
     */
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }
    
    /**
     * Returns the client's id
     * @return client id
     */
    public int getClientID() {
        return clientID;
    }

    /**
     * Returns the name of the show
     * @return show's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * A calendar for the start date of the show
     * @return Calendar
     */
    public Calendar getStartDate() {
        return dateRange[0];
    }
    
    /**
     * A calendar for the end date of the show
     * @return Calendar
     */
    public Calendar getEndDate() {
        return dateRange[1];
    }
    
    /**
     * Returns true or false depending if input object equal to this show 
     * 
     * @return true if the input Object is this show and false if is not
     */
    @Override
    public boolean equals (Object object) {    
        if(object instanceof Show){
            Show inputShow = (Show)object;
            return (this.compareTo(inputShow)==0);
        }
        return false;
    }
    
    /**
     * Returns 0 if an input Show is equal to the current Show
     * otherwise it will return -1
     */
    public int compareTo(Show show) {
        if(this.name.compareToIgnoreCase(show.getName())==0) {
            return 0;
        }
        return -1;
    }
    
    @Override
    public String toString() {
        return "Show: "+this.name+" belongs to client with ID "+this.clientID+"\nStarts: "
                +this.dateRange[0].get(Calendar.YEAR)+"/" +this.dateRange[0].
                get(Calendar.MONTH)+"/"+this.dateRange[0].get(Calendar.DATE)
                +", Ends: "+this.dateRange[1].get(Calendar.YEAR)+"/"
                +this.dateRange[1].get(Calendar.MONTH)+"/"+this.dateRange[1]
                .get(Calendar.DATE)+"\nRegular ticket price: $"+regularTicketPrice+"\n";
    }
}
