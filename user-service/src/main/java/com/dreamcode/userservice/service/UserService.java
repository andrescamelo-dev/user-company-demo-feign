package com.dreamcode.userservice.service;

import com.dreamcode.userservice.domain.model.User;
import com.dreamcode.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @PostConstruct
    void init() {
        userRepository.deleteAll();
        userRepository.save(User.builder().name("Rafa").company("BiGeek").build());
        userRepository.save(User.builder().name("Javi").company("BiGeek").build());
        userRepository.save(User.builder().name("Victor").company("Mirai").build());
        userRepository.save(User.builder().name("Alvaro").company("Tadaima").build());
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public List<User> getByCompany(@PathVariable("company") String company) {
        return userRepository.findByCompany(company);
    }

    public User create(User user) {
        userRepository.save(user);
        return user;
    }

    public User update(User user) {
        userRepository.save(user);
        return user;
    }
    public void delete(User user) {
        userRepository.delete(user);
    }

}
