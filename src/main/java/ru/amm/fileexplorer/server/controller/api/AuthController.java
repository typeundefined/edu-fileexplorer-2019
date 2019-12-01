package ru.amm.fileexplorer.server.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.amm.fileexplorer.server.data.user.UserCredentials;
import ru.amm.fileexplorer.server.data.user.UserLoginInfo;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
   // @Autowired
    AuthenticationManager manager;
    @PostMapping
    public void login(@RequestBody UserLoginInfo creds) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword());
        manager.authenticate(token);
    }
}
