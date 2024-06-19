package com.example.myblogsite.repository;

import com.example.myblogsite.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
