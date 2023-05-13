package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUsersByUserNameAndPassword(String userName, String password);
    User findUserByEmailAndPassword(String email, String password);
    User findFirstByUserName(String username);
    User findFirstById(Long id);
    User findUserById(Long id);
    User findUserByEmail(String email);
}
