package com.mvc.project.controller;

import com.mvc.project.constant.MappingUtils;
import com.mvc.project.dto.UserDTO;
import com.mvc.project.repositories.UserRepository;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login-page";
    }

    /**
     * @param email
     * @param password
     * @param model
     * @param request
     * @return
     */
    @PostMapping(path = "/login")
    public String login(@RequestParam(name = "email", defaultValue = "admin@gmail.com") String email,
                        @RequestParam(name = "password", defaultValue = "admin") String password,
                        Model model,
                        HttpServletRequest request) {
        UserDTO users = userRepository.findByUsernameAndPassword(email, password);
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
