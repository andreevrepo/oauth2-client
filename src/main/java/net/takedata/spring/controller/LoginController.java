package net.takedata.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping
    public String login(Model model) {
        model.addAttribute("title", "Sign in");
        return "/login";
    }

    @GetMapping(params = "error")
    public String loginErr(Model model){
        model.addAttribute("loginError", true);
        return "/login";
    }

    @GetMapping("principal")
    public Principal test(Principal principal){
        return principal;
    }
}
