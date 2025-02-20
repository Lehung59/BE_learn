package com.example.rabiloo.repository;

import com.example.rabiloo.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {



}
