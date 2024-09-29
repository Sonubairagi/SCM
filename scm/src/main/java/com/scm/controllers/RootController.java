package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.helper.LoggedInUserProvider;
import com.scm.payloads.UserDTO;
import com.scm.services.UserService;

@ControllerAdvice
public class RootController {

  @Autowired
  private LoggedInUserProvider loggedInUserProvider;

  @Autowired
  private UserService userService;

  @ModelAttribute
  public void addloggedInUserInfoInEachUserResponse(Model model, Authentication authentication) {

    if (authentication == null) {
      return;
    }

    String email = loggedInUserProvider.getLoggedInUser(authentication);

    System.out.println(email);

    UserDTO userDto = userService.getUserByEmail(email);

    model.addAttribute("user", userDto);
  }

}
