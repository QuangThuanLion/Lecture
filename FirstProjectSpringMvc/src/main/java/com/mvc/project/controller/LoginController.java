package com.mvc.project.controller;

import com.mvc.project.config.CustomValidator;
import com.mvc.project.constant.MappingUtils;
import com.mvc.project.dto.UserDTO;
import com.mvc.project.repositories.UserRepository;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomValidator customValidator;

    @GetMapping(value = "/login")
    public String loginPage(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "login-page";
    }

    /**
     * @param userDTO
     * @param bindingResult
     * @param model
     * @param request
     * @return
     */
    @PostMapping(path = "/loginFirst")
    public String loginFirstWithValidationAutomatic(@Valid @ModelAttribute(name = "userDTO") UserDTO userDTO,
                                                    BindingResult bindingResult,
                                                    Model model,
                                                    HttpServletRequest request)
    {
        return bindingModelLogin(userDTO, bindingResult, model, request);
    }

    /**
     * @param userDTO
     * @param errors
     * @param model
     * @param request
     * @return
     */
    @PostMapping(path = "/loginSecond")
    public String loginWithValidationManual(@ModelAttribute(name = "userDTO") UserDTO userDTO,
                        BindingResult errors,
                        Model model,
                        HttpServletRequest request)
    {
        if (userDTO.getName().isEmpty()) {
            errors.rejectValue("name", "userDTO", "Please enter username");
        }
        if (userDTO.getPassword().isEmpty()) {
            errors.rejectValue("password", "userDTO", "Please enter password");
        }
        return bindingModelLogin(userDTO, errors, model, request);
    }

    @PostMapping(path = "/login")
    public String loginFirstWithValidationCustom(@ModelAttribute(name = "userDTO") UserDTO userDTO,
                                                 BindingResult errors,
                                                 Model model,
                                                 HttpServletRequest request)
    {
        customValidator.validate(userDTO, errors);
        return bindingModelLogin(userDTO, errors, model, request);
    }

    /**
     * @param userDTO
     * @param errors
     * @param model
     * @param request
     * @return
     */
    private String bindingModelLogin(@ModelAttribute(name = "userDTO") UserDTO userDTO,
                                     BindingResult errors,
                                     Model model,
                                     HttpServletRequest request)
    {
        if (errors.hasErrors())
        {
            return "login-page";
        }

        UserDTO users = userRepository.findByUsernameAndPassword(userDTO.getName(), userDTO.getPassword());
        AtomicReference<String> directory = new AtomicReference<>("login-page");

        Optional.ofNullable(users).ifPresentOrElse(
                x -> {
                    directory.set("redirect:/products");
                    HttpSession session = request.getSession();
                    session.setAttribute(MappingUtils.SESSION_NAME, users);
                },
                () -> model.addAttribute("message", MappingUtils.MESSAGE_ERROR));

        return directory.get().toString();
    }

    /**
     * @param request
     * @return
     */
    @GetMapping(path = "/logout-controller")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute(MappingUtils.SESSION_NAME);

        Optional.ofNullable(userDTO).ifPresent(x -> {
            session.invalidate();
        });

        return "redirect:/login";
    }
}
