package ru.amm.fileexplorer.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.amm.fileexplorer.server.data.user.UserRegistrationInfo;
import org.springframework.security.core.userdetails.User;

import javax.validation.Valid;
import java.util.Arrays;

@Controller
@RequestMapping(path = "/register")
public class RegisterController {

    @Autowired
    private JdbcUserDetailsManager jdbcUserDetailsManager;

    @Autowired
    private PasswordEncoder encoder;

    @RequestMapping(method = RequestMethod.GET)
    public String renderPage(Model model) {
        model.addAttribute("account", new UserRegistrationInfo());
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("account") UserRegistrationInfo userInfo, Errors errors, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        // TODO store the user in the DB here
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));

        jdbcUserDetailsManager.createUser(new User(userInfo.getUsername(), userInfo.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
        return "register-ok";
    }
}
