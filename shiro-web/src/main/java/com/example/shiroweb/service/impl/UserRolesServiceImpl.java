package com.example.shiroweb.service.impl;

import com.example.shiroweb.mapper.UserRolesMapper;
import com.example.shiroweb.service.UserRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service

public class UserRolesServiceImpl implements UserRolesService {

    @Autowired
    UserRolesMapper userRolesMapper;

    public Set<String> getRolesByUsername(String username){
        List<String> roles = userRolesMapper.getRolesByUsername(username);
        Set<String> sets = new HashSet<>(roles);
        return sets;
    }
}
