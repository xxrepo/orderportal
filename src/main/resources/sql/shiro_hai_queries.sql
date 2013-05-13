
-- retrieve all user permissions with role and user id
select r.permissions_string 
	from shiro_user_roles ur, shiro_role_permissions r 
	where ur.shiro_role_id = r.shiro_role_id and ur.shiro_user_id = ?
union distinct select u.permissions_string
	from shiro_user_permissions u
	where u.shiro_user_id = ?
	
	
-- retrieve all user roles where user id = ?
select r.name from shiro_user_roles ur, shiro_role r where ur.shiro_role_id = r.id and ur.shiro_user_id = ?	