package com.example.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        if (userid == pw_check) {
            User user = new User();
            user.setUserid(userid);

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodedPassword = encoder.encode(password);
            user.setPassword(encodedPassword);

            userRepository.save(user);
            return "redirect:/";
        } else {
            return "redirect:/register";
        }
    }
}
