package org.aigua.dao.impl.mock;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import org.aigua.domain.User;
import org.aigua.dao.UserDao;

public class UserMockDaoImpl implements UserDao {

	private static final int MAX = 100;
	
	public User findById(int id){
		return getMockUsers(1).get(1);
	}
	
	public User findByUsername(String username) {
		return getMockUsers(1).get(1);
	}

	public String getUserPassword(String username) {
		return "PASSWORD";
	}

	public List<User> findAll(){
		return getMockUsers(MAX);
	}
	
	public List<User> findAllOffset(int max, int offset){
		return getMockUsers(max);
	}
	
	public List<User> search(String term){
		return getMockUsers(MAX);
	}

	
	public void save(User user){}
		
	public void update(User user){}
	
	public void delete(int id){}


	public void saveUserRole(int userId, int roleId){}
		
	public void saveUserPermission(int userId, String permission){}
	
	public void deleteUserRoles(int userId){}
	
	public void deleteUserPermissions(int userId){}
	
	
	public int count(){
		return 10;
	}

	public Set<String> getUserRoles(int id) {
		return null;
	}

	public Set<String> getUserPermissions(int id) {
		return null;
	}
	
	public Set<String> getUserRoles(String username) {
		return null;
	}

	public Set<String> getUserPermissions(String username) {
		return null;
	}
		
	private List<User> getMockUsers(int numberOfUsers){
	
		if(numberOfUsers > MAX)numberOfUsers = MAX;
		List<User> users = new ArrayList<User>();
		
		for(int k = 0; k < numberOfUsers; k++){
			User mockUser = new User();
			mockUser.setId(k);
			mockUser.setEmail("mockuser" + k + "@email.com");
			mockUser.setUsername("mockuser" + k);
			mockUser.setPasswordHash("password");
			mockUser.setName("name" + k);
			users.add(mockUser);
		}
		
		return users;
	}

	
}