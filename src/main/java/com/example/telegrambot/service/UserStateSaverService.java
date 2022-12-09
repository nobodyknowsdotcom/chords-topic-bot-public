package com.example.telegrambot.service;

import com.example.telegrambot.model.SongsPage;
import com.example.telegrambot.model.User;
import com.example.telegrambot.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserStateSaverService {
    private final UserRepository userRepository;

    public UserStateSaverService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUserState(long chatId, SongsPage songsPage){
        User user = new User(chatId, songsPage);
        userRepository.save(user);
    }
}
