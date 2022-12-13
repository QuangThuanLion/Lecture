package com.mvc.project.controller;

import com.mvc.project.validator.LoginValidator;
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
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "LoginController")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginValidator loginValidator;

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
    public ModelAndView loginFirstWithValidationAutomatic(@Valid @ModelAttribute(name = "userDTO") UserDTO userDTO,
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
    @PostMapping(path = "/login")
    public ModelAndView loginWithValidationManual(
                        @Valid @ModelAttribute(name = "userDTO") UserDTO userDTO,
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

    @PostMapping(path = "/loginSecond")
    public ModelAndView loginFirstWithValidationCustom(@ModelAttribute(name = "userDTO") UserDTO userDTO,
                                                 BindingResult errors,
                                                 Model model,
                                                 HttpServletRequest request)
    {
        loginValidator.validate(userDTO, errors);
        return bindingModelLogin(userDTO, errors, model, request);
    }

    /**
     * @param userDTO
     * @param errors
     * @param model
     * @param request
     * @return
     */
    private ModelAndView bindingModelLogin(@ModelAttribute(name = "userDTO") UserDTO userDTO,
                                     BindingResult errors,
                                     Model model,
                                     HttpServletRequest request)
    {
        if (errors.hasErrors())
        {
            return new ModelAndView("login-page");
//            return "login-page";
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

        return new ModelAndView(directory.get());
//        return directory.get();
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
