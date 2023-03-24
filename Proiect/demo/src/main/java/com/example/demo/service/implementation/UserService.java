package com.example.demo.service.implementation;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {this.userRepository = userRepository;}

    @Override
    public User findByUserName(String username) {return userRepository.findFirstByUserName(username);}

    @Override
    public User findUserById(Long id)
    {
        return userRepository.findFirstById(id);
    }

    @Override
    public User addUser(User user)
    {
        userRepository.save(user);

        return user;
    }

    @Override
    public User updateUserPassword(User user, String password)
    {
        System.out.println(user.getId());
        User updatedUser = userRepository.findFirstById(user.getId());
        if (updatedUser != null) {
            updatedUser.setPassword(password);
            userRepository.save(updatedUser);
        }

        return  updatedUser;
    }

    /**
     *
     * @param user
     * @return true if user was deleted from table, false if not
     */
    @Override
    public Boolean deleteUser(User user)
    {
        userRepository.deleteById(user.getId());
        if(userRepository.existsById(user.getId()))
            return false;
        return true;
    }

    @Override
    public User LogIn(String userName, String password) {
        return userRepository.findUsersByUserNameAndPassword(userName, password) ;
    }



}
