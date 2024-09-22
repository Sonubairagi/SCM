package com.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserForm {

    private String id;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 100, message = "Name minimum and maximum size can be 3 and 100")
    private String name;
    
    @NotBlank(message = "Email is required")
    @Email(message = "invalid email")
    private String email;

    @Size(max = 20, message = "Mobile Number max size should be 20")
    @NotBlank(message = "Mobile Number is required")
    private String mobileNumber;

    @Size(max = 20, message = "Password max size should be 20")
    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "About is required")
    @Size(max = 20, message = "Please write about within 20 charactors")
    private String about;

}
