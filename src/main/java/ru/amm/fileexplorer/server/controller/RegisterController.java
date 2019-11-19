package ru.amm.fileexplorer.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.amm.fileexplorer.server.data.user.UserRegistrationInfo;
import ru.amm.fileexplorer.server.repository.UserRepository;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/register")
public class RegisterController {
	@Autowired
	private UserRepository userRepository;

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
		//TODO send error
		// incorrect confirmation password
		if (!userInfo.getPassword().equals(userInfo.getConfirmPassword()))
			return "register";
		userInfo.setPassword(encoder.encode(userInfo.getPassword()));
		userRepository.save(userInfo);
		//TODO create register-ok page or redirect to login page
		return "register-ok";
	}
}
