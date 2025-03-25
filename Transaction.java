import java.util.ArrayList;
import java.util.List;
public class Transaction {
  private String transactionId;
  private Client client;
  private List<Service> services = new ArrayList<>();
  private List<Dentist> dentists = new ArrayList<>();
 
  // Constructor
  public Transaction(String transactionId, Client client) {
      this.transactionId = transactionId;
      this.client = client;
  }
 
  public String getTransactionId() {
      return transactionId;
  }
 
  public Client getClient() {
      return client;
  }
 
  public void addService(Service service) {
	  
      services.add(service);
      // Add all associated dentists
     
      for (Dentist dentist : service.getDentists()) {
          if (!dentists.contains(dentist)) {
              dentists.add(dentist);
          }
      }
  }
 
 
//   public double calculateTotal() {
//       return services.stream().mapToDouble(Service::getPricePerUnit).sum();
//   }
 
  public double calculateTotal() { // naka error kanina kaya eto nalang muna nilagay ko not sure kung eto ba tlga purpose neto
	   double totalPrice = 0;
	   for(Service service : services) {
		   totalPrice += service.getPrice();
	   }
	   return totalPrice;
  }
 
 
  public String getServiceNames() {
      StringBuilder serviceNames = new StringBuilder();
      for (int i = 0; i < services.size(); i++) {
          serviceNames.append(services.get(i).getName());
          if (i < services.size() - 1) {
              serviceNames.append(", ");
          }
      }
      return serviceNames.toString();
  }
 
  // display details
 
  // NOTE: dinagdagan ko na ng commision rate yung per service
  // service.price() + (service.price * dentist.getCommisionRate) = total price per service
  public String displayTransactionDetails() {
      StringBuilder receipt = new StringBuilder();
     
      receipt.append("********** TRANSACTION RECEIPT **********\n");
      receipt.append(String.format("Transaction ID: %s\n", transactionId));
      receipt.append(String.format("Client: %s\n\n", client.getName()));
     
      double totalCost = 0.0;
     
      for (Dentist dentist : dentists) {
          receipt.append(String.format("Dentist: %s\n", dentist.getName()));
          receipt.append("Services Provided:\n");
          double dentistTotal = 0.0;
         
          for (Service service : services) {
              if (service.getDentists().contains(dentist)) { // Corrected for multiple dentists
                  receipt.append(String.format("  - %s: PHP %.2f\n", service.getName(), (service.getPrice() + (service.getPrice() * dentist.getCommissionRate()))));
                  dentistTotal += (service.getPrice() + (service.getPrice() * dentist.getCommissionRate()));
              }
          }
          receipt.append(String.format("Subtotal for %s: PHP %.2f\n\n", dentist.getName(), dentistTotal));
          totalCost += dentistTotal;
      }
     
      receipt.append(String.format("TOTAL COST: PHP %.2f\n", totalCost));
      receipt.append("*****************************************\n");
      return receipt.toString();
  }
}
