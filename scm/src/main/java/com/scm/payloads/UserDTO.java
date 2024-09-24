package com.scm.payloads;

import com.scm.entities.Providers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private String Id;

    private String name;

    private String email;

    private String password;

    private String about;

    private String profilePic;

    private String mobileNumber;

    private boolean enabled;

    private boolean emailVerified;

    private boolean mobileNumberVerified;

    private Providers provider = Providers.SELF;

    private String providerUserId;

    // private List<Contact> contacts = new ArrayList<Contact>();
}
