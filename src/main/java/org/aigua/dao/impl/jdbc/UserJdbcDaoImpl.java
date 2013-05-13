package org.aigua.dao.impl.jdbc;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import org.apache.log4j.Logger;

import org.aigua.dao.UserDao;
import org.aigua.domain.User;


import static org.aigua.common.ShiroHaiConstants.*;


public class UserJdbcDaoImpl extends JdbcDaoSupport implements UserDao  {

    private static final Logger log = Logger.getLogger(UserJdbcDaoImpl.class.getName());
    
	@Value("${paginate}")
	private String paginate;
	
	@Value("${user.find.id}")
	private String findByIdSql;

	@Value("${user.find.username}")
	private String findByUsernameSql;

	@Value("${user.find.all}")
	private String findAllSql;
	
	@Value("${user.save}")
	private String insertSql;

	@Value("${user.count}")
	private String countSql;

	@Value("${user.next.id}")
	private String nextIdSql;
	
	@Value("${user.update}")
	private String updateSql;

	@Value("${user.delete}")
	private String deleteSql;
	
	@Value("${user.auth}")
	private String userAuthSql;

	@Value("${user.save.role}")
	private String insertUserRoleSql;
	
	@Value("${user.save.permission}")
	private String insertUserPermissionSql;
	
	@Value("${user.delete.roles}")
	private String deleteUserRolesSql;
	
	@Value("${user.delete.permissions}")
	private String deleteUserPermissionsSql;
	
	@Value("${user.roles}")
	private String userRolesSql;
	
	@Value("${user.permissions}")
	private String userPermissionsSql;

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public User findById(int id) {
		User user = jdbcTemplate.queryForObject(findByIdSql, new Object[] { id }, 
			new BeanPropertyRowMapper<User>(User.class));
		return user;
	}

	
	public User findByUsername(String username) {
		String searchSql = findByUsernameSql.replace(REPLACE_USERNAME, username);
		User user = jdbcTemplate.queryForObject(searchSql, new Object[] {}, 
			new BeanPropertyRowMapper<User>(User.class));
		return user;	
	}

	
	public List<User> findAll() {
		List<User> users = jdbcTemplate.query(findAllSql, new BeanPropertyRowMapper<User>(User.class));
		return users;
	}

	
	public List<User> findAllOffset(int max, int offset) {
		String sql = findAllSql;
		sql+= " " + paginate;
		sql = sql.replace(MAX, Integer.toString(max));
		sql = sql.replace(OFFSET, Integer.toString(offset));

		List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));

		return users;
	}
	


	public void save(User user) {
		int id = jdbcTemplate.queryForInt(nextIdSql, new Object[0]);
		jdbcTemplate.update(insertSql, new Object[] { 
			id, user.getName(), user.getEmail(), user.getUsername(), user.getPasswordHash()  
		});
	}

	
	public void update(User user) {
		jdbcTemplate.update(updateSql, new Object[] { 
			user.getName(), user.getEmail(), user.getUsername(), user.getPasswordHash(), user.getId()  
		});
	}

	
	public void delete(int id) {
		User user = findById(id);
		jdbcTemplate.update(deleteSql, new Object[] {id });
	}

	
	public int count() {
		int id = jdbcTemplate.queryForInt(countSql, new Object[0]);
	 	return id; 
	}

		
	public String getUserPassword(String username) {
		User user = findByUsername(username);
		return user.getPasswordHash();
	}
	
	
	public void saveUserRole(int userId, int roleId){
		jdbcTemplate.update(insertUserRoleSql, new Object[] { 
			roleId, userId
		});
	}
	
	
	public void saveUserPermission(int userId, String permission){
		jdbcTemplate.update(insertUserPermissionSql, new Object[] { 
			userId, permission
		});
	}
	
	
	public void deleteUserRoles(int userId){
		jdbcTemplate.update(deleteUserRolesSql, new Object[] { userId });
	}
	
	public void deleteUserPermissions(int userId){
		jdbcTemplate.update(deleteUserPermissionsSql, new Object[] { userId });	
	}
	
	
	public Set<String> getUserRoles(int id) {	
		String search = userRolesSql.replace(REPLACE_ID, Integer.toString(id));
		List<String> rolesList = jdbcTemplate.queryForList(search, String.class);
		Set<String> roles = new HashSet<String>(rolesList);
		return roles;
	}

	
	public Set<String> getUserPermissions(int id) {
		String search = userPermissionsSql.replace(REPLACE_ID, Integer.toString(id));
		List<String> permissionsList = jdbcTemplate.queryForList(search, String.class);
		Set<String> permissions = new HashSet<String>(permissionsList);
		return permissions;
	}
	

	
	public Set<String> getUserRoles(String username) {	
		User user = findByUsername(username);
		String search = userRolesSql.replace(REPLACE_ID, Integer.toString(user.getId()));
		List<String> rolesList = jdbcTemplate.queryForList(search, String.class);
		Set<String> roles = new HashSet<String>(rolesList);
		return roles;
	}

	
	public Set<String> getUserPermissions(String username) {
		User user = findByUsername(username);
		String search = userPermissionsSql.replace(REPLACE_ID, Integer.toString(user.getId()));
		List<String> permissionsList = jdbcTemplate.queryForList(search, String.class);
		Set<String> permissions = new HashSet<String>(permissionsList);
		return permissions;
	}
	
}