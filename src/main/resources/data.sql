--insert default user "admin" with password "password"
--password converted by BCryptPasswordEncoder()

INSERT INTO users (username, password, enabled)
	values('admin','$2a$10$WUSE5n2qwdLoQrLjSP/klOrMRER9oIBbgs/CihKe3Hgo0kBqOFt42',true);

INSERT INTO authorities (username, authority)
	values('admin','ROLE_ADMIN');