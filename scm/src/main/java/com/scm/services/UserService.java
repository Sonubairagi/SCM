package com.scm.services;

import java.util.List;
import java.util.Optional;

import com.scm.forms.UserForm;
import com.scm.payloads.UserDTO;

public interface UserService {

    UserForm saveUser(UserForm UserForm);
    Optional<UserForm> getUserById(String id);
    Optional<UserDTO> updateUser(UserDTO userDTO);
    void deleteUser(String id);
    boolean isUserExist(String id);
    boolean isUserExistByEmail(String email);
    List<UserDTO> getAllUser();

}
