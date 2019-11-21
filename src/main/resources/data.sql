--insert default user "admin" with password "password"
--password converted by BCryptPasswordEncoder()

INSERT INTO users (username, password, enabled)
	values('admin','$2a$10$2fXjV1xaK9TCUziuNPo7BO1N5PlZFg2Rk9wek4MO74x8Oi/Yqyxiu',true);

INSERT INTO authorities (username, authority)
	values('admin','ROLE_ADMIN');