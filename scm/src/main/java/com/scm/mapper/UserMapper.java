package com.scm.mapper;

import java.util.List;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.payloads.UserDTO;

public interface UserMapper {

    User mapToUser(UserForm userForm);
    
    UserForm mapToUserForm(User user);

    List<User> mapToAllUserFromUserForm(List<UserForm> userForms);

    List<UserForm> mapToAllUserForm(List<User> users);

    User mapToUser(UserDTO userDTO);
    
    UserDTO mapToDTO(User user);

    List<User> mapToAllUserFromUserDTO(List<UserDTO> userDTOs);

    List<UserDTO> mapToAllUserDTO(List<User> users);

}
