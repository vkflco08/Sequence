package com.example.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    String getRegister() {
        return "register.html";
    }

    @PostMapping("/register")
    String createUser(@RequestParam("userid") String userid,
                      @RequestParam("password") String password,
                      @RequestParam("pw_check") String pw_check) {
        log.info("userid: {}", userid);

        if (password.equals(pw_check)) {
            Member user = new Member();
            user.setUserid(userid);

            String encodedPassword = passwordEncoder.encode(password);
            user.setPassword(encodedPassword);

            userRepository.save(user);
            log.info("User registered successfully: {}", userid);
            return "redirect:/";
        } else {
            log.warn("Password mismatch for userid: {}", userid);
            return "redirect:/register";
        }
    }

    @GetMapping("/login")
    String getLogin() {
        return "login.html";
    }

    @PostMapping("/login")
    String loginUser() {
        return "redirect:/";
    }
}
