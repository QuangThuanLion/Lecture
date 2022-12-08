package com.mvc.project.config;

import com.mvc.project.dto.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CustomValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO userDTO = (UserDTO) target;
        if (userDTO.getName().isEmpty() || userDTO.getName().isBlank()) {
            errors.rejectValue("name", "useDTO", "Please enter username with validation custom !");
        }
        if (userDTO.getPassword().isEmpty() || userDTO.getPassword().isBlank()) {
            errors.rejectValue("password", "userDTO","Please enter password with validation custom !");
        }
    }
}
