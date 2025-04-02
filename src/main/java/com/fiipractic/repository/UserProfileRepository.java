package com.fiipractic.repository;

import com.fiipractic.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    @Query("SELECT u FROM UserProfile u WHERE u.id = :id")
    Optional<UserProfile> findById(@Param("id") Long id);
}
