package com.fecakarate.backendfecakarate.Services.interfaces;

import com.fecakarate.backendfecakarate.Models.Role;
import com.fecakarate.backendfecakarate.Models.Users;
import org.springframework.http.ResponseEntity;

public interface IUserservice {


    Users saveUser(Users user);

    Role saveRole(Role role);

    void addToUser(String username, String roleName);

    ResponseEntity<?> register();
}
