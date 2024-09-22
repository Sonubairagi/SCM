package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.val;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard() {

        System.out.println("inside userDashboard");

        return "/user/dashboard";
    }
    
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String userProfile() {
        System.out.println("inside user profile");
        return "/user/Profile";
    }

}
