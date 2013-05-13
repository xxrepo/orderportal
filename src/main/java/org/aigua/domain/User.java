package org.aigua.domain;

import java.util.Set;

public class User{
	
	private int id;
	private String username;
	private String passwordHash;
	// private String passwordSalt;
	
	private String name;
	private String email;
	
	private Set<String> roles;
	private Set<String> permissions;

	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	
	public String getUsername(){
		return this.username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	
	public String getPasswordHash(){
		return this.passwordHash;
	}
	
	public void setPasswordHash(String passwordHash){
		this.passwordHash = passwordHash;
	}
	
	
	// public String getPasswordSalt(){
	// 	return this.passwordSalt;
	// }
	// 
	// public void setPasswordSalt(String passwordSalt){
	// 	this.passwordSalt = passwordSalt;
	// }
	
			
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	
	public String getEmail(){
		return this.email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	
	public Set<String> getRoles(){
		return roles;
	}
	
	public void setRoles(Set<String> roles){
		this.roles = roles;
	}
	
	
	public Set<String> getPermissions(){
		return permissions;
	}
	
	public void setPermissions(Set<String> permissions){
		this.permissions = permissions;
	}
	
	
}