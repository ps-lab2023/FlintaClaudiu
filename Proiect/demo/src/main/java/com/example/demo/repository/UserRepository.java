package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findUsersByUserNameAndPassword(String userName, String password);
    User findFirstByUserName(String username);
    User findFirstById(Long id);
    User findUserById(Long id);
}
