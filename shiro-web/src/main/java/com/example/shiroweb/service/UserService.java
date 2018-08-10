package com.example.shiroweb.service;

import com.example.shiroweb.entity.Users;

public interface UserService {
    public Users getPasswordByUsername(String username);
}
