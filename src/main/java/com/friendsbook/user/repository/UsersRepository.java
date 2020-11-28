package com.friendsbook.user.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.friendsbook.user.model.User;

@Repository
public interface UsersRepository extends CrudRepository<User, Integer>{

	User findByEmail(String email);

}
