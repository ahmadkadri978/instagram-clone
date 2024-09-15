package com.instagram.clone.mapper;

import com.instagram.clone.dto.UserDto;
import com.instagram.clone.entity.User;

public class UserMapper {

    public UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        // قم بإضافة أي خصائص أخرى تحتاج إلى تحويلها
        return dto;
    }
}