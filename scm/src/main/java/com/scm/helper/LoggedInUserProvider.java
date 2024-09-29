package com.scm.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class LoggedInUserProvider {

  Logger logger = LoggerFactory.getLogger(LoggedInUserProvider.class);

  public static String getLoggedInUser(Authentication authentication) {

    String email = null;

    if (authentication instanceof OAuth2AuthenticationToken) {

      OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
      String authClineNameId = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

      if ("Google".equalsIgnoreCase(authClineNameId)) {

        String eml = oAuth2AuthenticationToken.getPrincipal().getAttribute("email").toString();
        System.out.println("inside login with google - email : " + eml);
        return eml;

      } else if ("Github".equalsIgnoreCase(authClineNameId)) {

        String eml = oAuth2AuthenticationToken.getPrincipal().getAttribute("email") != null
            ? oAuth2AuthenticationToken.getPrincipal().getAttribute("email")
            : oAuth2AuthenticationToken.getPrincipal().getAttribute("login") + "@gmail.com";
        System.out.println("inside login with github - email : " + eml);
        return eml;

      }

    } else {
      System.out.println(authentication.getName());
      return authentication.getName();
    }

    return email;
  }

}
