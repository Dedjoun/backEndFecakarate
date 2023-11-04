package com.fecakarate.backendfecakarate.Services.implementations;

import com.fecakarate.backendfecakarate.Models.Role;
import com.fecakarate.backendfecakarate.Models.Users;
import com.fecakarate.backendfecakarate.Repository.RoleRepo;
import com.fecakarate.backendfecakarate.Repository.UserRepo;
import com.fecakarate.backendfecakarate.Services.interfaces.IUserservice;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@Transactional
@Slf4j
public class IUSERServiceImpl implements IUserservice {


    private final UserRepo userRepo;

    private final RoleRepo roleRepo;

    private final PasswordEncoder passwordEncoder;

    public IUSERServiceImpl(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Users saveUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>());
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public void addToUser(String username, String roleName) {
        if (userRepo.findByEmail(username).isEmpty()){
            log.warn("User will email[" +username+"] does not exits");
            throw new IllegalArgumentException("User will email[" +username+"] does not exits");
        }

        Role role;
        role = roleRepo.findByName(roleName).get();
        if (role == null){
            log.warn("Role with name["+roleName+"] does not exits");
            throw new IllegalArgumentException("Role with name["+roleName+"] does not exits");
        }

        Users users = userRepo.findByEmail(username).get();
        users.getRoles().add(role);
    }
}
