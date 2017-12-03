package ui;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import business.Product;
import business.ProductDB;
//import business.PRLineItemDB;
//import business.Product;
//import business.ProductDB;
import business.PurchaseRequest;
import business.PurchaseRequestDB;
import business.PurchaseRequestLineItem;
import business.Status;
import business.User;
import business.UserDB;
import Util.Console;
import db.DBUtil;

public class PRSConsoleApp {
	//private static UserDB uDB = null; //creates a new instance of the User Database 
	private static User validatedUser = null;
	private static PurchaseRequest purchaseRequest = null; 
	private static ProductDB productDB = null;
	//private static PRLineItemDB PRDB = null;
	private static PurchaseRequestLineItem prlineitem = null;
	
	
	public static void main(String[] args) {
		//uDB = new UserDB();
		//productDB = new ProductDB();
		System.out.println("Welcome to the Purchase Request application");
			
		String choice = "";
		
		while (!choice.equalsIgnoreCase("exit")) {
			displayMenu();
			boolean loggedIn = validatedUser != null;
			choice = Console.getString("Please select an option: ");
			if (!loggedIn) {
				if (choice.equalsIgnoreCase("login")) {
					login();
				}else {
						System.out.println("Please select login to log into purchase request system.");
					}
				}else { if (choice.equalsIgnoreCase("all")) {
					listUsers();
				}else if (choice.equalsIgnoreCase("add")) {
					if (validatedUser.isAdmin())
					addUser();
					else
						System.out.println("You do not have access to this command.");
					
		//Uncomment below when reviewRequests method is written.
			//	}else if (choice.equalsIgnoreCase("review")){
				//	if (validatedUser.isReviewer())
					//reviewRequests();
					//else 
						//System.out.println("You do not have access to this command");
				}else if (choice.equalsIgnoreCase("del")) {
					deleteUser();
				}else if (choice.equalsIgnoreCase("logout")) {
					logout();
				}else if (choice.equalsIgnoreCase("newpr")) {
					enterNewPR();
				}
			}
		}
		System.out.println("User session ended");	
			
	}
	
	public static void displayMenu() {
		System.out.println("\nMenu");
		if (validatedUser!=null) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("all	-	Display All Users");
			if (validatedUser.isAdmin()) {
				System.out.println("add	-	Add a User");
				System.out.println("del	-	Delete a User");
			}
			System.out.println("logout	-	Logout of application");
			System.out.println("newpr	-	Begin a new purchase request");
			if (validatedUser.isReviewer()) {
				System.out.println("review	-	Review submitted purchase requests");
			}
		}else
		    {
			System.out.println("login   -	Login to the application");
		    }
			System.out.println("exit	-	Exit application");
			System.out.println();
			}
	
	
	public static void login() {
		System.out.println("User Login");
		String userName = Console.getString("Username: ", 20);
		String password = Console.getString("Password: ");
		validatedUser = UserDB.authenticateUser(userName, password);
		if (validatedUser!= null) {
			System.out.println("\nSuccess: you are logged in.");
			System.out.println(validatedUser);
			}else {System.out.println("Invalid username/password combination");
		}
	}
	
	public static void enterNewPR() {
		
		
		purchaseRequest = new PurchaseRequest();
		User user = validatedUser;
		Status status = new Status (1, "New");
		//purchaseRequest.setStatus(status);
		System.out.println("Enter new purchase request details");
		String desc = Console.getLine("Description: ");
		String justification = Console.getLine("Justification: ");
		String dateNeededStr = Console.getString("Date needed by (YYYY-MM-DD):	");
		String dlvMode = Console.getString("Delivery Mode:   \n", 25);
		LocalDate dateNeeded = LocalDate.parse(dateNeededStr);
		//purchaseRequest.setDescription(desc);
		//purchaseRequest.setJustification(justification);
		//purchaseRequest.setDateNeeded(dateNeeded);
		//purchaseRequest.setDeliveryMode(dlvMode);
		System.out.println("Please create line item(s).");
		System.out.println("\n");
		
		//Add line items
		System.out.println("List of products:");
		System.out.println("~~~~~~~~~~~~~~~~~~");
		for (Product p : ProductDB.getAllProducts()) {
			System.out.println(p);
		}
		String choice = "y";
		while (choice.equalsIgnoreCase("y")) {
			int productID = Console.getIntWithinRange(
					"Enter the product ID corresponding to the product you" + " would like to add:", 0, 13);
			int quantity = Console.getInt("Enter quantity: ");
			PurchaseRequestLineItem prli = new PurchaseRequestLineItem(0, 0, productID, quantity);
			//purchaseRequest.addLineItem(prli);
			System.out.println("Line item added.");
			System.out.println();
			choice = Console.getString("Add another line item? (y/n): ");
		}
			LocalDate submittedDate = LocalDate.now();
			//purchaseRequest.setSubmittedDate(submittedDate);
			double prsum = 0.0;
			String prsumFormatted ="";
			double productprice = 0.0;
			int qty = 0;
			NumberFormat currency = NumberFormat.getCurrencyInstance();
			for (PurchaseRequestLineItem purchaserequestlineitem : purchaseRequest.getLineItems()) {
				int productId = purchaserequestlineitem.getProductID();
				Product p = ProductDB.getProductByID(productId);
				qty = purchaserequestlineitem.getQuantity();
				System.out.println(p);
				// price is going to be within the product object.
				productprice = p.getPrice();
				// for each prli, add to sum (price * qty)
				prsum += productprice*qty;
				prsumFormatted = currency.format(prsum);
			}
			
			//purchaseRequest.setTotal(prsumFormatted);
			System.out.println(purchaseRequest.toString());
			
			PurchaseRequest pr = new PurchaseRequest(user, desc, justification, dateNeeded, dlvMode, status, 
					prsumFormatted, submittedDate);
			
			if (PurchaseRequestDB.addPurchaseRequest(pr)) {
				System.out.println("Purchase Request created submitted.");
		
			
	}
	}
	
