package com.weshopifyapp.features.users;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.weshopifyapp.features.users.data.models.Permissions;
import com.weshopifyapp.features.users.data.repository.PermissionsRepository;

@TestMethodOrder(value = OrderAnnotation.class)
public class PermissionsTest extends WeshopifyUsersmanagementServiceApplicationTests {

	@Autowired
	private PermissionsRepository permissionsRepo;
	
	@Test
	@Order(value = 1)
	public void createPermission() {
		Permissions view =	Permissions.builder().name("view").build();
		permissionsRepo.save(view);
		assertNotNull(view.getPermissionId());
		assertNotNull(view.getName());
		
	}
}
