package ru.amm.fileexplorer.server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	@RequestMapping(path = "/login")
	public ModelAndView loginPage() {
		return new ModelAndView("login");
	}
}
