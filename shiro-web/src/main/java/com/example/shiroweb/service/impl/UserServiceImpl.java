package com.example.shiroweb.service.impl;

import com.example.shiroweb.entity.Users;
import com.example.shiroweb.mapper.UsersMapper;
import com.example.shiroweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UsersMapper usersMapper;

    @Override
    public Users getPasswordByUsername(String username) {
        Users user = usersMapper.getPasswordByUsername(username);
        if(user != null){
            return user;
        }
        return null;
    }
}
