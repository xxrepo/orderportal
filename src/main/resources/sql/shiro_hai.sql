
CREATE TABLE shiro_user (
	id bigint NOT NULL,
	name character varying(55) NOT NULL,
	email character varying(55) NOT NULL,
	username character varying(55) NOT NULL,
	password_hash character varying(55) NOT NULL,
	CONSTRAINT shiro_user_pkey PRIMARY KEY (id),
	CONSTRAINT shiro_user_username_key UNIQUE (username)
)
WITH (
	OIDS=FALSE
);
ALTER TABLE shiro_user
	OWNER TO postgres;


CREATE TABLE shiro_role (
	id bigint NOT NULL,
	name character varying(55) NOT NULL,
	CONSTRAINT shiro_role_pkey PRIMARY KEY (id),
	CONSTRAINT shiro_role_name_key UNIQUE (name)
)
WITH (
  	OIDS=FALSE
);
ALTER TABLE shiro_role
  	OWNER TO postgres;
  


CREATE TABLE shiro_role_permissions (
	shiro_role_id bigint,
	permissions_string character varying(55),
	CONSTRAINT fk389b46c98ba4b1d FOREIGN KEY (shiro_role_id)
	    REFERENCES shiro_role (id) MATCH SIMPLE
	    ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  	OIDS=FALSE
);
ALTER TABLE shiro_role_permissions
  	OWNER TO postgres;
	
	  
	  

CREATE TABLE shiro_user_permissions (
	shiro_user_id bigint,
	permissions_string character varying(55),
	CONSTRAINT fk34555a9eade50efd FOREIGN KEY (shiro_user_id)
	    REFERENCES shiro_user (id) MATCH SIMPLE
	    ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  	OIDS=FALSE
);
ALTER TABLE shiro_user_permissions
  	OWNER TO postgres;



CREATE TABLE shiro_user_roles (
	shiro_role_id bigint NOT NULL,
	shiro_user_id bigint NOT NULL,
	CONSTRAINT shiro_user_roles_pkey PRIMARY KEY (shiro_user_id, shiro_role_id),
	CONSTRAINT fkba2210578ba4b1d FOREIGN KEY (shiro_role_id)
	    REFERENCES shiro_role (id) MATCH SIMPLE
	    ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fkba221057ade50efd FOREIGN KEY (shiro_user_id)
	    REFERENCES shiro_user (id) MATCH SIMPLE
	    ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  	OIDS=FALSE
);
ALTER TABLE shiro_user_roles
  	OWNER TO postgres;

