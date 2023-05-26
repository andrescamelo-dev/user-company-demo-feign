package com.dreamcode.userservice.service;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.dreamcode.userservice.domain.model.User;
import com.dreamcode.userservice.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {UserService.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * Method under test: {@link UserService#init()}
     */
    @Test
    public void testInit() {
        User user = new User();
        user.setCompany("Company");
        user.setId("42");
        user.setName("Name");
        when(this.userRepository.save(any())).thenReturn(user);
        doNothing().when(this.userRepository).deleteAll();
        this.userService.init();
        verify(this.userRepository, atLeast(1)).save((User) any());
        verify(this.userRepository).deleteAll();
    }

    /**
     * Method under test: {@link UserService#getAll()}
     */
    @Test
    public void testGetAll() {
        ArrayList<User> userList = new ArrayList<>();
        when(this.userRepository.findAll()).thenReturn(userList);
        List<User> actualAll = this.userService.getAll();
        assertSame(userList, actualAll);
        assertTrue(actualAll.isEmpty());
        verify(this.userRepository).findAll();
    }

    /**
     * Method under test: {@link UserService#getByCompany(String)}
     */
    @Test
    public void testGetByCompany() {
        ArrayList<User> userList = new ArrayList<>();
        when(this.userRepository.findByCompany((String) any())).thenReturn(userList);
        List<User> actualByCompany = this.userService.getByCompany("Company");
        assertSame(userList, actualByCompany);
        assertTrue(actualByCompany.isEmpty());
        verify(this.userRepository).findByCompany((String) any());
    }

    /**
     * Method under test: {@link UserService#create(User)}
     */
    @Test
    public void testCreate() {
        User user = new User();
        user.setCompany("Company");
        user.setId("42");
        user.setName("Name");
        when(this.userRepository.save((User) any())).thenReturn(user);

        User user1 = new User();
        user1.setCompany("Company");
        user1.setId("42");
        user1.setName("Name");
        assertSame(user1, this.userService.create(user1));
        verify(this.userRepository).save((User) any());
    }


    /**
     * Method under test: {@link UserService#update(User)}
     */
    @Test
    public void testUpdate() {
        User user = new User();
        user.setCompany("Company");
        user.setId("42");
        user.setName("Name");
        when(this.userRepository.save((User) any())).thenReturn(user);

        User user1 = new User();
        user1.setCompany("Company");
        user1.setId("42");
        user1.setName("Name");
        assertSame(user1, this.userService.update(user1));
        verify(this.userRepository, atLeast(1)).save((User) any());
    }

    /**
     * Method under test: {@link UserService#delete(User)}
     */
    @Test
    public void testDelete() {
        User user1 = new User();
        user1.setCompany("Company");
        user1.setId("42");
        user1.setName("Name");
        this.userService.delete(user1);
        verify(this.userRepository, atLeast(1)).delete((User) any());
    }

}

