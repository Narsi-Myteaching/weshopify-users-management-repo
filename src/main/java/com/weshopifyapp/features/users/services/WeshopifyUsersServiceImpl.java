package com.weshopifyapp.features.users.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
	
	WeshopifyUsersServiceImpl(WeshopifyUsersRepository usersRepo,WeshopifyRolesRepository rolesRepo,ModelMapper modelMapper){
		this.usersRepo = usersRepo;
		this.rolesRepo = rolesRepo;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public WeshopifyUsersBean createUser(WeshopifyUsersBean usersBean) {
		WeshopifyUsers entity = mapBeanToEntity(usersBean);
		provisioning(entity);
		usersRepo.save(entity);
		return mapEntityToBean(entity);
	}

	@Override
	public WeshopifyUsersBean updateUser(WeshopifyUsersBean usersBean) {
		WeshopifyUsers entity = mapBeanToEntity(usersBean);
		String opType = usersBean.getUserRoles().getOperation();
		if(StringUtils.hasText(opType) && "deProvision".contentEquals(opType)) {
			deProvisioning(entity);
		}
		usersRepo.save(entity);
		return mapEntityToBean(entity);
	}

	@Override
	public WeshopifyUsersBean findUserById(int id) throws WeshopifyUsersException {
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
	public WeshopifyUsers provisioning(WeshopifyUsers users){
	  	Optional<WeshopifyRoles> userRole = Optional
	  								.ofNullable(users.getUserRoles())
	  								.map(role->rolesRepo.findById(role.getRoleId())).get();
		WeshopifyRoles roleInDB =  userRole.get();
	  	users.setUserRoles(roleInDB);
		return users;
	}

	@Override
	public WeshopifyUsers deProvisioning(WeshopifyUsers users) {
		users.setUserRoles(null);
		return users;
	}

	@Override
	public WeshopifyUsersBean enableUser(int id) throws WeshopifyUsersException {
		Optional.ofNullable(usersRepo.findById(id).get()).ifPresentOrElse(user->{
			user.setEnabled(true);
			usersRepo.save(user);
		}, ()->WeshopifyUsersException.builder().message("No User Found with User Id:\t"+id).build());
		
		return findUserById(id);
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
}
