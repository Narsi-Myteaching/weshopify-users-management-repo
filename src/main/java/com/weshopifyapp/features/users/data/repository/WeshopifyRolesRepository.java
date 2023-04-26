package com.weshopifyapp.features.users.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.weshopifyapp.features.users.data.models.WeshopifyRoles;

public interface WeshopifyRolesRepository extends JpaRepository<WeshopifyRoles, Integer> {

	@Query("from WeshopifyRoles roles where roles.name=:name")
	Optional<WeshopifyRoles> findByName(String name);
}
