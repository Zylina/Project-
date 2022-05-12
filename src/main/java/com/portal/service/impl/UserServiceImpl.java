package com.portal.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.helper.UserFoundException;
import com.portal.helper.UserNotFoundException;
import com.portal.model.User;
import com.portal.model.UserRole;
import com.portal.repository.RoleRepository;
import com.portal.repository.UserRepository;
import com.portal.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	//creating service
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		
		User local = this.userRepository.findByUsername(user.getUsername());
		if(local != null) {
			System.out.println("User is already there !!");
			throw new UserFoundException();
		}
		else {
			//user create
			for(UserRole ur : userRoles) {
				roleRepository.save(ur.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			local = this.userRepository.save(user);
		}
		return local;
	}

	@Override
	public User getUser(String username) {
		return this.userRepository.findByUsername(username);
		
	}

	@Override
	public void deleteUser(Long userId) {
		
		this.userRepository.deleteById(userId);
	}
    
	
	
}

