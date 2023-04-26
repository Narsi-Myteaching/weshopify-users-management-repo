/**
 * 
 */
package com.weshopifyapp.features.users.data.models;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeshopifyUsers implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -4136621746687875180L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "firstName",nullable = false)
	private String fname;
	
	@Column(name = "lastName",nullable = false)
	private String lname;
	
	@Column(name = "email",nullable = false,unique = true)
	private String email;
	
	@Column(name = "userId",nullable = false,unique = true)
	private String userId;
	
	@Column(name = "mobile",nullable = false,unique = true)
	private String mobile;
	
	@Column(name = "isEnabled",nullable = false)
	private boolean isEnabled;
	
	@Column(name = "isLocked",nullable = true)
	private boolean isLocked;
	
	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private WeshopifyRoles userRoles;

}
