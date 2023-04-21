package com.weshopifyapp.features.users.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.weshopifyapp.features.users.beans.PermissionsBean;
import com.weshopifyapp.features.users.data.exceptions.PermissionsNotFoundException;
import com.weshopifyapp.features.users.data.models.Permissions;
import com.weshopifyapp.features.users.data.repository.PermissionsRepository;



@ExtendWith(MockitoExtension.class)
class PermissionsServiceImplTest {
	
	@Mock
	private PermissionsRepository repo;
	
	@Mock
	private ModelMapper mapper;

	@InjectMocks
	private PermissionsServiceImpl service;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	
	@Test
	void testCreatePermisson() throws PermissionsNotFoundException {
		PermissionsBean permissionBean = PermissionsBean.builder().name("Create").build();
		
		Permissions permission = Permissions.builder().name("Create").permissionId(1).build();
		
		when(repo.save(permission)).thenReturn(permission);
		when(mapper.map(permissionBean, Permissions.class)).thenReturn(permission);
		when(mapper.map(permission, PermissionsBean.class)).thenReturn(permissionBean);
		
		
		permissionBean = service.createPermisson(permissionBean);
		assertEquals(permission.getPermissionId(), permissionBean.getPermissionId());
		assertEquals(permission.getName(), permissionBean.getName());
	}
	
	 @Test 
	 void testUpdatePermisson() throws PermissionsNotFoundException { 
		 PermissionsBean permissionBean = PermissionsBean.builder().name("View").permissionId(1).build();
		 Permissions permission = Permissions.builder().name("View").permissionId(1).build();
		 
		 when(mapper.map(permissionBean, Permissions.class)).thenReturn(permission);
		 when(repo.save(permission)).thenReturn(permission);
		 when(mapper.map(permission, PermissionsBean.class)).thenReturn(permissionBean);
		 
		 permissionBean =  service.updatePermisson(permissionBean);
		 assertEquals(permission.getPermissionId(), permissionBean.getPermissionId());
		 assertEquals(permission.getName(), permissionBean.getName());
	 }

	/*
	 * @Test void testUpdatePermisson() { fail("Not yet implemented"); // TODO }
	 * 
	 * @Test void testFindPermissonById() { fail("Not yet implemented"); // TODO }
	 * 
	 * @Test void testDeletePermissonById() { fail("Not yet implemented"); // TODO }
	 * 
	 * @Test void testGetAllPermissons() { fail("Not yet implemented"); // TODO }
	 * 
	 * @Test void testSearchPermissons() { fail("Not yet implemented"); // TODO }
	 */

}
