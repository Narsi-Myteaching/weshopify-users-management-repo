package com.weshopifyapp.features.users.services;

import java.util.List;

import com.weshopifyapp.features.users.beans.PermissionsBean;
import com.weshopifyapp.features.users.data.exceptions.PermissionsNotFoundException;

public interface PermissionsService {

	PermissionsBean createPermisson(PermissionsBean pbean) throws PermissionsNotFoundException;
	PermissionsBean updatePermisson(PermissionsBean pbean) throws PermissionsNotFoundException;
	PermissionsBean findPermissonById(int permissionsId) throws PermissionsNotFoundException;
	List<PermissionsBean> deletePermissonById(int permissionsId) throws PermissionsNotFoundException;
	List<PermissionsBean> getAllPermissons();
	List<PermissionsBean> searchPermissons();
}
