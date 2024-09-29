package com.scm.controllers;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.val;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.helper.LoggedInUserProvider;
import com.scm.payloads.UserDTO;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {

    Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private LoggedInUserProvider loggedInUserProvider;

    @Autowired
    private UserService userService;

    @PostMapping("/loggedIn")
    public void loggedIn(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        request.setAttribute("authentication", authentication);

        try {
            new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
        } catch (IOException e) {
            e.printStackTrace();
            log.info("Problem occured while redirection from loggedIn to dashborad : " +
                    e.getMessage());
        }
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(Authentication Authentication) {

        System.out.println("inside userDashboard");

        return "/user/dashboard";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String userProfile(Model model, Authentication Authentication) {
        System.out.println("inside user profile");

        return "/user/Profile";
    }

}
