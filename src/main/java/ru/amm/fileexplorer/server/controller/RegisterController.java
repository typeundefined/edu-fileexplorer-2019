package ru.amm.fileexplorer.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.amm.fileexplorer.server.data.user.UserRegistrationInfo;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/register")
public class RegisterController {
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
        return "register-ok";
    }
}
