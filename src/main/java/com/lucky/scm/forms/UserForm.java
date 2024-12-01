package com.lucky.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserForm {

    @NotBlank(message = "Username is required")
    @Size(min = 3,message = "Minimum 3 character is required")
    private String name;

    @Email(message = "Invailid email")
    private String email;

    @Size(min = 8,message = "Minimum 8 character is required")
    private String password;

    @NotBlank(message = "About is required")
    private String about;

    @NotBlank(message = "Phone number is required")
    @Size(min = 10,message = "Minimum 10 digits mobile number menedatry")
    private String phoneNumber;
}
