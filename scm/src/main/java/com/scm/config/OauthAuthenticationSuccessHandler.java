package com.scm.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.scm.constants.AppConstants;
import com.scm.daos.UserRepository;
import com.scm.entities.Providers;
import com.scm.entities.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OauthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(OauthAuthenticationSuccessHandler.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        logger.info("inside OauthAuthenticationSuccessHandler");

        // //identify the provider
        var oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        String authorizedClientRegistrationId = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
        logger.info(authorizedClientRegistrationId);

        OAuth2User authenticatedUser = oAuth2AuthenticationToken.getPrincipal();

        User dbUser = userRepository.findByEmail(authenticatedUser.getAttribute("email") !=null ? authenticatedUser.getAttribute("email") : authenticatedUser.getAttribute("login")+"@gmail.com").orElse(null);
        if(dbUser==null) {

        /*
         * DefaultOAuth2User defaultOAuth2User =
         * (DefaultOAuth2User)authentication.getPrincipal();
         */

        logger.info("user name : " + authenticatedUser.getName());
        authenticatedUser.getAttributes().forEach((key, value) -> {
            logger.info("{} => {}", key, value);
        });

        logger.info(authenticatedUser.getAuthorities().toString());

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        
        user.setEmailVerified(true);
        user.setEnabled(true);
        user.setProviderUserId(authenticatedUser.getName());
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        user.setPassword("password");
        

        if (authorizedClientRegistrationId.equalsIgnoreCase("google")) {

            logger.info("inside google sign in");
            user.setName(authenticatedUser.getAttribute("name"));
            user.setEmail(authenticatedUser.getAttribute("email"));
            user.setProfilePic(authenticatedUser.getAttribute("picture"));
            user.setAbout("the user logged in by google");
            user.setProvider(Providers.GOOGLE);
            

        } else if (authorizedClientRegistrationId.equalsIgnoreCase("github")) {

            logger.info("inside github sign in");
            user.setName(authenticatedUser.getAttribute("name"));
            user.setEmail(authenticatedUser.getAttribute("email") !=null ? authenticatedUser.getAttribute("email") : authenticatedUser.getAttribute("login")+"@gmail.com");
            user.setProfilePic(authenticatedUser.getAttribute("avatar_id"));
            user.setAbout("the user logged in by github");
            user.setProvider(Providers.GITHUB);
            

        } else {

            logger.info("Unknown Provider");

        }

        User savedUser = userRepository.save(user);
        logger.info("user saved : "+savedUser.getEmail());
        
        } else {
        logger.info("user is already present in database : "+dbUser.getEmail());
        }

        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
    }

}
