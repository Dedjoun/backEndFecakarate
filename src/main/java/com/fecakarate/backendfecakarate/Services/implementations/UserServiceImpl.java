package com.fecakarate.backendfecakarate.Services.implementations;

import com.fecakarate.backendfecakarate.Models.Role;
import com.fecakarate.backendfecakarate.Models.User;
import com.fecakarate.backendfecakarate.Repository.RoleRepo;
import com.fecakarate.backendfecakarate.Repository.UserRepo;
import com.fecakarate.backendfecakarate.Services.interfaces.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    Instant date;

    @Override
    public User saveUser(User user) {
        date = Instant.now();
        log.info("Saving New User {} to database at :{} ",user,date);
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        date = Instant.now();
        log.info("Saving New Role [{}] to database at :{} ",role.getName(),date);
        return roleRepo.save(role);
    }


    @Override
    public User getUser(String email) {
        log.info("Get User bey {}",email);
        return userRepo.findByEmail(email);
    }

    @Override
    public List<User> getUsers() {
        log.info("Get All User");
        return userRepo.findAll();
    }
}
