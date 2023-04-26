/**
 * 
 */
package com.weshopifyapp.features.users.beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeshopifyUsersBean implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -4136621746687875180L;
	
	private int id;
	
	private String fname;
	
	private String lname;
	
	private String email;
	
	private String userId;
	
	private String mobile;
	
	private boolean isEnabled;
	
	private boolean isLocked;
	
	private WeshopifyRolesBean userRoles;
	private boolean isSelfReg;

}
