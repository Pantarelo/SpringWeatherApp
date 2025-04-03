package com.fiipractic.controller;

import com.fiipractic.entity.User;
import com.fiipractic.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestParam String name, @RequestParam String username, @RequestParam String password) {
        User user = userService.createUser(name, username, password);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        Optional<User> user = userService.getUser(username);

        if (user.isEmpty()) {
            throw new RuntimeException("User with id: " + user.get().getId() + " not found");
        }

        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
