package com.scm.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scm.constants.AppConstants;
import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.mapper.UserMapper;
import com.scm.payloads.UserDTO;

@Component
public class UserMapperImpl implements UserMapper {

    // @Autowired
    // private UserMapper userMapper;

    @Override
    public User mapToUser(UserForm userForm) {

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setMobileNumber(userForm.getMobileNumber());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setRoleList(List.of(AppConstants.ROLE_USER));

        return user;
    }

    @Override
    public UserForm mapToUserForm(User user) {
        
        UserForm userForm = new UserForm();
        userForm.setName(user.getName());
        userForm.setEmail(user.getEmail());
        userForm.setMobileNumber(user.getMobileNumber());
        userForm.setPassword(user.getPassword());
        userForm.setAbout(user.getAbout());

        return userForm;
    }

    @Override
    public List<User> mapToAllUserFromUserForm(List<UserForm> userForms) {

        UserMapperImpl userMapper = new UserMapperImpl();
        
        return userForms.stream().map(userMapper::mapToUser).collect(Collectors.toList());
    }

    @Override
    public List<UserForm> mapToAllUserForm(List<User> users) {

        UserMapperImpl userMapper = new UserMapperImpl();
       
        return users.stream().map(userMapper::mapToUserForm).collect(Collectors.toList());
    }

    @Override
    public User mapToUser(UserDTO userDTO) {

        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());
        user.setProfilePic(userDTO.getProfilePic());
        user.setMobileNumber(userDTO.getMobileNumber());
        user.setEnabled(userDTO.isEnabled());
        user.setEmailVerified(userDTO.isEmailVerified());
        user.setMobileNumberVerified(userDTO.isMobileNumberVerified());
        user.setProvider(userDTO.getProvider());
        user.setProviderUserId(userDTO.getProviderUserId());

        return user;

    }

    @Override
    public UserDTO mapToDTO(User user) {
        
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setAbout(user.getAbout());
        userDTO.setProfilePic(user.getProfilePic());
        userDTO.setMobileNumber(user.getMobileNumber());
        userDTO.setEnabled(user.isEnabled());
        userDTO.setEmailVerified(user.isEmailVerified());
        userDTO.setMobileNumberVerified(user.isMobileNumberVerified());
        userDTO.setProvider(user.getProvider());
        userDTO.setProviderUserId(user.getProviderUserId());

        return userDTO;
    }

    @Override
    public List<User> mapToAllUserFromUserDTO(List<UserDTO> userDTOs) {

        UserMapperImpl userMapper = new UserMapperImpl();
        
        return userDTOs.stream().map(userMapper::mapToUser).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> mapToAllUserDTO(List<User> users) {

        UserMapperImpl userMapper = new UserMapperImpl();
       
        return users.stream().map(userMapper::mapToDTO).collect(Collectors.toList());

    }

}
