package com.fecakarate.backendfecakarate.Services.implementations;

import com.fecakarate.backendfecakarate.Models.Role;
import com.fecakarate.backendfecakarate.Models.Users;
import com.fecakarate.backendfecakarate.Repository.RoleRepo;
import com.fecakarate.backendfecakarate.Repository.UserRepo;
import com.fecakarate.backendfecakarate.Services.interfaces.IUserservice;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class IUSERServiceImpl implements IUserservice {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Users saveUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoleSet(new HashSet<>());
        return userRepo.save(user);
    }
    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }
    @Override
    public void addToUser(String username, String roleName) {

        Optional<Users> users = userRepo.findByEmail(username);

        if (users.isEmpty()){
            log.warn("User will email[" +username+"] does not exits");
            throw new IllegalArgumentException("User will email[" +username+"] does not exits");
        }

        Users users1 = users.get();

        Optional<Role> role = roleRepo.findByName(roleName);
        if (role.isEmpty()){
            log.warn("Role will name[" +roleName+"] does not exits");
            throw new IllegalArgumentException("Role will name[" +roleName+"] does not exits");
        }
        Role role1 = role.get();

        users1.getRoleSet().add(role1);
    }

}
