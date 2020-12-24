package dev.leo.entities;

public class User {
	
		private String username;
		private String firstname;
		private String lastname;
		private String password;
		private double balance;
		private static int bank_id;
		
		
		public User() {
			super();
		}
		
		
		public User(int bank_id,String username, String firstname, String lastname, String password, double balance) {
			super();
			this.username = username;
			this.firstname = firstname;
			this.lastname = lastname;
			this.password = password;
			this.balance = balance;
		}
		
		
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getFirstname() {
			return firstname;
		}
		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}
		public String getLastname() {
			return lastname;
		}
		public void setLastname(String lastname) {
			this.lastname = lastname;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public double getBalance() {
			return balance;
		}
		public void setBalance(double balance) {
			this.balance = balance;
		}
		public static int getBank_id() {
			return bank_id;
		}
		public void setBank_id(int bank_id) {
			User.bank_id = bank_id;
		}
		
		@Override
		public String toString() {
			return "User [username=" + username + ", firstname=" + firstname + ", lastname=" + lastname + ", password="
					+ password + ", balance=" + balance + ", bank_id=" + bank_id + "]";
		}
		
		

}
