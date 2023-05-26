package com.dreamcode.userservice.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.dreamcode.userservice.domain.model.User;
import com.dreamcode.userservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link UserController#getAll()}
     */
    @Test
    public void testGetAll() throws Exception {
        when(this.userService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link UserController#getAll()}
     */
    @Test
    public void testGetAll2() throws Exception {
        User user = new User();
        user.setCompany("Company");
        user.setId("42");
        user.setName("Name");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        when(this.userService.getAll()).thenReturn(userList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(
                        MockMvcResultMatchers.content().string("[{\"id\":\"42\",\"name\":\"Name\",\"company\":\"Company\"}]"));
    }

    /**
     * Method under test: {@link UserController#getAll()}
     */
    @Test
    public void testGetAll3() throws Exception {
        when(this.userService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link UserController#getByCompany(String)}
     */
    @Test
    public void testGetByCompany() throws Exception {
        when(this.userService.getByCompany((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/{company}", "Company");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link UserController#getByCompany(String)}
     */
    @Test
    public void testGetByCompany2() throws Exception {
        User user = new User();
        user.setCompany("?");
        user.setId("42");
        user.setName("?");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        when(this.userService.getByCompany((String) any())).thenReturn(userList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/{company}", "Company");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("[{\"id\":\"42\",\"name\":\"?\",\"company\":\"?\"}]"));
    }

    /**
     * Method under test: {@link UserController#getByCompany(String)}
     */
    @Test
    public void testGetByCompany3() throws Exception {
        when(this.userService.getAll()).thenReturn(new ArrayList<>());
        when(this.userService.getByCompany((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/{company}", "", "Uri Vars");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link UserController#create(User)}
     */
    @Test
    public void testCreate() throws Exception {
        User user = new User();
        user.setCompany("Company");
        user.setId("42");
        user.setName("Name");
        when(this.userService.create((User) any())).thenReturn(user);

        User user1 = new User();
        user1.setCompany("Company");
        user1.setId("42");
        user1.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(user1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":\"42\",\"name\":\"Name\",\"company\":\"Company\"}"));
    }

    /**
     * Method under test: {@link UserController#update(User)}
     */
    @Test
    public void testUpdate() throws Exception {
        User user = new User();
        user.setCompany("Company");
        user.setId("42");
        user.setName("Name");
        when(this.userService.update((User) any())).thenReturn(user);

        User user1 = new User();
        user1.setCompany("Company");
        user1.setId("42");
        user1.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(user1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":\"42\",\"name\":\"Name\",\"company\":\"Company\"}"));
    }

}

