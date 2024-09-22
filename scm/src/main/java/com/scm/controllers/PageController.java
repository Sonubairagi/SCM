package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.scm.forms.UserForm;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home(Model Model) {
        System.out.println("inside home page");
        Model.addAttribute("name", "Sonu Vairagi");
        Model.addAttribute("URL", "www.google.com");
        return "home";
    }

    @RequestMapping("/about")
    public String aboutPage(Model model) {
        System.out.println("inside about page");
        model.addAttribute("isLogin", false);
        return "about";
    }

    @RequestMapping("/services")
    public String servicesPage() {
        System.out.println("inside services page loading...");
        return "services";
    }
    
    @RequestMapping("/contact")
    public String contactPage() {
        return "contact";
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }
    
    @GetMapping("/signUpPage")
    public String signUpPage(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "register";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult, HttpSession session) {

        System.out.println("inside signUp method");

        //validation form data
        if(bindingResult.hasErrors()) {
            return "register";
        }

        //call service to save data into database
        UserForm saveUser = userService.saveUser(userForm);

        //message = "Registration Successful!"
        Message message = Message.builder().message("Registration Successful!").type(MessageType.green).build();
        session.setAttribute("message", message);

        //redirect to login page
        return "redirect:/signUpPage";
    }


    // @GetMapping("/logout")
    // public String logout() {
    //     return "/do-logout";
    // }

}
