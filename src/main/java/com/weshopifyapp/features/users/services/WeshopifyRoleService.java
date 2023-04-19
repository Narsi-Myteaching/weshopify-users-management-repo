package com.weshopifyapp.features.users.services;

import java.util.List;

import com.weshopifyapp.features.users.beans.WeshopifyRolesBean;
import com.weshopifyapp.features.users.data.exceptions.WeshopifyRolesException;

public interface WeshopifyRoleService {

	WeshopifyRolesBean createRole(WeshopifyRolesBean pbean) throws WeshopifyRolesException;
	WeshopifyRolesBean updateRole(WeshopifyRolesBean pbean) throws WeshopifyRolesException;
	WeshopifyRolesBean findRoleById(int roleId) throws WeshopifyRolesException;
	WeshopifyRolesBean findRoleByName(String role) throws WeshopifyRolesException;
	List<WeshopifyRolesBean> deleteRoleById(int roleId) throws WeshopifyRolesException;
	List<WeshopifyRolesBean> getAllRoles() throws WeshopifyRolesException;
	List<WeshopifyRolesBean> searchRoles();
	
}
