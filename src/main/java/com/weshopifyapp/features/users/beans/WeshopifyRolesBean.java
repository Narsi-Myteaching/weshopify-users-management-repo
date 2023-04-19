package com.weshopifyapp.features.users.beans;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class WeshopifyRolesBean implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5877471833946737824L;

	private int roleId;
	
	private String name;
	
	private List<PermissionsBean> permissions;
	
	/**
	 * op type is: provisioning/deprovisioning
	 */
	private String operation;
}
