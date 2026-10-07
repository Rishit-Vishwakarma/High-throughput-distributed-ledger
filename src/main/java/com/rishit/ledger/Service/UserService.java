package com.rishit.ledger.Service;

import com.rishit.ledger.DTO.Request.CreateUserRequest;
import com.rishit.ledger.DTO.Response.UserResponse;
import com.rishit.ledger.Entity.User;
import com.rishit.ledger.Enum.UserStatus;
import com.rishit.ledger.Exception.UserNotFoundException;
import com.rishit.ledger.Mapper.UserMapper;
import com.rishit.ledger.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper){
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

//// Method to retrive user info.
    public UserResponse getUserByUserId(Long userId){
        User user = userRepository.findUserByUserId(userId).orElseThrow(() -> new UserNotFoundException("No user found with this ID."));
        return userMapper.toUserResponse(user);
    }

/// Method to create user.
    public UserResponse createUser(CreateUserRequest createUserRequest){
        String email = createUserRequest.getEmail();
        String username = createUserRequest.getUsername();

        if(userRepository.existsByEmail(email)){
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setStatus(UserStatus.ACTIVE);
        user.setCreatedAt(LocalDateTime.now());

        User user1 = userRepository.save(user);
        UserResponse userResponse = userMapper.toUserResponse(user1);
        return userResponse;
    }

}
