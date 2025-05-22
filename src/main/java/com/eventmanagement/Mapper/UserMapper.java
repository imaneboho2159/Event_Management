package com.eventmanagement.Mapper;

import com.eventmanagement.Model.User;
import com.eventmanagement.Security.Athentification.RegisterRequest;

public interface UserMapper {
//    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(RegisterRequest request);
}
