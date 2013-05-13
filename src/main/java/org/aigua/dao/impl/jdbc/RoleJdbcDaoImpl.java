package org.aigua.dao.impl.jdbc;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.apache.log4j.Logger;
import java.util.List;

import org.aigua.dao.RoleDao;
import org.aigua.domain.Role;


import static org.aigua.common.ShiroHaiConstants.*;


public class RoleJdbcDaoImpl extends JdbcDaoSupport implements RoleDao {

	private static final Logger log = Logger.getLogger(RoleJdbcDaoImpl.class.getName());
	
	@Value("${paginate}")
	private String paginate;
	
	@Value("${role.count}")
	private String countSql;
	
	@Value("${role.next.id}")
	private String nextIdSql;
	
	@Value("${role.find.id}")
	private String findByIdSql;
	
	@Value("${role.find.name}")
	private String findByNameSql;
	
	@Value("${role.find.all}")
	private String findAllSql;
	
	@Value("${role.save}")
	private String insertSql;
	
	@Value("${role.update}")
	private String updateSql;
	
	@Value("${role.delete}")
	private String deleteSql;
	
	
	
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	
	public int count() {
		int id = jdbcTemplate.queryForInt(countSql, new Object[0]);
	 	return id; 
	}
	
	public Role findById(int id) {
		Role role = jdbcTemplate.queryForObject(findByIdSql, new Object[] { id }, 
				new BeanPropertyRowMapper<Role>(Role.class));
		return role;
	}

	
	public Role findByName(String name) {
		String search = findByNameSql.replace(REPLACE_NAME, name);
		Role role = jdbcTemplate.queryForObject(search, new Object[] {}, 
				new BeanPropertyRowMapper<Role>(Role.class));
		return role;
	}
	
	
	public List<Role> findAll() {
		List<Role> roles = jdbcTemplate.query(findAllSql, new BeanPropertyRowMapper<Role>(Role.class));
		return roles;
	}
	
	
	public List<Role> findAllOffset(int max, int offset) {
		String sql = findAllSql;
		sql+= " " + paginate;
		sql = sql.replace(MAX, Integer.toString(max));
		sql = sql.replace(OFFSET, Integer.toString(offset));
	
		System.out.println("find all " + sql);
		List<Role> roles = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Role>(Role.class));
	
		return roles;
	}
	
	
	public void save(Role role) {
		int id = jdbcTemplate.queryForInt(nextIdSql, new Object[0]);
		jdbcTemplate.update(insertSql, new Object[] { 
			id, role.getName()  
		});
	}
	
	
	public void update(Role role) {
		jdbcTemplate.update(updateSql, new Object[] { 
			role.getName(), role.getId()  
		});
	}
	
	
	public void delete(int id) {
		Role role = findById(id);
		jdbcTemplate.update(deleteSql, new Object[] {id });
	}
	

}