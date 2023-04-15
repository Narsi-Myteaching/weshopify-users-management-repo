package com.weshopifyapp.features.users;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.weshopifyapp.features.users.beans.PermissionsBean;
import com.weshopifyapp.features.users.data.exceptions.PermissionsNotFoundException;
import com.weshopifyapp.features.users.services.PermissionsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@TestMethodOrder(value = OrderAnnotation.class)
public class PermissionsServiceTest extends WeshopifyUsersmanagementServiceApplicationTests {

	@Autowired
	private PermissionsService permissionService;

	private static int PERMISSIONS_ID = 1;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	@Order(value = 1)
	void testCreatePermisson() throws PermissionsNotFoundException {
		PermissionsBean bean = PermissionsBean.builder().name("Create").build();
		bean = permissionService.createPermisson(bean);
		log.info("permissions created are:\t" + bean.toString());
		assertNotEquals(0, bean.getPermissionId());
	}

	@Test
	@Order(value = 2)
	void testUpdatePermisson() throws PermissionsNotFoundException {
		PermissionsBean bean = PermissionsBean.builder().name("View").permissionId(PERMISSIONS_ID).build();
		bean = permissionService.updatePermisson(bean);
		log.info("permissions updated are:\t" + bean.toString());
		assertEquals("View", bean.getName());

	}

	@Test
	@Order(value = 3)
	void testFindPermissonById() throws PermissionsNotFoundException {
		PermissionsBean bean = permissionService.findPermissonById(PERMISSIONS_ID);
		log.info("permissions by id are:\t" + bean.toString());
		assertNotNull(bean);
		assertNotEquals(0, bean.getPermissionId());
		assertEquals(1, bean.getPermissionId());
		assertEquals("View", bean.getName());
	}

	//@Test
	//@Order(value = 5)
	void testDeletePermissonById() throws PermissionsNotFoundException {
		List<PermissionsBean> permissionList = permissionService.deletePermissonById(PERMISSIONS_ID);
		//assertNotNull(permissionList);
		//assertNotEquals(0, permissionList.size());
		PermissionsBean bean = permissionService.findPermissonById(PERMISSIONS_ID);
		
	}

	@Test
	@Order(value = 4)
	void testGetAllPermissons() {
		List<PermissionsBean> permissionList = permissionService.getAllPermissons();
		assertNotNull(permissionList);
		assertNotEquals(0, permissionList.size());

	}

	@Test
	void testSearchPermissons() {
		//fail("Not yet implemented"); // TODO
		
	}

}
