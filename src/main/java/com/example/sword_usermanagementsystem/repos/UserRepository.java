package com.example.sword_usermanagementsystem.repos;

import com.example.sword_usermanagementsystem.models.UserM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserM, Long> {

    @Query("SELECT u FROM UserM u WHERE u.email = ?1")
    Optional<UserM> findByEmail(String email);

    @Query("SELECT u FROM UserM u WHERE u.job_title = ?1")
    List<UserM> findByJob_title(String jobTitle);


    @Query("SELECT u FROM UserM u WHERE u.username = ?1")
    Optional<UserM> findByUserName(String userName);
}
