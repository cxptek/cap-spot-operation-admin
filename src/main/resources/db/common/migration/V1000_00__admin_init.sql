CREATE TABLE IF NOT EXISTS public.partner_info (
	id uuid NOT NULL,
	"name" varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	verify_token_endpoint varchar(255) NULL,
	reserve_order_endpoint varchar(255) NULL,
	cancel_order_endpoint varchar(255) NULL,
	update_order_price_endpoint varchar(255) NULL,
	update_matching_order_endpoint varchar(255) NULL,
	user_balance_endpoint varchar(255) NULL,
	status varchar(50) NOT NULL,
	created_at timestamp NOT NULL,
	updated_at timestamp NOT NULL,
	created_by varchar(32) NULL,
	updated_by varchar(32) NULL,
	CONSTRAINT partner_email_unique UNIQUE (email),
	CONSTRAINT partner_info_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.partner_key (
	id bigserial NOT NULL,
	alias varchar(255) NULL,
	ref_id varchar(255) NULL,
	status varchar(255) NOT NULL,
	"type" varchar(255) NOT NULL,
	created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    created_by varchar(32) NULL,
    updated_by varchar(32) NULL,
	partner_info_id uuid NOT NULL,
	CONSTRAINT partner_key_pkey PRIMARY KEY (id),
	CONSTRAINT uk_partner_key_alias UNIQUE (alias),
	CONSTRAINT uk_partner_key_ref_id UNIQUE (ref_id),
	CONSTRAINT fk_partner_key_id_partner_info_id FOREIGN KEY (partner_info_id) REFERENCES public.partner_info(id)
);

CREATE TABLE IF NOT EXISTS public."role" (
	"name" varchar(255) NOT NULL,
	CONSTRAINT "rolePK" PRIMARY KEY (name)
);

CREATE TABLE IF NOT EXISTS public."permission" (
	"name" varchar(255) NOT NULL,
	CONSTRAINT "permissionPK" PRIMARY KEY (name)
);

CREATE TABLE IF NOT EXISTS public.role_permissions (
	roles_name varchar(255) NOT NULL,
	permissions_name varchar(255) NOT NULL,
	CONSTRAINT "fk_role_permissions_role_name" FOREIGN KEY (roles_name) REFERENCES public."role"("name"),
	CONSTRAINT "fk_role_permissions_permission_name" FOREIGN KEY (permissions_name) REFERENCES public."permission"("name")
);

CREATE TABLE IF NOT EXISTS public.sys_user (
	username varchar(255) NOT NULL,
	status varchar(255) NOT NULL,
	display_name varchar(255) NULL,
	"password" varchar(255) NOT NULL,
	CONSTRAINT "sys_userPK" PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS public.sys_user_roles (
	username varchar(255) NOT NULL,
	roles_name varchar(255) NOT NULL,
	CONSTRAINT "username_rolesPK" PRIMARY KEY (username, roles_name),
	CONSTRAINT "fk_username_sys_user" FOREIGN KEY (username) REFERENCES public.sys_user(username),
	CONSTRAINT "fk_sys_user_roles_roles_name" FOREIGN KEY (roles_name) REFERENCES public."role"("name")
);

CREATE TABLE IF NOT EXISTS public.partner_admin_user (
	id uuid NOT NULL,
	email varchar(255) NOT NULL,
	display_name varchar(255) NULL,
	"password" varchar(255) NOT NULL,
	status varchar(255) NOT NULL,
	partner_info_id uuid NOT NULL,
	created_at timestamp(6) NULL,
    created_by varchar(255) NULL,
    updated_at timestamp(6) NULL,
    updated_by varchar(255) NULL,
	CONSTRAINT partner_admin_user_pkey PRIMARY KEY (id),
	CONSTRAINT fk_partner_admin_user_partner_info FOREIGN KEY (partner_info_id) REFERENCES public.partner_info(id)
);

CREATE TABLE IF NOT EXISTS public.partner_admin_user_roles (
	partner_admin_user_id uuid NOT NULL,
	roles_name varchar(255) NOT NULL,
	CONSTRAINT "partner_admin_user_id_rolesPK" PRIMARY KEY (partner_admin_user_id, roles_name),
	CONSTRAINT "fk_partner_admin_user_id_admin_id" FOREIGN KEY (partner_admin_user_id) REFERENCES public.partner_admin_user(id),
	CONSTRAINT "fk_partner_admin_user_roles_name" FOREIGN KEY (roles_name) REFERENCES public."role"("name")
);

-- Init roles and permissions
INSERT INTO public."role" ("name") VALUES('MANAGER');
INSERT INTO public."role" ("name") VALUES('PARTNER');


INSERT INTO public."permission" ("name") VALUES('read_user');
INSERT INTO public."permission" ("name") VALUES('create_user');
INSERT INTO public."permission" ("name") VALUES('update_user');
INSERT INTO public."permission" ("name") VALUES('delete_user');

INSERT INTO public.role_permissions (roles_name, permissions_name) VALUES('MANAGER', 'read_user');
INSERT INTO public.role_permissions (roles_name, permissions_name) VALUES('MANAGER', 'create_user');
INSERT INTO public.role_permissions (roles_name, permissions_name) VALUES('MANAGER', 'update_user');
INSERT INTO public.role_permissions (roles_name, permissions_name) VALUES('MANAGER', 'delete_user');

-- Init default CEX Admin
INSERT INTO public.sys_user (username, "status", "display_name", "password") VALUES('admin', 'ACTIVE', 'Manager', '$2a$10$BdQDIOKoF2Xw/vYfBpRik.DZH6kbhqUHvnpRksi42EWBVDuCW0sxK');
INSERT INTO public.sys_user_roles (username, roles_name) VALUES('admin', 'MANAGER');