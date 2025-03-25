import java.util.ArrayList;
import java.util.List;

class Service {
	private String name;
	private double price;
	private final List<Dentist> dentists; // Initialize in constructor
	
	// Constructor
	public Service(String name, double price) {
		this.name = name;
		this.price = price;
		this.dentists = new ArrayList<>(); // Initialize list
	}
	
	// Getters
	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public List<Dentist> getDentists() {
		return dentists;
	}

	// Assign multiple dentists to the service
	public void addDentist(Dentist dentist) {
		if (!dentists.contains(dentist)) {
			dentists.add(dentist);
		}
	}
	
	public String getServiceDetails() {
		return name + ": PHP " + String.format("%.2f", price); // Format price to 2 decimal places
	}
}
