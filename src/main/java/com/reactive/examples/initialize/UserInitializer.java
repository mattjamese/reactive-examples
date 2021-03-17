package com.reactive.examples.initialize;

import com.reactive.examples.model.Department;
import com.reactive.examples.model.User;
import com.reactive.examples.repository.DepartmentRepository;
import com.reactive.examples.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@Profile("!test")
@Slf4j
@RequiredArgsConstructor
public class UserInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public void run(String... args) {
            initialDataSetup();
    }

    private List<User> getData(){
        return Arrays.asList(new User(null,"Suman Das",30, new BigDecimal("10000.00"), "suman.das@abc.com", LocalDate.now()),
                             new User(null,"Arjun Das",55,new BigDecimal("20000.00"), "Arjun.Das@abc.com", LocalDate.of(2029, 10, 29)),
                             new User(null,"Saurabh Ganguly",40,new BigDecimal("25000.00"), "Saurabh.Ganguly@abc.com", LocalDate.of(2029, 10, 29)));
    }

    private List<Department> getDepartments(){
        return Arrays.asList(new Department(null,"Mechanical",1,"Mumbai"),
                new Department(null,"Computer",2,"Bangalore"));
    }

    private void initialDataSetup() {
        userRepository.deleteAll()
                .thenMany(Flux.fromIterable(getData()))
                .flatMap(userRepository::save)
                .thenMany(userRepository.findAll())
                .subscribe(user -> {
                    log.info("User Inserted from CommandLineRunner " + user);
                });

        departmentRepository.deleteAll()
                .thenMany(Flux.fromIterable(getDepartments()))
                .flatMap(departmentRepository::save)
                .thenMany(departmentRepository.findAll())
                .subscribe(user -> {
                    log.info("Department Inserted from CommandLineRunner " + user);
                });

    }

}
