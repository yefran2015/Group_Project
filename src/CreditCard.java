import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *Serves as a credit card for a customer that will have a
 *card number,the customer's name and a expiration date 
 *
 *  @author Taylor, Andrey, Chelsea, Jean
 */
public class CreditCard implements Comparable<CreditCard>, Serializable {
    
    /**
     *Declared local variables 
     */
    private static final long serialVersionUID = 1L;
    private long cardNumber;
    private String nameOnCard;
    private Calendar expirationDate;
   
    /**
     * Constructor which takes car number, name on card, and expiration date and 
     * creates credit card object
     * @param cardNumber -- initial card number
     * @param nameOnCard -- initial name on card
     * @param expiration -- initial expiration date
     */
    public CreditCard(long cardNumber, String nameOnCard, GregorianCalendar expiration) {
        this.cardNumber = cardNumber;
        this.nameOnCard = nameOnCard;
        this.expirationDate = expiration;
    }
    
    /**
     * This method is setting new cards number.
     * @param cardNumber -- new Card number
     */
    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    /**
     * Sets the name associated with the credit card
     * @param nameOnCard -- new Name for this card
     */
    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }
    
    /**
     * Sets the expiration date associated with the credit card
     * @param expirationDate -- new expiration date for this card
     */
    public void setExpirationDate(Calendar expirationDate) {
        this.expirationDate = expirationDate;
    }
    
    /**
     * Returns the number associated with the credit card.
     * @return card number
     */
    public long getCardNumber() {
        return cardNumber;
    }
    
    /**
     * Returns the name on the credit card.
     * @return name on this credit card
     */
    public String getNameOnCard() {
        return this.nameOnCard;
    }
   
    /**
     * Returns the expiration date
     * @return expiration date of the credit card.
     */
    public Calendar getExpirationDate() {
        return expirationDate;
    }
    
    /**
     * Returns true or false depending if the comparison of an 
     * object is of type CreditCard
     * 
     * @return true if the input Object is of type CreditCard else false
     */
    @Override
    public boolean equals (Object object) {    
        if(object instanceof CreditCard) {
            CreditCard inputCreditCard = (CreditCard)object;
            return (this.compareTo(inputCreditCard)==0);
        }
        return false;
    }
    
    /**
     * Returns 0 if an input CreditCard is equal to the current CreditCard
     * otherwise it will return -1
     */
    public int compareTo(CreditCard card) {
        if(this.cardNumber == card.getCardNumber()) {
            return 0;
        }
        return -1;
    }
    
    @Override
    public String toString() {
        return "\nCard Number: "+this.cardNumber+"\nName On Card: "+this.nameOnCard
               +"\nExpiration: "+this.expirationDate.get(Calendar.MONTH)+"/"
               +this.expirationDate.get(Calendar.YEAR)+"\n";
    }
}
