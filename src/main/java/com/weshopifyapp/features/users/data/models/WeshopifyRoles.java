package com.weshopifyapp.features.users.data.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
//@Builder
@NoArgsConstructor
public class WeshopifyRoles implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2333742779946866837L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int roleId;
	
	@Column(nullable = false,unique = true)
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "roles",fetch = FetchType.EAGER)
	private List<RoleToPermissions> role_to_permissions;
	
	private String createdBy;
	private String modifiedBy;
	private Date createdDate;
	private Date modifiedDate;
}
