package com.weshopifyapp.features.users;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.weshopifyapp.features.users.data.models.WeshopifyUsers;
import com.weshopifyapp.features.users.data.repository.WeshopifyRolesRepository;
import com.weshopifyapp.features.users.data.repository.WeshopifyUsersRepository;

public class UsersTest extends RolesTest {

	@Autowired
	private WeshopifyUsersRepository usersRepo;
	
	@Autowired
	private WeshopifyRolesRepository rolesRepo;
	
	
	@Test
	public void testCreateUser() {
		
		WeshopifyUsers users = WeshopifyUsers.builder().userRoles(rolesRepo.findById(1).get())
				      .fname("Demo")
				      .lname("User")
				      .email("demoUser@yopmail.com")
				      .userId("demoUser")
				      .mobile("9876543210")
				      .isEnabled(false)
				      .isLocked(true)
				      .build();
		usersRepo.save(users);
		assertNotNull(users);
		assertNotNull(users.getId());
	}
}
