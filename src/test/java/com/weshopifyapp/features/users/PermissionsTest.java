package com.weshopifyapp.features.users;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.weshopifyapp.features.users.data.models.Permissions;
import com.weshopifyapp.features.users.data.repository.PermissionsRepository;

public class PermissionsTest extends WeshopifyUsersmanagementServiceApplicationTests {

	@Autowired
	private PermissionsRepository permissionsRepo;
	
	@Test
	public void createPermission() {
		Permissions view =	Permissions.builder().name("view").build();
		permissionsRepo.save(view);
		assertNotNull(view.getPermissionId());
		assertNotNull(view.getName());
		
	}
}