//	public static void addPRLineItems() {
//		System.out.println("List of products:");
//		System.out.println("~~~~~~~~~~~~~~~~~~");
//		for (Product p : ProductDB.getAllProducts()) {
//			System.out.println(p);
//		}
//		String choice = "y";
//		while (choice.equalsIgnoreCase("y")) {
//			int productID = Console.getIntWithinRange(
//					"Enter the product ID corresponding to the product you" + " would like to add:", 0, 13);
//			int quantity = Console.getInt("Enter quantity: ");
//			PurchaseRequestLineItem prli = new PurchaseRequestLineItem(0, 0, productID, quantity);
//			purchaseRequest.addLineItem(prli);
//			System.out.println("Line item added.");
//			System.out.println();
//			choice = Console.getString("Add another line item? (y/n): ");
//		}

	
	
	public static void summarizePR() {
		LocalDate submittedDate = LocalDate.now();
		purchaseRequest.setSubmittedDate(submittedDate);
		double prsum = 0.0;
		String prsumFormatted = "";
		double productprice = 0.0;
		int qty = 0;
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		for (PurchaseRequestLineItem prli : purchaseRequest.getLineItems()) {
			int productId = prli.getProductID();
			Product p = ProductDB.getProductByID(productId);
			qty = prli.getQuantity();
			System.out.println(p);
			// price is going to be within the product object.
			productprice = p.getPrice();
			// for each prli, add to sum (price * qty)
			prsum += productprice*qty;
			prsumFormatted = currency.format(prsum);
		}
		
		purchaseRequest.setTotal(prsumFormatted);
		System.out.println(purchaseRequest.toString());
		
		//determine total of entire PR. Looping through line items. 
		
		
	}
	
	
	public static void listUsers() {
		ArrayList<User> users = UserDB.getAllUsers();
		System.out.println("All users: ");
		for (User u:users.subList(1, users.size())) //skips first iteration, which is a dummy placeholder.
			{
			System.out.println(u);
		}
		System.out.println();
	}
	
	private static void addUser() {
		System.out.println("Please enter the attributes of a new user.");
	
		//int id = Console.getInt("User ID: ");
		String username = Console.getString("userName: ", 20);
	    String password = Console.getString("password: ");
		String firstName = Console.getString("firstName: ");
		String lastName = Console.getString("lastName: ");
		String phone = Console.getString("phoneNumber: ");
		String email = Console.getString("email: ");
		boolean reviewer = Console.getBoolean("reviewer(true or false): ");
		boolean admin = Console.getBoolean("admin (true or false): ");
		User u = new User(username, password, firstName, lastName, phone, email, reviewer, admin);
		u.setDateCreated(new Timestamp(System.currentTimeMillis()));
				
		if (UserDB.addUser(u)) {
			System.out.println("User created successfully!");
		}
		System.out.println("passing u to print statement = "+u);
		System.out.println();
	}
	
	public static void deleteUser() {
	
		ArrayList<User> users = UserDB.getAllUsers();
		System.out.println("All users: ");
		for (User u:users.subList(1, users.size())) //skips first iteration, which is a dummy placeholder.
			{
			System.out.println(u);
			}
		int userID = Console.getInt("Enter ID of user you would like to delete: ");
		User usr = UserDB.getUserByID(userID); //shows the list of users so you can confirm entries exist.
		if (usr == null) {
			System.out.println("User does not exist.");	
		}
		if (UserDB.deleteUser(usr)) {
			System.out.println(usr.getUsername()+"successfully deleted.");
		}
		System.out.println();
		}
	
	public static void logout() {
		String ustring = validatedUser.getUsername();
		validatedUser=null;
		System.out.println("User '"+ustring+"' logged out.");
	}

}
