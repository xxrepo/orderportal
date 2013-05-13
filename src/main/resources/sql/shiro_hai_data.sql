
insert into shiro_user (id, password_hash, username, name, email)
	values (1, 'admin', 'admin', 'admin', 'admin@email.com');
insert into shiro_user (id, password_hash, username, name, email)
	values (2, 'cust1', 'customer1', 'customer1', 'customer1@email.com');
insert into shiro_user (id, password_hash, username, name, email)
	values (3, 'cust2', 'customer2', 'customer2', 'customer2@email.com');
	
  	

insert into shiro_role (id, name)
	values (1, 'ROLE_ADMIN');
insert into shiro_role (id, name)
	values (2, 'ROLE_CUSTOMER');
		
	
insert into shiro_user_permissions (shiro_user_id, permissions_string)
	values (2, 'user:2:edit');
insert into shiro_user_permissions (shiro_user_id, permissions_string)
	values (2, 'user:2:update');
insert into shiro_user_permissions (shiro_user_id, permissions_string)
	values (3, 'user:3:edit');
insert into shiro_user_permissions (shiro_user_id, permissions_string)
	values (3, 'user:3:update');


insert into shiro_user_roles (shiro_role_id, shiro_user_id)
	values (1, 1);
insert into shiro_user_roles (shiro_role_id, shiro_user_id)
	values (2, 2);
insert into shiro_user_roles (shiro_role_id, shiro_user_id)
	values (2, 3);