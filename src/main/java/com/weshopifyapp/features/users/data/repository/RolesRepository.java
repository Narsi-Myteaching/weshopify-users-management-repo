package com.weshopifyapp.features.users.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weshopifyapp.features.users.data.models.WeshopifyRoles;

public interface RolesRepository extends JpaRepository<WeshopifyRoles, Integer> {

}
