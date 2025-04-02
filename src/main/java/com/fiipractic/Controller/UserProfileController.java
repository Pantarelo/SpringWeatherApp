package com.fiipractic.controller;

import com.fiipractic.entity.UserProfile;
import com.fiipractic.service.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user-profile")
public class UserProfileController {
    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping
    public ResponseEntity<UserProfile> createUserProfile(@RequestBody UserProfile userProfile) {
        UserProfile savedUserProfile = userProfileService.createUserProfile(userProfile);
        return ResponseEntity.ok(savedUserProfile);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> getUserProfileById(@PathVariable Long id) {
        Optional<UserProfile> userProfile = userProfileService.getUserProfileById(id);

        return userProfile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserProfileById(@PathVariable Long id) {
         userProfileService.deleteUserProfileById(id);
         return ResponseEntity.noContent().build();
    }
}
