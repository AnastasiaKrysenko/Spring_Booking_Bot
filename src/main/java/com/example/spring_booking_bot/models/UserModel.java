package com.example.spring_booking_bot.models;

import jakarta.persistence.*;

@Entity
@Table(name = "telegram_user")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name = "username")
    String username;
    @Column(name = "telegram_id")
    String tgId;
    @Column(name = "name")
    String name;
}
