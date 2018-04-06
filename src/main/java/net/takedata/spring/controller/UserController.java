package net.takedata.spring.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAnyAuthority('ROLE_USER,ROLE_ADMIN')")
public class UserController {

    @GetMapping
    public String user()
    {
        return "/user";
    }
}
