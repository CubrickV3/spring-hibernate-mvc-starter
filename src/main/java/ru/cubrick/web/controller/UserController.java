package ru.cubrick.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cubrick.web.models.User;
import ru.cubrick.web.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String usersPage(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users/all_users";
    }

    @GetMapping("/new_user")
    public String newUserPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "users/new_user";
    }
    @PostMapping
    public String newUser(@ModelAttribute("user") User user) {
        userService.persist(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "users/edit_user";
    }
    @PatchMapping("/{id}")
    public String editUser(@ModelAttribute("user") User user, @PathVariable Long id) {
        userService.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.remove(id);
        return "redirect:/users";
    }
}
