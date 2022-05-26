package com.scoresDei.repositories;

import com.scoresDei.data.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    public boolean existsUserByEmail(String email);

    public boolean existsUserByUsername(String username);

    public boolean existsUserByTelephone(String telephone);

    @Query("select u from User u where u.username=?1")
    public User findUserByUsername(String username);

}