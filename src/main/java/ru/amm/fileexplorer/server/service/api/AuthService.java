package ru.amm.fileexplorer.server.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;
import ru.amm.fileexplorer.server.data.api.JwtResponseDTO;
import ru.amm.fileexplorer.server.data.api.UserLoginDTO;
import ru.amm.fileexplorer.server.data.user.UserRegistrationInfo;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

@Service
public class AuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JdbcUserDetailsManager repository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    public JwtResponseDTO login(UserLoginDTO loginDTO) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = jwtTokenProvider.generateToken(auth);
        return new JwtResponseDTO(jwt);
    }

    public void register(UserRegistrationInfo registrationInfo){
        repository.createUser(new User(registrationInfo.getUsername(), encoder.encode(registrationInfo.getPassword()),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
    }

}
