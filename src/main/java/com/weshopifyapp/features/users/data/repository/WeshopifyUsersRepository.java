package com.weshopifyapp.features.users.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weshopifyapp.features.users.data.models.WeshopifyUsers;

public interface WeshopifyUsersRepository extends JpaRepository<WeshopifyUsers, Integer> {

}
