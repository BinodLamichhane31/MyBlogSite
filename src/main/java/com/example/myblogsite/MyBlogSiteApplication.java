package com.example.myblogsite;

import com.example.myblogsite.config.AppConstants;
import com.example.myblogsite.entity.Role;
import com.example.myblogsite.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class MyBlogSiteApplication implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(MyBlogSiteApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


    @Override
    public void run(String... args) throws Exception {
        try {
            Role adminRole = new Role();
            adminRole.setId(AppConstants.ADMIN_USER);
            adminRole.setName("ADMIN_USER");

            Role userRole = new Role();
            userRole.setId(AppConstants.NORMAL_USER);
            userRole.setName("NORMAL_USER");

            List<Role> roles = List.of(adminRole, userRole);
            this.roleRepository.saveAll(roles);
            roles.forEach(role -> {
                System.out.println(role.getName());
            });


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
