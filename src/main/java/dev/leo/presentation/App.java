package dev.leo.presentation;

import java.util.Scanner;
import java.util.Set;

import dev.leo.business.UserService;
import dev.leo.business.UserServiceImpl;
import dev.leo.entities.User;

public class App {
	
	static Scanner scan = new Scanner(System.in);
	static User user = null;
	
	private static UserService userServ = new UserServiceImpl();
	
	public static void main(String[] args) {
		
		System.out.println("----------------------------------------------");
		System.out.println("Welcome to Bank of America banking app");
		System.out.println("----------------------------------------------");
		boolean notLoggedIn = true;
		while(notLoggedIn) {
			System.out.println("Login: 1, Create Account: 2");
			int choice = scan.nextInt();
			
			switch (choice) {
			
			case 1: {
				System.out.println("Enter Username");
				String username = scan.next();
				System.out.println("Enter Password");
				String password = scan.next();
				user = userServ.login(username, password);
				if(user == null) {
					System.out.println("Invalid username or passwor \n");
					continue;
				} else {
					notLoggedIn = false;
				}
				
				break;
			}
			
			case 2: {
				System.out.println("Enter a username");
				String username = scan.next();
				System.out.println("Enter your firstname");
				String firstname = scan.next();
				System.out.println("Enter your lastname");
				String lastname = scan.next();
				System.out.println("Enter your starting balance");
				double balance = scan.nextDouble();
				user = userServ.createUser(username, firstname, lastname, lastname, balance);
				notLoggedIn = false;
				break;
			}
			
			
			}
			break;
			
		}
		welcome();
	}
	
	public static void welcome() {
		System.out.println("\nWelcome " + user.getFirstname() + ", Your Balance is $" + user.getBalance());
		
		System.out.println("Make a Deposit: 1, Make a Withdrawal: 2, View Accounts: 3, Logout 4, Delete Account: 5");
		
		boolean LoggedIn = true;
		while (LoggedIn) {
			int choice = scan.nextInt();
			
			switch (choice) {
			
			case 1: {
				System.out.println("How much would you like to deposit?");
				double deposit = scan.nextDouble();
				
				if (deposit <= 0) {
					System.out.println("Amount deposited must be positive.");
					welcome();
					break;
				}
				double balance = user.getBalance() + deposit;
				user = userServ.makeDeposit(user, balance, deposit);
				System.out.println("Your deposit has been made for $" + deposit + " and your current balance is $" + user.getBalance());
				welcome();
				break;
				
				
			}
			
			case 2: {
				System.out.println("How much would you like to withdraw?");
				double withdrawal = scan.nextDouble();
				
				if(withdrawal > user.getBalance()) {
					System.out.println("Insufficient Funds.");
					welcome();
				} else if(withdrawal < 0) {
					System.out.println("Amount withdraw must be positive.");
					welcome();
					break;
				} else {
					double balance = user.getBalance() - withdrawal;
					user = userServ.makeWithdrawal(user, balance, withdrawal);
					System.out.println("Your withdrawal has been made for $" + withdrawal + "and your current balance is $" + user.getBalance());
					welcome();
				}
				
				break;
			}
			case 3: {
				Set<User> getAll = userServ.getAll(user.getUsername());
				System.out.println(getAll);
				welcome();
				break;
			}
			case 4: {
				LoggedIn = false;
				System.out.println("Thank you for Banking with us!!!");
				break;
			}
			case 5: {
				if (user.getBalance() == 0) {
					System.out.println("Your account has been deleted, rerun  the console to make a new account.");
					userServ.deleteAccount(User.getBank_id());
					LoggedIn = false;
					break;
				} else {
					System.out.println("Account must have a balance of $0 to be deleted.");
					welcome();
					break;
				}
			}
			}
		}
	}

}
