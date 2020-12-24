package dev.leo.data;

import java.util.Set;

import dev.leo.entities.User;

public interface UserDAO {
	
	User createUser(User user);
	
	User login(String username, String password);
	
	User updateBalance(User user);
	
	boolean deleteUser(int getBank_id);
	
	Set<User> getAll(String username);
	
	

}
