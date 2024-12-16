package ru.cubrick.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import ru.cubrick.web.models.User;
import ru.cubrick.web.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping( "/users")
    public String usersPage(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping( "/new_user")
    public String newUserPage(Model model) {
        model.addAttribute("user", new User());
        return "new_user";
    }
    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        userService.persist(user);
        return "redirect:/users";
    }
}
