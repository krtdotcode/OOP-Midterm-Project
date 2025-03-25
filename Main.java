import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	private static Scanner scan = new Scanner(System.in);
	private static List<Dentist> dentists = new ArrayList<>();
	private static List<Client> clients = new ArrayList<>();
	private static List<Transaction> transactions = new ArrayList<>();
	private static List<Client> archivedClients = new ArrayList<>();

	public static void main(String[] args) {
		initializeData();
		while (true) {
			displayMenu();
			int choice = getIntInput("Enter your choice: ");
			switch (choice) {
			case 1 -> displayDentists();
			case 2 -> displayClients();
			case 3 -> addTransaction();
			case 4 -> displayTransactions();
			case 5 -> viewDentistReport();
			case 6 -> viewClientReport();
			case 7 -> archiveRecords();
			case 8 -> updateRecords();
			case 9 -> {
				System.out.println("Exiting the program. Thank you!");
				return;
			}
			default -> System.out.println("❌ Invalid choice! Please select a valid option.");
			}
		}
	}

	// Initialize Predefined Data
	private static void initializeData() {
		Dentist dentist1 = new Dentist("Dr. Smith", 123456789, "123 Dental St.", 0.10);
		Dentist dentist2 = new Dentist("Dr. Johnson", 987654321, "456 Smile Blvd.", 0.12);
		Service service1 = new Service("Fillings", 1000.00);
		Service service2 = new Service("Check-up and Cleaning", 600.00);
		Service service3 = new Service("Teeth Whitening", 500.00);
		Service service4 = new Service("Tooth Extraction", 700.00);
		dentist1.addService(service1);
		dentist1.addService(service2);
		dentist2.addService(service3);
		dentist2.addService(service4);
		Client client1 = new Client("Alice Brown", 123123123, "789 Client Rd.", 2025);
		Client client2 = new Client("Bob White", 321321321, "987 Smile Ct.", 2025);
		Transaction transaction1 = new Transaction("T-0001", client1);
		transaction1.addService(service1);
		transaction1.addService(service3);
		Transaction transaction2 = new Transaction("T-0002", client2);
		transaction2.addService(service2);
		transaction2.addService(service4);
		dentist1.addTransaction(transaction1);
		dentist2.addTransaction(transaction2);
		client1.addTransaction(transaction1);
		client2.addTransaction(transaction2);
		dentists.add(dentist1);
		dentists.add(dentist2);
		clients.add(client1);
		clients.add(client2);
		transactions.add(transaction1);
		transactions.add(transaction2);
	}

	// Display Menu
	private static void displayMenu() {
		System.out.println("\n========== Dental Clinic Management ==========");
		System.out.println("1. Display all dentists");
		System.out.println("2. Display all clients");
		System.out.println("3. Add a new transaction");
		System.out.println("4. Display all transactions");
		System.out.println("5. View transactions of a specific dentist");
		System.out.println("6. View transactions of a specific client");
		System.out.println("7. Archive records");
		System.out.println("8. Update records");
		System.out.println("9. Exit");
		System.out.println("=============================================");
	}

	// Archive Records - kaye
	private static void archiveRecords() {
		System.out.println("\n========== Archive Records ==========");
		System.out.println("1. Archive a client");
		System.out.println("2. Archive a dentist");
		System.out.println("======================================");
		int choice = getIntInput("Select an option: ");
		if (choice == 1) {
			archiveClient();
		}
	}

	private static void archiveClient() {
		System.out.print("Enter client ID to archive (YEAR-XXXX format): ");
		String input = scan.next();
		for (int i = 0; i < clients.size(); i++) {
			Client client = clients.get(i);
			String clientId = client.getRegistrationYear() + "-" + String.format("%04d", client.getClientId());
			if (clientId.equals(input)) {
				archivedClients.add(client); // Move client to archived list
				clients.remove(i); // Remove from active list
				System.out.println("Client " + clientId + " has been archived.");
				return;
			}
		}
		System.out.println("Client not found.");
	}

	// Update Records - kaye
	private static void updateRecords() {
		System.out.println("\n========== Update Records ==========");
		System.out.println("1. Update client");
		System.out.println("2. Update dentist");
		System.out.println("====================================");
		int choice = getIntInput("Select an option: ");
		if (choice == 1) {
			updateClientDetails();
		}
	}

	private static void updateClientDetails() {
		System.out.print("Enter client ID to update (YEAR-XXXX format): ");
		String input = scan.next();
		for (Client client : clients) {
			String clientId = client.getRegistrationYear() + "-" + String.format("%04d", client.getClientId());
			if (clientId.equals(input)) {
				System.out.print("Enter new name: ");
				scan.nextLine();
				String newName = scan.nextLine();
				System.out.print("Enter new contact number: ");
				int newContactNumber = getIntInput("");
				System.out.print("Enter new address: ");
				scan.nextLine();
				String newAddress = scan.nextLine();
				client.updateDetails(newName, newContactNumber, newAddress);
				System.out.println("✅ Client details updated successfully.");
				return;
			}
		}
		System.out.println("❌ Client not found.");
	}

	// Display all Dentists
	private static void displayDentists() {
		System.out.println("\n========== List of Dentists ==========");
		System.out.printf("%-5s %-20s %-15s %-25s %-10s\n", "ID", "Name", "Contact No.", "Address", "Commission Rate");
		System.out.println("---------------------------------------------------------------------------------");
		for (int i = 0; i < dentists.size(); i++) {
			Dentist dentist = dentists.get(i);
			System.out.printf("%-5s %-20s %-15s %-25s %.2f%%\n", String.format("%04d", i + 1), // Dentist IDs in XXXX
																								// format
					dentist.getName(), dentist.getContactNumber(), dentist.getAddress(),
					dentist.getCommissionRate() * 100);
		}
		System.out.println("---------------------------------------------------------------------------------");
	}

	// Display all Clients
	private static void displayClients() {
		System.out.println("\n========== List of Clients ==========");
		System.out.printf("%-10s %-20s %-15s %-25s %-10s\n", "ID", "Name", "Contact No.", "Address", "Year");
		System.out.println("---------------------------------------------------------------------------------");
		if (clients.isEmpty()) {
			System.out.println("No active clients available.");
		} else {
			for (int i = 0; i < clients.size(); i++) {
				Client client = clients.get(i);
				System.out.printf("%-10s %-20s %-15s %-25s %d\n",
						client.getRegistrationYear() + "-" + String.format("%04d", client.getClientId()), // Keeping the
																											// proper
																											// Client ID
																											// format
						client.getName(), client.getContactNumber(), client.getAddress(), client.getRegistrationYear());
			}
		}
		System.out.println("---------------------------------------------------------------------------------");
	}

	// Add a New Transaction
	private static void addTransaction() {
		System.out.println("\n========== Add a New Transaction ==========");
		displayClients();
		int clientIndex = getIntInput("Select a client by number (1-" + clients.size() + "): ") - 1;
		if (clientIndex < 0 || clientIndex >= clients.size()) {
			System.out.println("❌ Invalid client selection!");
			return;
		}
		Client client = clients.get(clientIndex);
		displayDentists();
		int dentistIndex = getIntInput("Select a dentist by number (1-" + dentists.size() + "): ") - 1;
		if (dentistIndex < 0 || dentistIndex >= dentists.size()) {
			System.out.println("❌ Invalid dentist selection!");
			return;
		}
		Dentist dentist = dentists.get(dentistIndex);
		Transaction transaction = new Transaction("T-" + String.format("%04d", transactions.size() + 1), client);
		System.out.println("Available services offered by " + dentist.getName() + ":");
		List<Service> services = dentist.getServicesOffered();
		for (int i = 0; i < services.size(); i++) {
			System.out.printf("%d. %-25s %.2f\n", (i + 1), services.get(i).getName(), services.get(i).getPrice());
		}
		while (true) {
			int serviceIndex = getIntInput("Select a service by number (1-" + services.size() + ") or 0 to finish: ")
					- 1;
			if (serviceIndex == -1)
				break;
			if (serviceIndex < 0 || serviceIndex >= services.size()) {
				System.out.println("❌ Invalid service selection!");
				continue;
			}
			transaction.addService(services.get(serviceIndex));
		}
		if (transaction.calculateTotal() > 0) {
			dentist.addTransaction(transaction);
			client.addTransaction(transaction);
			transactions.add(transaction);
			System.out.println("✅ Transaction added successfully!");
		} else {
			System.out.println("❌ Transaction must include at least one service!");
		}
	}

	// Display all Transactions
	private static void displayTransactions() {
		if (transactions.isEmpty()) {
			System.out.println("\nNo transactions available.");
			return;
		}
		System.out.println("\n========== List of Transactions ==========");
		for (Transaction transaction : transactions) {
			System.out.println(transaction.displayTransactionDetails());
		}
	}

	public static void viewDentistReport() {
		System.out.println("\n========== List of Dentists ==========");
		for (Dentist dentist : dentists) {
			System.out.println(dentist.getDentistId() + ". " + dentist.getName());
		}
		int dentistIndex = getIntInput("Select a dentist by number (1-" + dentists.size() + "): ") - 1;
		if (dentistIndex < 0 || dentistIndex >= dentists.size()) {
			System.out.println("❌ Invalid dentist selection!");
			return;
		}

		Dentist dentist = dentists.get(dentistIndex);
		List<Transaction> transactions = dentist.getTransactions();

		List<Client> totalClients = new ArrayList<>();
		for (Transaction tr : transactions) {
			if (!totalClients.contains(tr.getClient())) {
				totalClients.add(tr.getClient());
			}
		}

		double totalPayment = 0;
		for (Transaction tr : transactions) {
			totalPayment += tr.calculateTotal();
		}
		System.out.println("\n---------------------------------------------------------------------------------");
		System.out.println("Summarized Dentist Report of " + dentist.getName());
		System.out.println("Clients: " + totalClients.size());
		System.out.println("Total Transactions Done: " + transactions.size());
		System.out.println("Total Payment: " + totalPayment);
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("Transaction Receipts of " + dentist.getName());
		for (Transaction tr : transactions) {
			System.out.println(tr.displayTransactionDetails());
		}
	}

	// aayusin pa
	public static void viewClientReport() {
		System.out.println("\n========== List of Clients ===========");
		for (Client client : clients) {
			System.out.println(client.getClientId() + ". " + client.getName());
		}
		int clientIndex = getIntInput("Select a client by number (1-" + clients.size() + "): ") - 1;
		if (clientIndex < 0 || clientIndex >= clients.size()) {
			System.out.println("❌ Invalid client selection!");
			return;
		}

		Client client = clients.get(clientIndex);
		List<Transaction> transactions = client.getTransactions();
		System.out.println("\n---------------------------------------------------------------------------------");
		System.out.println("Summarized Client Report of " + client.getName());
		System.out.println("Total Transactions Done: " + transactions.size());
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("Transaction Receipts of " + client.getName());
		for (Transaction tr : transactions) {
			System.out.println(tr.displayTransactionDetails());
		}
	}

	// Utility/Input-Handling Methods
	private static int getIntInput(String message) {
		while (true) {
			try {
				System.out.print(message);
				return Integer.parseInt(scan.nextLine().trim());
			} catch (NumberFormatException e) {
				System.err.println("❌ Invalid input! Please enter a valid number.");
			}
		}
	}

	private static double getDoubleInput(String message) {
		while (true) {
			try {
				System.out.print(message);
				return Double.parseDouble(scan.nextLine().trim());
			} catch (NumberFormatException e) {
				System.err.println("❌ Invalid input! Please enter a valid number.");
			}
		}
	}

}
