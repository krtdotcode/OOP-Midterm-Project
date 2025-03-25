import java.util.ArrayList;
import java.util.List;


public class Dentist extends Person {
   private int dentistId;
   private double commissionRate;
   private List<Service> servicesOffered = new ArrayList<>();
   private List<Transaction> transactions = new ArrayList<>();
   private boolean isActive;
   private static int count = 1;


   // Constructor
   public Dentist(String name, int contactNumber, String address, 
                  double commissionRate) {
       super(name, contactNumber, address);
       this.dentistId = count++;
       this.commissionRate = commissionRate;
   }
  
   // Getters
   public int getDentistId() {
       return dentistId;
   }


   public double getCommissionRate() {
       return commissionRate;
   }
  
   public List<Service> getServicesOffered() {
       return servicesOffered;
   }


   public List<Transaction> getTransactions() {
       return transactions;
   }
   public boolean isActive() {
        return isActive;
   }
   // Update Dentist Information
    public void updateDentistInfo(String name, int contactNumber,
                              String address, Double commissionRate) {
        if (name != null && !name.isEmpty()) {
            setName(name);
        }
        if (contactNumber > 0) {
            setContactNumber(contactNumber);
        }
        if (address != null && !address.isEmpty()) {
            setAddress(address);
        }
        if (commissionRate != null && commissionRate >= 0) {
            this.commissionRate = commissionRate;
        }
    }


   private void setAddress(String address) {
		this.address = address;
		
	}

   private void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	   }

private void setName(String name) {
	this.name = name;
	
}

// Add service to the dentist's list
   public void addService(Service service) {
       servicesOffered.add(service);
       service.addDentist(this);
   }


   // Add a transaction to the dentist's list
   public void addTransaction(Transaction transaction) {
       if (!transactions.contains(transaction)) {
           transactions.add(transaction);
       }
   }
   // Update Services Offered
    public void updateServices(List<Service> newServices) {
        if (newServices != null) {
            servicesOffered.clear();
            servicesOffered.addAll(newServices);
        }
    }

    // Archive Dentist Record
    public void archiveDentist() {
        this.isActive = false;
    }


    // Reactivate Dentist Record
    public void reactivateDentist() {
        this.isActive = true;
    }

   // Display dentist details
   @Override
   public void displayDetails() {
       super.displayDetails();
       System.out.println("Dentist ID: " + String.format("%04d", dentistId));
       System.out.println("Commission rate: " + commissionRate);
       System.out.println("Services offered:");
       for (Service service : servicesOffered) {
           System.out.println("- " + service.getName() + ": PHP " + service.getPrice());
       }
   }
//Updated Dentist Record


public void updateDetails(String name, String address, int contactNumber) {
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
    }

}
