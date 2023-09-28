package aug30;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class keeps track on computers
 * 
 * @author Suman Tanwar
 *  written by : Suman 
 * 
 */
public class Inventory {

	static Scanner scan = new Scanner(System.in);
	static int computerStore = 0;   // maximum number of computers have in store 
	static int noOfComputerUserEnter = 0; // This attribute will store number of computers.
											
											 
	static Computer[] inventory; // Array to store the computer information  by user .....
	
	final static String ownerPassword = "password"; //Attribute containing the password

	
	// search all the computers by brand
	
	
	public static void findComputersBy(String brand) {
		boolean flag = false;
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i].getBrand().equals(brand)) {
				flag = true;
				inventory[i].showInfoByIndex(i +1);
			}
		}
		if (!flag) {
			System.out.println("Computer does not exists of this brand " + brand);
		}
	}

	
	 // find Cheaper asked by user.....
	 
	
	public static void findCheaperThan(double price) {
		boolean flag = false;
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i].getPrice() < price) {
				flag = true;
				inventory[i].showInfoByIndex(i + 1);
			}
		}
		if (!flag) {
			System.out.println("No computer is less than $" + price + "price");
		}
	}
	/**
	 * storeInventoryInFile store all the data of inventory in text file
	 */
	public static void storeInventoryInFile()
	{
		 PrintWriter pw = null;
		 try {
       	  pw = new PrintWriter(new FileOutputStream("computer.txt"));
         }catch(FileNotFoundException e)
         {
       	  System.out.println("file not found");
         }
     
		for (int i = 0; i < inventory.length; i++) {
			
			if (inventory[i] != null) {
				pw.println();	
				pw.println("Computer # " + (i + 1));
				pw.println("Brand : " + inventory[i].getBrand());
				pw.println("Computer Model : " + inventory[i].getModel());
				pw.println("SN : " + inventory[i].getSN());
				pw.println("Computer Price : $" + inventory[i].getPrice());
			
				pw.println("================================");
				pw.println();
			}
		}
		pw.close();
	}
	

	
	  // mainMenu {the action list}
	  //1.New computers entry (password required)
	  //2.Change information of a computer (password required) 
	  //3.Display  all computers by a specific brand 
	  //4.Display all computers under a certain price 
	  // 5.Quit
	 
	  //and based on that allow owner to choice the action based on owner choice that
	  // action will be performed to keep track
	
	public static void mainMenu() {

		System.out.println();
		System.out.println();
		System.out.println("What do you want to do?\r\n" 
		                   + "1.Enter new computers (password required)\r\n"
				           + "2.Change information of a computer (password required)\r\n"
				           + "3.Display all computers by a specific brand\r\n"
				           + "4.Display all computers under a certain a price.\r\n"
				           + "5.Quit");

		System.out.println("Please enter your choice====>>>> ");
		int choice  = 0;
		 boolean isflag = false;
			while(!isflag)
			{
			try {
				choice = scan.nextInt();
				isflag = true;
			}catch(InputMismatchException e) {
		         System.out.println( "This is not a good value.....");    	                   
		         scan.nextLine(); 
			}
		  }
		
	
		while (choice < 1 || choice > 5) {
			System.out.println("Enter your choice b/w 1 and 5 ====>>>> ");
			choice = scan.nextInt();
		}

		switch (choice) {
		case 1:
			if (isValidPassword()) {
				System.out.println("How many computer devices you want ???? ");
				
				int noOfComputer  = 0;
				 boolean isnoOfComputer = false;
					while(!isnoOfComputer)
					{
					try {
						noOfComputer = scan.nextInt();
						isnoOfComputer = true;
					}catch(InputMismatchException e) {
				         System.out.println( "This is not a good value.....");    	                   
				         scan.nextLine(); 
					}
				  }
				
				
				while (choice < 0) {
					System.out.println("Please tell how many computers you want?????? ");
					noOfComputer = scan.nextInt();
				}

				
				
				noOfComputerUserEnter = noOfComputerUserEnter + noOfComputer;
				if (noOfComputerUserEnter <= computerStore) {
					for (int i = 0; i < noOfComputerUserEnter; i++) {

						if (inventory[i] == null) {
							System.out.println("Enter Information for Computer (brand,model,SN,price) for " + (i + 1));
							inventory[i] = new Computer(scan.next(), scan.next(), scan.nextLong(), scan.nextDouble());

						}
					}

				} else {
					if (noOfComputerUserEnter > 0 && noOfComputerUserEnter != computerStore) {
						noOfComputerUserEnter = noOfComputerUserEnter - noOfComputer;
					}

					System.out.println(" Sorry we dont have space to add computer in inventory ?");
					mainMenu();
				}

			}
			mainMenu();
			break;

		case 2:
			if (isValidPassword()) {
				System.out.println();
				System.out.println("Enter number which computer you want to modify ");
				int index = 0;
				boolean isInt = false;
				while(!isInt)
				{
				try {
					
					index = scan.nextInt();	
					
					isInt = true;
					
				}catch(InputMismatchException e) {
			         System.out.println( "this is bad value");                       
			         scan.nextLine();
				}
				}
				
				

				if (computerStore > 0) {
					if (index > 0 && index <= noOfComputerUserEnter) {
						inventory[index - 1].showInfoByIndex(index);
						changeAttribute(index - 1);

					} else if (index > 0 && index <= computerStore) {
						System.out.println("Do you want another entry or quit this operation  (Y or N) ? ");
						char confirm = scan.next().charAt(0);
						if (confirm == 'y') {
						
							noOfComputerUserEnter = noOfComputerUserEnter + 1;
						
							System.out.println("Enter Information for Computer (brand,model,SN,price) for "
									+ noOfComputerUserEnter);
							int addindex = noOfComputerUserEnter - 1 ;
													
							inventory[addindex] = new Computer(scan.next(), scan.next(), scan.nextLong(),
									scan.nextDouble());

						}
					} else {
						System.out.println("You have entered invalid number");
					}
				} else {
					System.out.println("Sorry.. you have not  any computer in store!");
				}

			}
			mainMenu();
			break;

		case 3:
			System.out.println();
			System.out.println("Enter a brand name to search");
			String searchBrand = scan.next();
			if (searchBrand.length() > 0) {
				findComputersBy(searchBrand);
			}
			mainMenu();
			break;

		case 4:
			System.out.println();
			System.out.println("Enter a price value to search");
			double searchPrice = 0;
			boolean isflag1 = false;
			while(!isflag1)
			{
			 try {
				
				searchPrice = scan.nextDouble();
				isflag1 = true;
				
			 }catch(InputMismatchException e) {
		         System.out.println( "This is not a good  value....");    
		         scan.nextLine(); 
			 }
			}
				
			findCheaperThan(searchPrice);
			mainMenu();
			break;

		case 5:
			storeInventoryInFile();
			System.out.println("Thank you.... ");
			scan.close();
			break;

		}
	}

	/**
	 * changeAttribute method prompt the user which attribute they wish to change by
	 * displaying the menu for the particular computer store in array. (brand ,
	 * model , SN , price and quit this operation and go back to the main menu)
	 * based on choice allow user to modify the attribute value by entering the
	 * value
	 * 
	 * @param index accepts the index of computer store in array
	 */
	public static void changeAttribute(int index) {
		System.out.println();
		System.out.println("What information would you like to change?\r\n" + "1.brand\r\n" + "2.model\r\n" + "3.SN\r\n"
				+ "4.price.\r\n" + "5.Quit");

		System.out.println("Please enter your choice==>>>> ");
	
		
		int choice  = 0;
		 boolean isflag = false;
			while(!isflag)
			{
			try {
				choice = scan.nextInt();
				isflag = true;
			}catch(InputMismatchException e) {
		         System.out.println( "This is not a good value");    	                   
		         scan.nextLine(); 
			}
		  }
		
		
		while (choice < 1 || choice > 5) {
			System.out.println("Enter your choice b/w 1 and 5 ====>>>>  ");
			choice = scan.nextInt();
		}
		


		switch (choice) {
		case 1:
			System.out.println("Enter brand name to modify : ");
			inventory[index].setBrand(scan.next());
			changeAttribute(index);
			break;
		case 2:
			System.out.println("Enter model name to modify : ");
			inventory[index].setModel(scan.next());
			changeAttribute(index);
			break;
		case 3:
			System.out.println("Enter Serial number to modify : ");
			long sn  = 0;
			boolean isLong = false;
			while(!isLong)
			{
			 try {
				sn  = scan.nextLong();
						isLong = true;
			 }catch(InputMismatchException e) {
		         System.out.println( "This is not a good value");    	                   
		         scan.nextLine(); 
			 }
		    }
			
			inventory[index].setSN(sn);
			changeAttribute(index);
			break;
		case 4:
			System.out.println("Enter price to modify : ");
			double price  = 0;
			boolean isPrice = false;
			while(!isPrice)
			{
				try {
				 price  = scan.nextLong();
				isPrice = true;
				}catch(InputMismatchException e) {
		         System.out.println( "This is not a good value");    	                   
		         scan.nextLine(); 
				}
			}
			
			
			inventory[index].setPrice(price);
			changeAttribute(index);
			break;

		default:
			break;

		}

	}

	/**
	 * isValidpassword will validate the password enter by the user with
	 * ownerPassword attribute value password is required for two main menu options
	 * only The computerstore owner has 3 tries to enter the correct password. After
	 * the 3rd illegal entry displaying the main menu
	 * 
	 * @return type is boolean if the password is valid than it will return true
	 *         else false
	 */
	public static boolean isValidPassword() {
		int pwdEnter = 0;
		System.out.println("Please Enter your password::: ");
		String pwd = scan.next();
		while (pwdEnter < 3) {

			if (pwd.equals(ownerPassword)) {
				break;

			} else {
				pwdEnter++;
				System.out.println("Please Enter your password as password only ( : " + pwdEnter + ")" );
				pwd = scan.next();
			}

		}

		if (pwdEnter == 3) {
			System.out.println("Sorry you have exit your tries to enter your password");
			return false;
		}

		return true;
	}

	/**
	 * main method to compile
	 * @param args accept string array element 
	 */
	public static void main(String[] args) {
		
		// Scanner scan = new Scanner(System.in);

		System.out.println("**********Welcome to Computer Store**********");

		System.out.println("Enter maximum number of computers you have in your store ?");
		 boolean isflag = false;
		while(!isflag)
		{
		try {
			
			computerStore = scan.nextInt();	
			// System.out.println("try " + computerStore);
			
			isflag = true;
			while (computerStore < 1) {
				System.out.println("Please Enter maximum number of computers you have in your store ? ");
				computerStore = scan.nextInt();
				
			}
					
			System.out.println();
			inventory = new Computer[computerStore];
			mainMenu();
		}catch(InputMismatchException e) {
	         System.out.println( "This is not a good value");    
	                   
	         scan.nextLine();
	        
	         
		}
	  }
		

	}

}
