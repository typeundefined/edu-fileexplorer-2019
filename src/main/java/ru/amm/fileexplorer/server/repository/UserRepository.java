package ru.amm.fileexplorer.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.amm.fileexplorer.server.data.user.UserRegistrationInfo;

@Repository
public interface UserRepository extends JpaRepository<UserRegistrationInfo, Long> {
	UserRegistrationInfo findByUsername(String username);
}
