import java.text.SimpleDateFormat;
import java.util.Date;

// Class representing a credit card transaction
public class CreditCardTransaction {
    public String nameHolder;
    public String expireDate;
    public String cardNumber;
    public String cvv;

    public CreditCardTransaction(String nameHolder, String expireDate, String cardNumber, String cvv) {
		this.nameHolder = nameHolder;
		this.expireDate = expireDate;
		this.cardNumber = cardNumber;
		this.cvv = cvv;
	}



	public boolean authorized() {

    boolean isAuthorized = false;
    
    // Check whether the credit card number is valid
    // assuming that a credit card number is valid if it has 16 digits
    if (cardNumber.length() == 16) {
        
        // Check whether the CVV is valid
        // assuming that a CVV is valid if it has 3 digits
        if (cvv.length() == 3) {
            
            // Check whether the expiration date is valid
            // assuming that the expiration date is valid if it is in the future
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yy");
            Date currentDate = new Date();
            Date expirationDate;
            
            try {
                expirationDate = dateFormat.parse(expireDate);
            } catch (Exception e) {
                return false;
            }
            
            if (expirationDate.after(currentDate)) {
                isAuthorized = true;
            }
        }
    }
    
    return isAuthorized;
    }
}