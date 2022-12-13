package com.mvc.project.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

//    @NotEmpty(message = "username cannot empty")
//    @NotNull(message = "username cannot null")
//    @Email(message = "invalid formatter email. Please correct format !")
//    private String name;
//
//    @NotNull(message = "password cannot null")
//    @NotEmpty(message = "password cannot empty")
//    private String password;

    private String name;

    private String password;
}
