package org.aigua.dao;

import java.util.List;
import org.aigua.domain.Role;

public interface RoleDao {
	
	public int count();
	
	public Role findById(int id);
	
	public Role findByName(String name);
	
	public List<Role> findAll();
	
	public List<Role> findAllOffset(int max, int offset);
	
	public void save(Role role);
	
	public void update(Role role);
	
	public void delete(int id);
	
}