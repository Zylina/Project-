package com.portal;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.portal.helper.UserFoundException;
import com.portal.model.Role;
import com.portal.model.User;
import com.portal.model.UserRole;
import com.portal.service.UserService;

@SpringBootApplication
public class GenpactEPortalApplication implements CommandLineRunner {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public static void main(String[] args)  {
		SpringApplication.run(GenpactEPortalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		try {
		System.out.println("Starting Code..");
		
		User user = new User();
		user.setFirstName("Nisha");
		user.setLastName("Gupta");
	    user.setUsername("nisha002");
		user.setPassword(this.bCryptPasswordEncoder.encode("abc"));
		user.setEmail("guptahimanshi002@gmail.com");
		user.setProfile("default.png");
		
		Role role1 = new Role();
		role1.setRoleId(44L);
		role1.setRoleName("Admin");
		
		Set<UserRole> userRoleSet = new HashSet<>();
		UserRole userRole = new UserRole();
		userRole.setRole(role1);
		userRole.setUser(user);
		userRoleSet.add(userRole);
		
		User user1 = this.userService.createUser(user, userRoleSet);
		System.out.println(user1.getUsername());
		}catch(UserFoundException e) {
			e.printStackTrace();
		}
	}

}
