package com.weshopifyapp.features.users.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weshopifyapp.features.users.beans.PermissionsBean;
import com.weshopifyapp.features.users.beans.WeshopifyRolesBean;
import com.weshopifyapp.features.users.beans.WeshopifyUsersBean;
import com.weshopifyapp.features.users.services.PermissionsService;
import com.weshopifyapp.features.users.services.WeshopifyRoleService;
import com.weshopifyapp.features.users.services.WeshopifyUsersService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = "/users")
public class WeshopifyUsersResource {

	private PermissionsService permissionsService;

	private WeshopifyRoleService rolesService;
	
	private WeshopifyUsersService usersService;
	

	WeshopifyUsersResource(PermissionsService permissionsService, WeshopifyRoleService rolesService,WeshopifyUsersService usersService) {
		this.permissionsService = permissionsService;
		this.rolesService = rolesService;
		this.usersService = usersService;
	}
	
	@PostMapping
	public ResponseEntity<WeshopifyUsersBean> createUser(@RequestBody WeshopifyUsersBean usersBean) {
        log.info("the create user data is {}",usersBean.toString());
        return ResponseEntity.ok(usersService.createUser(usersBean));
	}
	
	@PutMapping
	public ResponseEntity<WeshopifyUsersBean> updateUser(@RequestBody WeshopifyUsersBean usersBean) {
        log.info("the update user data is {}",usersBean.toString());
        return ResponseEntity.ok(usersService.updateUser(usersBean));
	}
	
	@GetMapping
	public ResponseEntity<List<WeshopifyUsersBean>> getAllUsers() {
         return ResponseEntity.ok(usersService.findAllUsers());
	}
	
	@GetMapping("/provision/{id}")
	public ResponseEntity<WeshopifyUsersBean> provisionUser(@PathVariable("id") int id) {
		
         return ResponseEntity.ok(usersService.provisioningRoleToUser(id));
	}
	
	@GetMapping("/verifyUser/{id}")
	public ResponseEntity<WeshopifyUsersBean> verifyUser(@PathVariable("id") int id) {
		
         return ResponseEntity.ok(usersService.enableUser(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<List<WeshopifyUsersBean>> deleteUsersById(@PathVariable("id") int id) {
         return ResponseEntity.ok(usersService.deleteUser(id));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<WeshopifyUsersBean> findUsersById(@PathVariable("id") int id) {
         return ResponseEntity.ok(usersService.findUserById(id));
	}
	

	@PostMapping(value = "/permissions")
	public ResponseEntity<PermissionsBean> createPermission(@RequestBody PermissionsBean permissionsBean) {
		log.info("permissions data is:\t" + permissionsBean.toString());
		permissionsBean = permissionsService.createPermisson(permissionsBean);
		return ResponseEntity.ok(permissionsBean);
	}

	@PutMapping(value = "/permissions")
	public ResponseEntity<PermissionsBean> updatePermission(@RequestBody PermissionsBean permissionsBean) {
		log.info("permissions data is:\t" + permissionsBean.toString());
		permissionsBean = permissionsService.createPermisson(permissionsBean);
		return ResponseEntity.ok(permissionsBean);
	}

	@GetMapping(value = "/permissions")
	public ResponseEntity<List<PermissionsBean>> getAllPermission() {
		return ResponseEntity.ok(permissionsService.getAllPermissons());
	}

	@GetMapping(value = "/permissions/{permissionsId}")
	public ResponseEntity<PermissionsBean> getPermissionById(@PathVariable("permissionsId") int permissionsId) {
		return ResponseEntity.ok(permissionsService.findPermissonById(permissionsId));
	}

	@DeleteMapping(value = "/permissions/{permissionsId}")
	public ResponseEntity<List<PermissionsBean>> deleteAPermission(@PathVariable("permissionsId") int permissionsId) {
		return ResponseEntity.ok(permissionsService.deletePermissonById(permissionsId));
	}

	@PostMapping("/roles")
	public ResponseEntity<WeshopifyRolesBean> createRole(@RequestBody WeshopifyRolesBean rolesBean) {
		log.info("Role is creating with the data {}", rolesBean.toString());
		return ResponseEntity.ok(rolesService.createRole(rolesBean));

	}

	@PutMapping("/roles")
	public ResponseEntity<WeshopifyRolesBean> updateRole(@RequestBody WeshopifyRolesBean rolesBean) {
		log.info("Role is creating with the data {}", rolesBean.toString());
		return ResponseEntity.ok(rolesService.updateRole(rolesBean));

	}

	@GetMapping("/roles")
	public ResponseEntity<List<WeshopifyRolesBean>> findAllRoles() {
		List<WeshopifyRolesBean> rolesList = rolesService.getAllRoles();
		log.info("Roles retrived from db are {}", rolesList.size());
		return ResponseEntity.ok(rolesList);
	}

	@GetMapping("/roles/{roleId}")
	public ResponseEntity<WeshopifyRolesBean> getRoleByRoleId(@PathVariable("roleId") int roleId) {
		log.info("Roles are asking by the role id {}", roleId);
		return ResponseEntity.ok(rolesService.findRoleById(roleId));
	}

	@DeleteMapping("/roles/{roleId}")
	public ResponseEntity<List<WeshopifyRolesBean>> deleteRoleByRoleId(@PathVariable("roleId") int roleId) {
		log.info("Roles are asking by the role id {}", roleId);
		return ResponseEntity.ok(rolesService.deleteRoleById(roleId));
	}

}
