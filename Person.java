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

	// Setters (needed for updateDetails)
	public void setName(String name) {
		this.name = name;
	}

	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	// Update details
	public void updateDetails(String newName, int newContactNumber, String newAddress) {
		setName(newName);
		setContactNumber(newContactNumber);
		setAddress(newAddress);
	}
	
	// Display person details
	public void displayDetails() {
		System.out.println("Name: " + name);
		System.out.println("Contact number: " + contactNumber);
		System.out.println("Address: " + address);
	}
}
