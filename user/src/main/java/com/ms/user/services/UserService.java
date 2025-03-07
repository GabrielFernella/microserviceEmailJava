package com.ms.user.services;

import com.ms.user.models.UserModel;
import com.ms.user.producers.UserProducer;
import com.ms.user.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    final UserRepository userRepository;
    final UserProducer userProducer;

    public UserService(UserRepository userRepository, UserProducer userProducer){
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    public Optional<UserModel> getUser(UUID userId) {
        return userRepository.findById(userId);
    }

    @Transactional
    public UserModel saveUser(UserModel userModel) {

        var verifyUserAlreadyExists = userRepository.findByEmail(userModel.getEmail());
        if(verifyUserAlreadyExists != null){
            throw new Error("User already exists"); // TODO: Fazer uma exceção personalizada
        }

        var user = userRepository.save(userModel);
        userProducer.publishMessageEmail(user);
        return user;
    }
}
