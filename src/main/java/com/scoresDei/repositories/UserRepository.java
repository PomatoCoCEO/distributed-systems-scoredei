package com.scoresDei.repositories;

import com.scoresDei.data.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}