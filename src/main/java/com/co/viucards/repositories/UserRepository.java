package com.co.viucards.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.viucards.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
