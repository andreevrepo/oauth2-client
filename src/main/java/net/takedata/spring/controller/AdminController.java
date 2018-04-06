package net.takedata.spring.controller;


import net.takedata.spring.model.Role;
import net.takedata.spring.model.User;
import net.takedata.spring.service.ServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
public class AdminController {

    @Autowired
    private ServiceI<User> userService;

    @Autowired
    private ServiceI<Role> roleService;


    @GetMapping
    public String adminPanel(Model model) {
        model.addAttribute("users", userService.getAll());
        model.addAttribute("addUser", new User());
        model.addAttribute("roles", roleService.getAll());
        model.addAttribute("title", "Admin Panel");
        return "/admin";
    }

    @PostMapping(params = "add")
    public String add(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("errors");
        }
        user.setRole(roleService.get(user.getRole().getRole()));
        userService.add(user);
        return "redirect:/admin";
    }


    @PostMapping("/update")
    public String update(@RequestParam String id, @RequestParam String email, @RequestParam String password, @RequestParam String name, @RequestParam String role) {
        Role temp = roleService.get(role);
        User user = userService.get(Long.parseLong(id)).get();
        user.setRole(temp);
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        userService.update(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam long id) {
        userService.delete(id);
        return "redirect:/admin";
    }


}
