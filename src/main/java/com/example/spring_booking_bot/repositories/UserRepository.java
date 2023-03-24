package com.example.spring_booking_bot.repositories;

import com.example.spring_booking_bot.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
UserModel findUserModelByTgId(String id);
}
