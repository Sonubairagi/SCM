package com.scm.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.catalina.mbeans.UserMBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.daos.UserRepository;
import com.scm.entities.User;
import com.scm.exceptions.ResourceNotFoundException;
import com.scm.forms.UserForm;
import com.scm.mapper.UserMapper;
import com.scm.payloads.UserDTO;
import com.scm.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserMapper userMapper;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserForm saveUser(UserForm userForm) {

        System.out.println("inside saveUser method of UserService");
        log.info("inside saveUser of UserService");

        //map from UserForm to User
        User user = userMapper.mapToUser(userForm);

        //assign a unique id to user
        user.setId(UUID.randomUUID().toString());

        //Encode the password before storing it into database
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //save into database
        User saveUser = userRepo.save(user);

        //map from User to UserForm
        UserForm mappedUserForm = userMapper.mapToUserForm(saveUser);

        return mappedUserForm;
    }

    @Override
    public Optional<UserForm> getUserById(String id) {
        
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource was not found for id : "+id));
        return Optional.ofNullable(userMapper.mapToUserForm(user));
    }

    @Override
    public Optional<UserDTO> updateUser(UserDTO UserDTO) {
        User user = userRepo.findById(UserDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Resource not found for id : "+UserDTO.getId()));
        
        //get the value form UserDTO and store in User object and the update/save
        User mappedUser = userMapper.mapToUser(UserDTO);
        
        User savedUser = userRepo.save(mappedUser);

        return Optional.ofNullable(userMapper.mapToDTO(savedUser));
    }

    @Override
    public void deleteUser(String id) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found for id : "+id));
        userRepo.delete(user);
    }

    @Override
    public boolean isUserExist(String id) {
       User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found for id : "+id));
       return user!=null ? true : false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User user = userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Resource not found for email : "+email));
        return user!=null ? true : false;
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<User> users = userRepo.findAll();

        //map from User to UserDTO
        List<UserDTO> userDTOs = userMapper.mapToAllUserDTO(users);

        return userDTOs;
    }

}
