package com.mvc.project.validator;

import com.mvc.project.dto.CategoryDTO;
import java.util.Objects;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CategoryValidator implements Validator {
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return CategoryDTO.class.isAssignableFrom(clazz);
//    }
//
//    @Override
//    public void validate(Object object, Errors errors) {
//        CategoryDTO categoryDTO = (CategoryDTO) object;
//
//        if (categoryDTO.getName().isEmpty() || categoryDTO.getName() == null) {
//            errors.rejectValue("name", "categoryDTO", "error category customize");
//        }
//        if (categoryDTO.getName().length() > 10) {
//            errors.rejectValue("name", "categoryDTO", "category name cannot be exceed 10 customize");
//        }
//
//        ValidationUtils.rejectIfEmptyOrWhitespace(
//                errors,
//                "description",
//                "categoryDTO",
//                "description cannot be empty customize");
//        if (categoryDTO.getDescription().length() > 30) {
//            errors.rejectValue(
//                    "description",
//                    "categoryDTO",
//                    "category descriptions cannot be exceed 30 customize");
//        }
//    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CategoryDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CategoryDTO categoryDTO = (CategoryDTO) target;

        if (categoryDTO.getName().isEmpty() || Objects.isNull(categoryDTO.getName())) {
            errors.rejectValue("name", "NotEmpty.categoryDTO.name");
        }
        if (categoryDTO.getName().length() > 10) {
            errors.rejectValue("name", "Size.categoryDTO.name");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty.categoryDTO.description");
        if (categoryDTO.getDescription().length() > 30) {
            errors.rejectValue("description", "Size.categoryDTO.description");
        }
    }
}
