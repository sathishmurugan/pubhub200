package com.sathish.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathish.form.LoginForm;
import com.sathish.form.RegistrationForm;
import com.sathish.model.User;
import com.sathish.repository.UserRepository;
import com.sathish.util.EmailUtil;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EmailUtil emailUtil;
	public User findByEmailAndPassword(String email,String password)
	{
		return userRepository.findByEmailAndPassword(email, password);
	}
	
	public void register(RegistrationForm regUserForm) throws Exception {

		User user = userRepository.findByEmail(regUserForm.getEmail());
		System.out.println("Is email already exists? "+ user);
		if ( user != null) {
			throw new Exception ("Email already exists!!!");
		}
		User userObj = new User();
		userObj.setName(regUserForm.getName());
		userObj.setEmail(regUserForm.getEmail());
		userObj.setPassword(regUserForm.getPassword());
		
		
		userRepository.save(userObj);
		String subject = "Your account has been created";
		String body = "Welcome to Revature ! You can login to your account !";
		emailUtil.send(userObj.getEmail(), subject, body);
		

	}

	public void login(LoginForm loginForm)
	{
		
	}
}
