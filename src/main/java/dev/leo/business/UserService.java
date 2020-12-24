package dev.leo.business;

import java.util.Set;

import dev.leo.entities.User;

public interface UserService {
	
	User createUser(String username, String firstname, String lastname, String password, double balance);
	
	User login(String username, String password);
	
	User makeDeposit(User user, double balance, double deposit);
	
	User makeWithdrawal(User user, double balance, double withdrawal);
	
	boolean deleteAccount(int getBank_id);
	
	Set<User> getAll(String username);

}
