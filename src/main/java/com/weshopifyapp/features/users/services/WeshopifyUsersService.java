package com.weshopifyapp.features.users.services;

import java.util.List;

import com.weshopifyapp.features.users.beans.WeshopifyUsersBean;
import com.weshopifyapp.features.users.data.exceptions.WeshopifyRolesException;
import com.weshopifyapp.features.users.data.exceptions.WeshopifyUsersException;
import com.weshopifyapp.features.users.data.models.WeshopifyUsers;

public interface WeshopifyUsersService {

	WeshopifyUsersBean createUser(WeshopifyUsersBean usersBean);
	WeshopifyUsersBean updateUser(WeshopifyUsersBean usersBean);
	WeshopifyUsersBean findUserById(int id) throws WeshopifyUsersException;
	WeshopifyUsersBean findUserByEmail(String email);
	List<WeshopifyUsersBean> findAllUsers();
	List<WeshopifyUsersBean> findAllUsers(int currPage, int noOfRecPerPage);
	List<WeshopifyUsersBean> deleteUser(WeshopifyUsersBean usersBean) throws WeshopifyUsersException;
	List<WeshopifyUsersBean> deleteUser(int id) throws WeshopifyUsersException;
	
	/**
	 * Search Criteria yet to decides
	 * @return
	 */
	List<WeshopifyUsersBean> searchUsers();
	
	/**
	 * Assiging the role to a user is called as provisioing
	 * @param role
	 * @return
	 * @throws WeshopifyRolesException 
	 */
	WeshopifyUsers provisioning(WeshopifyUsers usersBean);
	
	/**
	 * Un assign the role to a user is called as de-provisioning 
	 * @param role
	 * @return
	 */
	WeshopifyUsers deProvisioning(WeshopifyUsers user);
	
	/**
	 * By Default Every Created User will be in the disabled and locked state
	 * we have to enable the user and unlock the user. 
	 * @param id
	 * @return
	 * @throws WeshopifyUsersException 
	 */
	WeshopifyUsersBean enableUser(int id) throws WeshopifyUsersException;
	
	/**
	 * By Default Every Created User will be in the disabled and locked state
	 * we have to enable the user and unlock the user. 
	 * @param id
	 * @return
	 */
	WeshopifyUsersBean unlockUser(int id);
	
	
	
}
