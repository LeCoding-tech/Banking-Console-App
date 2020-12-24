package dev.leo.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import dev.leo.entities.User;
import dev.leo.utli.ConnectionUtli;

public class UserDAOImpl implements UserDAO {

	public User createUser(User user) {
		
		try (Connection conn = ConnectionUtli.getConnection()) {
			String sql = "INSERT INTO BANKUSER (FIRST_NAME , LAST_NAME , USERNAME , PASSWORD , BALANCES) VALUES (?, ?, ?, ?, ?)";
			
			String[] keys = { "BANK_ID" };
			PreparedStatement ps = conn.prepareStatement(sql, keys);
			
			ps.setString(1, user.getFirstname());
			ps.setString(2, user.getLastname());
			ps.setString(3, user.getUsername());
			ps.setString(4, user.getPassword());
			ps.setDouble(5, user.getBalance());
			
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			
			rs.next();
			int generatedId = rs.getInt(1);
			user.setBank_id(generatedId);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public User login(String username, String password) {
		try (Connection conn = ConnectionUtli.getConnection()) {
			String sql = "SELECT * FROM BANKUSER WHERE USERNAME = ? AND PASSWORD = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			User user = new User();
			user.setBank_id(rs.getInt("BANK_ID"));
			user.setFirstname(rs.getString("FIRST_NAME"));
			user.setLastname(rs.getString("LAST_NAME"));
			user.setUsername(rs.getString("USERNAME"));
			user.setPassword(rs.getString("PASSWORD"));
			user.setBalance(rs.getInt("BALANCE"));
			
			return user;
		} catch (SQLException e) {
			return null;
		}

	}

	public User updateBalance(User user) {
		try (Connection conn = ConnectionUtli.getConnection()) {
			String sql = "UPDATE BANKUSER set BALANCE = ? WHERE BANK_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setDouble(1, user.getBalance());
			ps.setInt(2, User.getBank_id());
			
			ps.executeUpdate();
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean deleteUser(int id) {
		try (Connection conn = ConnectionUtli.getConnection()) {
			String sql = "DELETE FROM BANKUSER WHERE BANK_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public Set<User> getAll(String username) {
		try (Connection conn = ConnectionUtli.getConnection()) {
			String sql = "SELECT * FROM BANKUSER WHERE USERNAME = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			Set<User> getAll = new HashSet<User>();
			while(rs.next()) {
				User user = new User();
				user.setBank_id(rs.getInt("BANK_ID"));
				user.setFirstname(rs.getString("FIRST_NAME"));
				user.setLastname(rs.getString("LAST_NAME"));
				user.setUsername(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setBalance(rs.getInt("BALANCE"));
				
				getAll.add(user);
			}
			return getAll;
			
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		
		}
		
	}
	
	

}
