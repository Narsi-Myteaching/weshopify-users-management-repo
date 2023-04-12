package com.weshopifyapp.features.users.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weshopifyapp.features.users.data.models.Roles;

public interface RolesRepository extends JpaRepository<Roles, Integer> {

}
