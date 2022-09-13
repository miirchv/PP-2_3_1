package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UsersController {

    private UserService userService;
    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("allusers", userService.getAllUsers());
        return "users/allusers";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") int id, Model model) {
        model.addAttribute("userbyid", userService.getUserById(id));
        return "users/userbyid";
    }

    @GetMapping("/saveuser")
    public String newUser(@ModelAttribute("newuser") User user) {
        return "users/newuser";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("newuser") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/updateuser")
    public String editUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("edituser", userService.getUserById(id));
        return "/users/edituser";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("edituser") User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
