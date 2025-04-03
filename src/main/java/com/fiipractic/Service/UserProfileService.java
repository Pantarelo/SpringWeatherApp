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
        Optional<User> userOptional = userRepository.findById(userProfile.getUser().getId());

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User with id: " + userProfile.getUser().getId() + " does not exist");
        }
        User user = userOptional.get();

        userProfile.setUser(user);

        userRepository.save(user);
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

    @Transactional
    public UserProfile updateUserProfile1(Long id, UserProfile userProfile) {
        //63, 12, 9, 3, 5, 4, 2
        long startTime = System.currentTimeMillis();

        UserProfile updatedUserProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User profile with id: " + id + " not found"));

        if (userProfile.getEmail() != null) {
            updatedUserProfile.setEmail(userProfile.getEmail());
        }
        if (userProfile.getEmailNotification() != null) {
            updatedUserProfile.setEmailNotification(userProfile.getEmailNotification());
        }
        if (userProfile.getWeatherApiKey() != null) {
            updatedUserProfile.setWeatherApiKey(userProfile.getWeatherApiKey());
        }

        UserProfile savedUserProfile = userProfileRepository.save(updatedUserProfile);

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println(duration);

        return savedUserProfile;
    }

    @Transactional
    public UserProfile updateUserProfile2(Long id, UserProfile userProfile) {
        //68, 17, 6, 6, 6, 4
        long startTime = System.currentTimeMillis();

        userProfileRepository.updateUserProfile(
                id,
                userProfile.getEmail(),
                userProfile.getEmailNotification(),
                userProfile.getWeatherApiKey()
        );

        UserProfile savedUserProfile = userProfileRepository.findById(id).orElseThrow(() -> new RuntimeException("User profile with id: " + id + " not found"));

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println(duration);

        return savedUserProfile;
    }
}
