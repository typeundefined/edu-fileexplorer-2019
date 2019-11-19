package ru.amm.fileexplorer.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.amm.fileexplorer.server.data.user.UserRegistrationInfo;
import ru.amm.fileexplorer.server.repository.UserRepository;

import java.util.Collections;

@Service
public class UserDetailsProviderService implements UserDetailsService {
	@Autowired
 	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		UserRegistrationInfo user = userRepository.findByUsername(s);
		if (user == null) {
			throw new UsernameNotFoundException(s);
		}
		return new User(user.getUsername(), user.getPassword(),
				true,true,true, true,
				Collections.singleton(new SimpleGrantedAuthority("ADMIN")));
	}
}
