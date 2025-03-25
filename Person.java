public class Person {
   protected String name;
   protected int contactNumber;
   protected String address;
   // Constructor
   public Person(String name, int contactNumber, String address) {
       this.name = name;
       this.contactNumber = contactNumber;
       this.address = address;
   }
   // Getters
   public String getName() {
       return name;
   }
   public int getContactNumber() {
       return contactNumber;
   }
   public String getAddress() {
       return address;
   }
   // Display person details
   public void displayDetails() {
       System.out.println("Name: " + this.name);
       System.out.println("Contact number: " + this.contactNumber);
       System.out.println("Address: " + this.address);
   }
}
