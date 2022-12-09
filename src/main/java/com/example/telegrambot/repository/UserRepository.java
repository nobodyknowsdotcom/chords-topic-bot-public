package com.example.telegrambot.repository;

import com.example.telegrambot.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
