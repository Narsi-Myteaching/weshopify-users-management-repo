package com.weshopifyapp.features.users.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.weshopifyapp.features.users.beans.WeshopifyRolesBean;
import com.weshopifyapp.features.users.beans.WeshopifyUsersBean;
import com.weshopifyapp.features.users.data.exceptions.WeshopifyUsersException;
import com.weshopifyapp.features.users.data.models.WeshopifyRoles;
import com.weshopifyapp.features.users.data.models.WeshopifyUsers;
import com.weshopifyapp.features.users.data.repository.WeshopifyRolesRepository;
import com.weshopifyapp.features.users.data.repository.WeshopifyUsersRepository;

@Service
public class WeshopifyUsersServiceImpl implements WeshopifyUsersService {

	private WeshopifyUsersRepository usersRepo;
	private WeshopifyRolesRepository rolesRepo;
	private ModelMapper modelMapper;
	
	@Value("${weshopify.self-reg.role}")
	private String selfRegRole;
	
	WeshopifyUsersServiceImpl(WeshopifyUsersRepository usersRepo,WeshopifyRolesRepository rolesRepo,ModelMapper modelMapper){
		this.usersRepo = usersRepo;
		this.rolesRepo = rolesRepo;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public WeshopifyUsersBean createUser(WeshopifyUsersBean usersBean) {
		
		WeshopifyUsers entity = provisioning(usersBean);
		usersRepo.save(entity);
		return mapEntityToBean(entity);
	}

	@Override
	public WeshopifyUsersBean updateUser(WeshopifyUsersBean usersBean) {
		WeshopifyUsers entity = provisioning(usersBean);
		String opType = usersBean.getUserRoles().getOperation();
		if(StringUtils.hasText(opType) && "deProvision".contentEquals(opType)) {
			deProvisioning(entity);
		}
		usersRepo.save(entity);
		return mapEntityToBean(entity);
	}

	@Override
	public WeshopifyUsersBean findUserById(int id) {
		return mapEntityToBean(Optional.ofNullable(usersRepo.findById(id))
				                        .orElseThrow(()->WeshopifyUsersException.builder().message("NO User Found with the Id:\t"+id)
				                        .build())
				                        .get());
	}

	@Override
	public WeshopifyUsersBean findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WeshopifyUsersBean> findAllUsers() {
	    return Optional.ofNullable(usersRepo.findAll())
	    							.get()
	    							.stream()
	    							.map(user->mapEntityToBean(user))
	    							.collect(Collectors.toList());
		
	}

	@Override
	public List<WeshopifyUsersBean> findAllUsers(int currPage, int noOfRecPerPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WeshopifyUsersBean> deleteUser(WeshopifyUsersBean usersBean) throws WeshopifyUsersException {
	 	boolean isUserExisted =  Optional
		.ofNullable(usersRepo.existsById(usersBean.getId()))
		.orElseThrow(()->WeshopifyUsersException.builder().message("No User Found with the Id:\t"+usersBean.getId()).build());
	 	
	 	
	 	if(isUserExisted) {
	 		usersRepo.delete(mapBeanToEntity(usersBean));
	 	}
	 	
	 	return findAllUsers();
	}

	@Override
	public List<WeshopifyUsersBean> deleteUser(int id) throws WeshopifyUsersException {
		boolean isUserExisted =  Optional
				.ofNullable(usersRepo.existsById(id))
				.orElseThrow(()->WeshopifyUsersException.builder().message("No User Found with the Id:\t"+id).build());
			 	
			 	
			 	if(isUserExisted) {
			 		usersRepo.deleteById(id);
			 	}
			 	
			 	return findAllUsers();
	}

	@Override
	public List<WeshopifyUsersBean> searchUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WeshopifyUsers provisioning(WeshopifyUsersBean usersBean){
		WeshopifyRoles roleInDB =  null;
		WeshopifyUsers userEntity = null;
		
		if(usersBean.getUserRoles() != null) {
			Optional<WeshopifyRoles> userRole = Optional
						.ofNullable(usersBean.getUserRoles())
						.map(role->rolesRepo.findById(role.getRoleId())).get();
			roleInDB =  userRole.get();
		}
	  	
		/**
		 * incase of update user we will pass the id so that it will verify the 
		 * user existed in db or not
		 */
		if(usersBean.getId() >0 && usersRepo.existsById(usersBean.getId())) {
			//userEntity = usersRepo.findById(usersBean.getId()).get();
			userEntity = mapBeanToEntity(usersBean);
			userEntity.setUserRoles(roleInDB);
		}else {
			/**
			 * in the case of the CreateUser
			 */
			userEntity = mapBeanToEntity(usersBean);
			userEntity.setUserRoles(roleInDB);
		}
		
				
		return userEntity;
	}

	@Override
	public WeshopifyUsers deProvisioning(WeshopifyUsers users) {
		users.setUserRoles(null);
		return users;
	}

	@Override
	public WeshopifyUsersBean enableUser(int id) throws WeshopifyUsersException {
		/*
		 * Optional.ofNullable(usersRepo.findById(id).get()).ifPresentOrElse(user->{
		 * user.setEnabled(true); //role shuld be provisionined usersRepo.save(user); },
		 * ()->WeshopifyUsersException.builder().message("No User Found with User Id:\t"
		 * +id).build());
		 * 
		 * return findUserById(id);
		 */
		return provisioningRoleToUser(id);
	}

	@Override
	public WeshopifyUsersBean unlockUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private WeshopifyUsers mapBeanToEntity(WeshopifyUsersBean userBean) {
		return modelMapper.map(userBean, WeshopifyUsers.class);
	}
	
	private WeshopifyUsersBean mapEntityToBean(WeshopifyUsers users) {
		return modelMapper.map(users, WeshopifyUsersBean.class);
	}

	@Override
	public WeshopifyUsersBean provisioningRoleToUser(int userId) {
		
		/**
		 * step-1: with the customer role name, 
		 * get the role details from the db
		 */
		Optional<WeshopifyRoles> userRole = rolesRepo.findByName(selfRegRole);
		WeshopifyRoles roleInDB =  userRole.get();
		
		/**
		 * with the userId get the user details from the DB
		 * and assign the role
		 */
		WeshopifyUsers userEntity = usersRepo.findById(userId).get();
		userEntity.setUserRoles(roleInDB);
		userEntity.setEnabled(true);
		userEntity.setLocked(false);
		
		/**
		 * save the updated user in the db and convert it into a bean
		 */
		return mapEntityToBean(usersRepo.save(userEntity));
	}
}
