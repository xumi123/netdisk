package com.cuit.netdisk4.repository;

import com.cuit.netdisk4.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByIsVip(boolean b);
}