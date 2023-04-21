package com.weshopifyapp.features.users;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;

import com.weshopifyapp.features.users.beans.WeshopifyRolesBean;
import com.weshopifyapp.features.users.beans.WeshopifyUsersBean;
import com.weshopifyapp.features.users.services.WeshopifyUsersService;

//@TestMethodOrder(value = OrderAnnotation.class)
public class WeshopifyUsersServiceTest extends RolesTest {

	@Autowired
	private WeshopifyUsersService usersService;
	
	@Test
	@Order(value = 3)
	public void testCreateUser() {
		WeshopifyRolesBean roleBean = WeshopifyRolesBean.builder().name("Admin").roleId(1).build();
		WeshopifyUsersBean usersBean = WeshopifyUsersBean.builder()
									.fname("test")
									.lname("user")
									.email("testUser@yopmail.com")
									.userId("testUser")
									.mobile("9876543210")
									.isEnabled(false)
									.isLocked(true)
									.userRoles(roleBean)
									.build();
		usersService.createUser(usersBean);
	}
	
	@Test
	@Order(value = 4)
	public void testUpdateUser() {
		//WeshopifyRolesBean roleBean = WeshopifyRolesBean.builder().name("Admin").roleId(1).operation("deProvision").build();
		WeshopifyUsersBean usersBean = WeshopifyUsersBean.builder()
									.fname("test")
									.lname("user")
									.email("testUser@yopmail.com")
									.userId("testUser")
									.mobile("9876543210")
									.isEnabled(false)
									.isLocked(true)
									.userRoles(null)
									.id(1)
									.build();
		usersService.updateUser(usersBean);
	}
	
	@Test
	@Order(value = 5)
	public void testUpdateProvisionUser() {
		WeshopifyRolesBean roleBean = WeshopifyRolesBean.builder().name("Admin").roleId(1).build();
		WeshopifyUsersBean usersBean = WeshopifyUsersBean.builder()
									.fname("test")
									.lname("user")
									.email("testUser@yopmail.com")
									.userId("testUser")
									.mobile("9876543210")
									.isEnabled(false)
									.isLocked(true)
									.userRoles(roleBean)
									.id(1)
									.build();
		usersService.updateUser(usersBean);
	}
}
