package com.example.restapi.DataBase;


import com.example.restapi.Domain.Customer;
import com.example.restapi.Repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CustomerRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Customer("Johnny","Bravo","johnny@yahoo.com","male",21)));
            log.info("Preloading " + repository.save(new Customer("Anon","Anon","anon@gmail.com","male",44)));
        };
    }
}
