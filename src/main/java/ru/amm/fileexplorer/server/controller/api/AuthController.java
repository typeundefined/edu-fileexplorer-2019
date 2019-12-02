package ru.amm.fileexplorer.server.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.amm.fileexplorer.server.data.api.UserLoginDTO;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager manager;

    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public void login(@RequestBody UserLoginDTO creds) {
        Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(
                creds.getUsername(),
                creds.getPassword()
        ));

        // XXX [KN] This will trigger the server to return "Set-Cookie: ..." header and
        // thus will allow to authenticate with those cookies
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
