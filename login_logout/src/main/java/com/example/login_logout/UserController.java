package com.example.login_logout;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

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
            User user = new User();
            user.setUserid(userid);

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodedPassword = encoder.encode(password);
            user.setPassword(encodedPassword);

            userRepository.save(user);
            log.info("User registered successfully: {}", userid);
            return "redirect:/";
        } else {
            log.warn("Password mismatch for userid: {}", userid);
            return "redirect:/register";
        }
    }
}
