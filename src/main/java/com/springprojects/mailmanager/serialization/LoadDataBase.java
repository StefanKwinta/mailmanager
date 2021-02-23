package com.springprojects.mailmanager.serialization;

import com.springprojects.mailmanager.data.AccountsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(AccountsRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new MailAccount("login","haslo")));
            log.info("Preloading " + repository.save(new MailAccount("login2","haslo2")));
        };
    }
}