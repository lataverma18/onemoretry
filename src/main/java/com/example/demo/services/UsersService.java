package com.example.demo.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.entities.Users;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.respositories.UsersRepository;

@Service
public class UsersService {
	@Autowired
	UsersRepository usersRepository;
	public String authenticateUser(Users user) throws UserNotFoundException
	{
		BCryptPasswordEncoder bcrypt=new BCryptPasswordEncoder();
		Optional<Users> opUser=usersRepository.findById(user.getUsername());
		if(opUser.isPresent())
		{
			Users dbUser=opUser.get();
			if(bcrypt.matches(user.getPassword(),dbUser.getPassword()))
				return "Authenticated User";
			else
				return "Incorrect Password";
		}
		throw new UserNotFoundException("No user is not found for this username!!!");
	}
	public String addUser(Users user)
	{
		BCryptPasswordEncoder bcrypt=new BCryptPasswordEncoder();
		String encryptedPwd=bcrypt.encode(user.getPassword());
		user.setPassword(encryptedPwd);
		Users savedUser=usersRepository.save(user);
		return savedUser.getUsername()+"added to database successfully";
	}
}
