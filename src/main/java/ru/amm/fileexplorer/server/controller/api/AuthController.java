package ru.amm.fileexplorer.server.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.amm.fileexplorer.server.data.api.JwtResponseDTO;
import ru.amm.fileexplorer.server.data.api.UserLoginDTO;
import ru.amm.fileexplorer.server.data.user.UserRegistrationInfo;
import ru.amm.fileexplorer.server.service.api.AuthService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService auth;

    @PostMapping("/login")
    public JwtResponseDTO login(@RequestBody UserLoginDTO loginDTO) {
        return auth.login(loginDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/register")
    public void register(@Valid UserRegistrationInfo registrationInfo, BindingResult result, HttpServletResponse response) {
        if (result.hasErrors()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        auth.register(registrationInfo);
    }
}
