package com.fecakarate.backendfecakarate.Initializer;

import com.fecakarate.backendfecakarate.Models.Role;
import com.fecakarate.backendfecakarate.Models.Users;
import com.fecakarate.backendfecakarate.Repository.RoleRepo;
import com.fecakarate.backendfecakarate.Repository.UserRepo;
import com.fecakarate.backendfecakarate.Services.interfaces.IUserservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
@Slf4j
public class Initializer {

    private final UserRepo userRepo;

    private final RoleRepo roleRepo;

    public Initializer(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Bean
    CommandLineRunner runner(IUserservice userservice){
        return args -> {

            //Save Role
            if (roleRepo.findByName("Role_ADMIN").isEmpty()){
                userservice.saveRole(new Role( null,"Role_ADMIN", "This is Admin"));
            }

            if (roleRepo.findByName("Role_ORG").isEmpty()){
                userservice.saveRole(new Role(null,"Role_ORG", "This is Organization"));
            }

            if (roleRepo.findByName("Role_MANAGER").isEmpty()){
                userservice.saveRole(new Role(null,"Role_MANAGER", "This is Admin"));
            }

           //Save User
            if (userRepo.findByEmail("vanickperex@gmail.com").isEmpty()){
                Users users = new Users("237654243027","Admin","vanickperex@gmail.com","pass",new HashSet<>());
                userservice.saveUser(users);
            }

            if (userRepo.findByEmail("vanickperex2@gmail.com").isEmpty()){
                Users users = new Users("237690526831","dedjoun","vanickperex2@gmail.com","pass",new HashSet<>());
                userservice.saveUser(users);
            }

            if (userRepo.findByEmail("vanickperex3@gmail.com").isEmpty()){
                Users users = new Users("237677772414","orgDedjoun","vanickperex3@gmail.com","pass",new HashSet<>());
                userservice.saveUser(users);
            }

           //Assign Role to User
           /* userservice.addToUser("vanickperex@gmail.com", "Role_ADMIN");
            userservice.addToUser("vanickperex2@gmail.com", "Role_ORG");
            userservice.addToUser("vanickperex3@gmail.com", "Role_MANAGER");*/

        };
    }
}
