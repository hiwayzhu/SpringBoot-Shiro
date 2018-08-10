package com.example.shiroweb.service;

import java.util.Set;

public interface UserRolesService {

    public Set<String> getRolesByUsername(String username);
}
