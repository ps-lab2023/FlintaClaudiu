package com.example.demo.service.implementation;

import com.example.demo.DTO.UserDTO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.messages.LoginMesage;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserServiceInterface;
import enums.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {this.userRepository = userRepository;}

    @Override
    public User findByUserName(String username) {return userRepository.findFirstByUserName(username);}

    @Override
    public User findUserById(Long id)
    {
        return userRepository.findFirstById(id);
    }
    @Override
    public UserDTO findUserByEmail(String email)
    {
        System.out.println(email);
        User user = userRepository.findUserByEmail(email);
        if (user != null) {
            return userMapper.mapModelToDto(user);
        }
        else return null;
    }

    @Override
    public User findUserByEmail2(String email)
    {
        System.out.println(email);
        User user = userRepository.findUserByEmail(email);
        if (user != null) {
            return user;
        }
        else return null;
    }


    @Override
    public User addUser(UserDTO userDTO)
    {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setTelephone(userDTO.getTelephone());
        user.setType(userDTO.getType());
        user.setStatus(UserStatus.DISCONNECTED);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
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
    public LoginMesage LogIn(String email, String password) {
        UserDTO user = this.findUserByEmail(email);
        if (user != null) {
            String encodedPassword = user.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if(isPwdRight) {
                User user1 = userRepository.findUserByEmailAndPassword(email, encodedPassword);
                if (user1 != null) {
                    user1.setStatus(UserStatus.CONNECTED);
                    userRepository.save(user1);
                    return new LoginMesage("Login Success", true, user1.getType(), user1.getFirstName());
                } else {
                    return new LoginMesage("Login Failed", false, user.getType(), user.getFirstName());
                }
            } else {

                return new LoginMesage("password Not Match", false, null, null);
            }
        }else {
            return new LoginMesage("Email not exits", false, null,null);
        }
    }

    @Override
    public Boolean changePassword(String email, String password, String newPassword)
    {
        User user = userRepository.findUserByEmail(email);
        String oldPass = user.getPassword();
        System.out.println(oldPass);
        System.out.println(password);
        Boolean isPwdRight = passwordEncoder.matches(password, oldPass);
        if(isPwdRight){
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public Boolean logout(String email)
    {
        User user = userRepository.findUserByEmail(email);
        if(user != null){
            user.setStatus(UserStatus.DISCONNECTED);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return userRepository.findAll()
                .stream().map(
                        UserMapper::mapModelToDto
                ).collect(Collectors.toList());
    }


}
