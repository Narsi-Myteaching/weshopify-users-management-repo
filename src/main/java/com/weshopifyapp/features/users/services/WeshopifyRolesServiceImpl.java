package com.weshopifyapp.features.users.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.weshopifyapp.features.users.beans.PermissionsBean;
import com.weshopifyapp.features.users.beans.WeshopifyRolesBean;
import com.weshopifyapp.features.users.data.exceptions.WeshopifyRolesException;
import com.weshopifyapp.features.users.data.models.Permissions;
import com.weshopifyapp.features.users.data.models.RoleToPermissions;
import com.weshopifyapp.features.users.data.models.WeshopifyRoles;
import com.weshopifyapp.features.users.data.repository.PermissionsRepository;
import com.weshopifyapp.features.users.data.repository.WeshopifyRolesRepository;

@Service
public class WeshopifyRolesServiceImpl implements WeshopifyRoleService {

	private WeshopifyRolesRepository rolesrepo;
	private ModelMapper modelMapper;
	
	private PermissionsRepository permissionsRepo;

	public WeshopifyRolesServiceImpl(WeshopifyRolesRepository rolesrepo, ModelMapper modelMapper
			,PermissionsRepository permissionsRepo) {
		this.rolesrepo = rolesrepo;
		this.modelMapper = modelMapper;
		this.permissionsRepo = permissionsRepo;
	}

	@Override
	public WeshopifyRolesBean createRole(WeshopifyRolesBean roleBean){

		WeshopifyRoles roles = Optional.ofNullable(rolesrepo.save(mapBeanToEntity(roleBean)))
				.orElseThrow(() -> WeshopifyRolesException.builder().message("Roles Creation Failed").build());
		return mapEntityToBean(roles);
	}

	@Override
	public WeshopifyRolesBean updateRole(WeshopifyRolesBean roleBean) throws WeshopifyRolesException {
		WeshopifyRoles roles = Optional.ofNullable(rolesrepo.save(mapBeanToEntity(roleBean)))
				.orElseThrow(() -> WeshopifyRolesException.builder().message("Roles updation Failed").build());
		return mapEntityToBean(roles);
	}

	@Override
	public WeshopifyRolesBean findRoleById(int roleId) throws WeshopifyRolesException {
		WeshopifyRoles roles =  Optional.ofNullable(rolesrepo.findById(roleId))
								.orElseThrow(()-> WeshopifyRolesException.builder().message("").build())
								.get();
		return mapEntityToBean(roles);
	}

	@Override
	public List<WeshopifyRolesBean> deleteRoleById(int roleId) throws WeshopifyRolesException {
		Optional.of(rolesrepo.existsById(roleId)).ifPresentOrElse((status)->rolesrepo.deleteById(roleId), 
				()->WeshopifyRolesException.builder().message("No Role Found with the Role Id:\t"+roleId).build());
		
		return getAllRoles();
	}

	@Override
	public List<WeshopifyRolesBean> getAllRoles() throws WeshopifyRolesException {
		List<WeshopifyRoles> rolesList =  Optional.ofNullable(rolesrepo.findAll())
												.orElseThrow(()->WeshopifyRolesException.builder()
																.message("No Roles Found in the Database")
														.build());
		List<WeshopifyRolesBean> roleBeansList = new ArrayList<>();
		if(Optional.of(rolesList).isPresent()) {
					rolesList.stream().forEach(role->roleBeansList.add(mapEntityToBean(role)));
		}
		
		return roleBeansList;
	}

	@Override
	public List<WeshopifyRolesBean> searchRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	private WeshopifyRoles mapBeanToEntity(WeshopifyRolesBean rolesBean) {
		List<RoleToPermissions> list =new ArrayList<>();
		WeshopifyRoles roles = modelMapper.map(rolesBean, WeshopifyRoles.class);
		roles.setCreatedBy("Admin");
		roles.setModifiedBy("Admin");
		Date now = new Date();
		roles.setCreatedDate(now);
		roles.setModifiedDate(now);
		
		if(rolesBean.getPermissions() != null && rolesBean.getPermissions().size() >0) {
			rolesBean.getPermissions().stream().forEach(permissionsBean->{
				RoleToPermissions roleToPermissions = new RoleToPermissions();
				
				Permissions permissionsEntity = permissionsRepo.findById(permissionsBean.getPermissionId()).get();
				roleToPermissions.setPermissions(permissionsEntity);
				roleToPermissions.setRoles(roles);
				list.add(roleToPermissions);
			});
		}
		
		roles.setRole_to_permissions(list);		

		return roles;
	}

	private WeshopifyRolesBean mapEntityToBean(WeshopifyRoles roles) {
		WeshopifyRolesBean rolesBean =  modelMapper.map(roles, WeshopifyRolesBean.class);
		
		List<PermissionsBean> permissionsList =  Optional.ofNullable(roles.getRole_to_permissions()).get().stream()
												.map(roleToPermission->roleToPermission.getPermissions())
												.map(permissions->modelMapper.map(permissions, PermissionsBean.class))
												.collect(Collectors.toList());
		  
		rolesBean.setPermissions(permissionsList);
		
		return rolesBean;
	}

	@Override
	public WeshopifyRolesBean findRoleByName(String role) throws WeshopifyRolesException {
		// TODO Auto-generated method stub
		return null;
	}

}
