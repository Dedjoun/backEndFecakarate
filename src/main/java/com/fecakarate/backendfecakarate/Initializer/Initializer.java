package com.fecakarate.backendfecakarate.Initializer;

import com.fecakarate.backendfecakarate.Enums.Role;
import com.fecakarate.backendfecakarate.Models.Users;
import com.fecakarate.backendfecakarate.Repository.UserRepo;
import com.fecakarate.backendfecakarate.Services.interfaces.IUserservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Initializer {

    private final UserRepo userRepo;


    public Initializer(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Bean
    CommandLineRunner runner(IUserservice userservice){
        return args -> {


           //Save User
            if (userRepo.findByEmail("vanickperex@gmail.com").isEmpty()){
                Users users = new Users();
                users.setFirstname("Dedjoun");
                users.setLastname("Vanick");
                users.setEmail("vanickperex@gmail.com");
                users.setPassword("1234567890");
                users.setRole(Role.Admin);
                users =  userservice.saveUser(users);
                log.info("CREATE USER  UserName:{}  Name:{}  ", users.getEmail(), users.getFirstname());
            }

            if (userRepo.findByEmail("vanickperex2@gmail.com").isEmpty()){
                Users users = new Users();
                users.setFirstname("Dedjoun");
                users.setLastname("Perex");
                users.setEmail("vanickperex2@gmail.com");
                users.setPassword("1234567890");
                users.setRole(Role.User);
                users =  userservice.saveUser(users);
                log.info("CREATE USER  UserName:{}  Name:{}  ", users.getEmail(), users.getFirstname());
            }


        };
    }
}
