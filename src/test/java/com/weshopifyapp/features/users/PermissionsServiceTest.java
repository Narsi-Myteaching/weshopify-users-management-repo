package com.weshopifyapp.features.users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.weshopifyapp.features.users.WeshopifyUsersmanagementServiceApplication;
import com.weshopifyapp.features.users.beans.PermissionsBean;
import com.weshopifyapp.features.users.data.exceptions.PermissionsNotFoundException;
import com.weshopifyapp.features.users.services.PermissionsService;

public class PermissionsServiceTest extends WeshopifyUsersmanagementServiceApplicationTests {

	@Autowired
	private PermissionsService pservice;
	
	@Test
	
	public void testCreatePermission() throws PermissionsNotFoundException {
		PermissionsBean pbean = PermissionsBean.builder().name("delete").build();
		pservice.createPermisson(pbean);
	}
}
