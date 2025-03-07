package com.ms.user.controllers;

import com.ms.user.dtos.UserRecordDTO;
import com.ms.user.dtos.UserResponseDTO;
import com.ms.user.models.UserModel;
import com.ms.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {

    final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable UUID id) {
        var result = userService.getUser(id);

        if(result.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        var userResponseDTO = new UserResponseDTO(
                result.get().getId(),
                result.get().getName(),
                result.get().getEmail()
        );

        return ResponseEntity.status(HttpStatus.OK).body(userResponseDTO);
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponseDTO> saveUser(@RequestBody @Valid UserRecordDTO userRecordDTO) {

        var userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDTO, userModel);

        var result  = userService.saveUser(userModel);

        var userResponseDTO = new UserResponseDTO(
                result != null ? result.getId() : null,
                result != null ? result.getName() : null,
                result != null ? result.getEmail() : null
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
    }
}
