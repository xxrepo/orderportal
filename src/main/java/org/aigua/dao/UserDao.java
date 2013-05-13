package org.aigua.dao;

import org.aigua.domain.User;
import java.util.List;
import java.util.Set;

public interface UserDao {

	public int count();
	
	public User findById(int id);
	
	public User findByUsername(String username);
	
	public List<User> findAll();
	
	public List<User> findAllOffset(int max, int offset);
	
	public void save(User user);
	
	public void update(User user);
	
	public void delete(int id);
	
	public String getUserPassword(String username);
	
	public void saveUserRole(int userId, int roleId);
	
	public void saveUserPermission(int userId, String permission);
	
	public void deleteUserRoles(int userId);
	
	public void deleteUserPermissions(int userId);
	
	public Set<String> getUserRoles(int id);	
	
	public Set<String> getUserRoles(String username);
	
	public Set<String> getUserPermissions(int id);
	
	public Set<String> getUserPermissions(String username);
	
}
