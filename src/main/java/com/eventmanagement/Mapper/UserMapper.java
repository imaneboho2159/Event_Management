package com.eventmanagement.Mapper;


import com.eventmanagement.DTO.UserDto;
import com.eventmanagement.Model.User;
import com.eventmanagement.Security.Athentication.RegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto UserToUserDto(User user);
    User UserDtoToUser(UserDto userDto);

}
