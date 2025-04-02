package com.fiipractic.repository;

import com.fiipractic.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    @Query("SELECT u FROM UserProfile u WHERE u.id = :id")
    Optional<UserProfile> findById(@Param("id") Long id);

    @Modifying //required for DELETE and UPDATE queries in Spring Data JPA
    @Transactional
    @Query("DELETE FROM UserProfile u WHERE u.id = :id")
    void deleteById(@Param("id") Long id);
}
