package com.dreamcode.userservice.controller;

import com.dreamcode.userservice.domain.model.User;
import com.dreamcode.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{company}")
    public List<User> getByCompany(@PathVariable("company") String company) {
        return userService.getByCompany(company);
    }

    @PostMapping("/create")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    //update company endpoint
    @PostMapping("/update")
    public User update(@RequestBody User user) {
        return userService.update(user);
    }
    //delete company endpoint
    @DeleteMapping("/delete")
    public void delete(@RequestBody User user) {
        userService.delete(user);
    }
}
