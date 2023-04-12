package com.weshopifyapp.features.users;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.weshopifyapp.features.users.data.models.Users;
import com.weshopifyapp.features.users.data.repository.RolesRepository;
import com.weshopifyapp.features.users.data.repository.UsersRepository;

public class UsersTest extends RolesTest {

	@Autowired
	private UsersRepository usersRepo;
	
	@Autowired
	private RolesRepository rolesRepo;
	
	
	@Test
	public void testCreateUser() {
		
		Users users = Users.builder().userRoles(rolesRepo.findById(1).get())
				      .fname("Demo")
				      .lname("User")
				      .email("demoUser@yopmail.com")
				      .userId("demoUser")
				      .mobile("9876543210")
				      .isActive(false)
				      .build();
		usersRepo.save(users);
		assertNotNull(users);
		assertNotNull(users.getId());
	}
}
