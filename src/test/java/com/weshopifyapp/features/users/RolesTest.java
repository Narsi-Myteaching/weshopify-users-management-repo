package com.weshopifyapp.features.users;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.weshopifyapp.features.users.data.models.RoleToPermissions;
import com.weshopifyapp.features.users.data.models.Roles;
import com.weshopifyapp.features.users.data.repository.PermissionsRepository;
import com.weshopifyapp.features.users.data.repository.RolesRepository;

public class RolesTest extends PermissionsTest {

	@Autowired
	private RolesRepository rolesRepo;
	
	@Autowired
	private PermissionsRepository permissions;
	
	@Test
	public void testCreateRole() {
		
		
		Roles role = new Roles();
		role.setName("Admin");
		RoleToPermissions rolesToPerm = RoleToPermissions.builder()
										.permissions(permissions.findById(1).get())
										.roles(role).build();
		role.setRole_to_permissions(Arrays.asList(rolesToPerm));
		rolesRepo.save(role);
		assertNotNull(role.getRole_to_permissions());
		assertNotNull(role.getRoleId());
	}
}
