package com.eventmanagement.Service;

import com.eventmanagement.DTO.UserDto;
import com.eventmanagement.Mapper.UserMapper;
import com.eventmanagement.Model.User;

import com.eventmanagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class UserService {


     private final  UserRepository userRepository;
      private final UserMapper userMapper;
    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
      this.userRepository = userRepository;
      this.userMapper = userMapper;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::UserToUserDto).toList();
    }
    public UserDto getUserById(long id){
        User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("User Not Found"));
        return userMapper.UserToUserDto(user);
    }
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public UserDto updateUser(Long id,UserDto userDto){
        User existingUser = userRepository.findById(id).orElseThrow(()->new RuntimeException("User Not Found"));
        existingUser.setEmail(userDto.email());
        existingUser.setRole(userDto.role());
        User updatedUser = userRepository.save(existingUser);
        return userMapper.UserToUserDto(updatedUser);
    }

}
