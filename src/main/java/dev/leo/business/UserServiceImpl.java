package dev.leo.business;

import java.util.Set;

import dev.leo.data.UserDAO;
import dev.leo.data.UserDAOImpl;
import dev.leo.entities.User;

public class UserServiceImpl implements UserService {
	
	private static UserDAO udao = new UserDAOImpl();

	@Override
	public User createUser(String username, String firstname, String lastname, String password, double balance) {
		User user = new User(0, username, firstname, lastname, password, balance);
		udao.createUser(user);
		return user;
	}

	@Override
	public User login(String username, String password) {
		User user = udao.login(username, password);
		return user;
	}

	@Override
	public User makeDeposit(User user, double balance, double deposit) {
		user.setBalance(user.getBalance() + deposit);
		udao.updateBalance(user);
		return user;
	}

	@Override
	public User makeWithdrawal(User user, double balance, double withdrawal) {
		user.setBalance(user.getBalance() - withdrawal);
		return user;
	}

	@Override
	public boolean deleteAccount(int getBank_id) {
		udao.deleteUser(getBank_id);
		return true;
	}

	@Override
	public Set<User> getAll(String username) {
		Set<User> getAll = udao.getAll(username);
		return getAll;
	}
	
	

}
