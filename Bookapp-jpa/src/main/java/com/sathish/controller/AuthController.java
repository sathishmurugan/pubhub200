package com.sathish.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sathish.model.User;
import com.sathish.repository.UserRepository;
import com.sathish.util.EmailUtil;

@Controller
@RequestMapping("auth")
public class AuthController {
	private static final Logger LOGGER = Logger.getLogger(AuthController.class);
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EmailUtil emailUtil;
	@PostMapping("/login")
	public String login(@RequestParam("email") String emailId, @RequestParam("password") String password,
			HttpSession session) {
		LOGGER.info("Entering Login");
		LOGGER.debug(new Object[] { emailId, password });
		User user = userRepository.findByEmailAndPassword(emailId, password);
		if (user != null) {
			session.setAttribute("log_user", emailId);
			LOGGER.info("login sucess");
			return "redirect:../books";
		} else {
			return "fail";
		}

	}

	@GetMapping("/register")
	public String showRegister() {
		return "user/Register";

	}

	@PostMapping("/register")
	public String register(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("password") String password) throws Exception {
		LOGGER.info("registration");
		LOGGER.debug(new Object[] {name, email, password });
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		Object obj = userRepository.save(user);
		String subject="Registration Detials";
		String body="Hey you have sucessfuly register your account";
		emailUtil.send(email, subject, body); 

		return "index";

	}

}
