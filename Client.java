import java.util.ArrayList;
import java.util.List;


public class Client extends Person {
   private int clientId;
   private static int count = 1;
   private int registrationYear;
   private List<Transaction> transactions = new ArrayList<>();


   // Constructor
   public Client(String name, int contactNumber, String address, int 
                   registrationYear) {
       super(name, contactNumber, address);
       this.clientId = count++;
       this.registrationYear = registrationYear;
   }


   // Getters
   public int getRegistrationYear() {
		return registrationYear;
   }
	
   public List<Transaction> getTransactions() {
       return transactions;
   }
  
   // Add a transaction to the client's list
   public void addTransaction(Transaction transaction) {
       if (!transactions.contains(transaction)) {
           transactions.add(transaction);
       }
   }
   // Display client details
   @Override
   public void displayDetails() {
       super.displayDetails();
       System.out.println("Client ID: " + registrationYear + "-" +               
                              String.format("%04d", clientId));
       System.out.println("Transactions:");


       for (Transaction transaction : transactions) {
             System.out.println("- Transaction ID: " + 
                               transaction.getTransactionId());
       }
   }
}
