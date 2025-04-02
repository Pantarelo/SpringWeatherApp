package com.fiipractic.service;

import com.fiipractic.entity.User;
import com.fiipractic.entity.UserProfile;
import com.fiipractic.repository.UserProfileRepository;
import com.fiipractic.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;

    public UserProfileService(UserProfileRepository userProfileRepository, UserRepository userRepository) {
        this.userProfileRepository = userProfileRepository;
        this.userRepository = userRepository;
    }

    public UserProfile createUserProfile(UserProfile userProfile) {
        Optional<User> user = userRepository.findById(userProfile.getUser().getId());

        if (user.isEmpty()) {
            throw new RuntimeException("User with id: " + userProfile.getUser().getId() + " does not exist");
        }

        userProfile.setUser(user.get());
        return userProfileRepository.save(userProfile);
    }

    public Optional<UserProfile> getUserProfileById(Long id) {
        return userProfileRepository.findById(id);
    }

    @Transactional
    public void deleteUserProfileById(Long id) {
        Optional<UserProfile> optionalUserProfile = userProfileRepository.findById(id);

        if (optionalUserProfile.isEmpty()) {
            throw new RuntimeException("User profile with id: " + id + " does not exist");
        }

        UserProfile userProfile = optionalUserProfile.get();

        User user = userProfile.getUser();
        if (user != null) {
            user.setUserProfile(null);
        }

        userProfileRepository.deleteById(id);
    }
}
