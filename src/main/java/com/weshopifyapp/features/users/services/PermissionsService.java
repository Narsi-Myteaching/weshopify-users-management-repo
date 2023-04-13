package com.weshopifyapp.features.users.services;

import java.util.List;

import com.weshopifyapp.features.users.beans.PermissionsBean;
import com.weshopifyapp.features.users.data.exceptions.PermissionsNotFoundException;

public interface PermissionsService {

	PermissionsBean createPermisson(PermissionsBean pbean) throws PermissionsNotFoundException;
	PermissionsBean updatePermisson(PermissionsBean pbean);
	PermissionsBean findPermissonById(int permissionsId);
	List<PermissionsBean> deletePermissonById(int permissionsId);
	List<PermissionsBean> getAllPermissons();
	List<PermissionsBean> searchPermissons();
}
