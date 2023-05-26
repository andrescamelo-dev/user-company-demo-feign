package com.dreamcode.companyservice.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.dreamcode.companyservice.domain.dto.CompanyDto;
import com.dreamcode.companyservice.service.CompanyService;
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

@ContextConfiguration(classes = {CompanyController.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class CompanyControllerTest {
    @Autowired
    private CompanyController companyController;

    @MockBean
    private CompanyService companyService;

    /**
     * Method under test: {@link CompanyController#getAll()}
     */
    @Test
    public void testGetAll() throws Exception {
        when(this.companyService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        MockMvcBuilders.standaloneSetup(this.companyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CompanyController#getAll()}
     */
    @Test
    public void testGetAll2() throws Exception {
        when(this.companyService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.companyController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CompanyController#create(CompanyDto)}
     */
    @Test
    public void testCreate() throws Exception {
        when(this.companyService.create((CompanyDto) any())).thenReturn(new CompanyDto());

        CompanyDto companyDto = new CompanyDto();
        companyDto.setId("42");
        companyDto.setName("Name");
        companyDto.setUsers(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(companyDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.companyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":null,\"name\":null,\"users\":null}"));
    }

    /**
     * Method under test: {@link CompanyController#update(CompanyDto)}
     */
    @Test
    public void testUpdate() throws Exception {
        when(this.companyService.update((CompanyDto) any())).thenReturn(new CompanyDto());

        CompanyDto companyDto = new CompanyDto();
        companyDto.setId("42");
        companyDto.setName("Name");
        companyDto.setUsers(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(companyDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.companyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":null,\"name\":null,\"users\":null}"));
    }

    /**
     * Method under test: {@link CompanyController#delete(String)}
     */
    @Test
    public void testDelete() throws Exception {
        doNothing().when(this.companyService).delete((String) any());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/delete")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new String()));
        MockMvcBuilders.standaloneSetup(this.companyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link CompanyController#getById(String)}
     */
    @Test
    public void testGetById() throws Exception {
        when(this.companyService.getById((String) any())).thenReturn(new CompanyDto());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/getById")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new String()));
        MockMvcBuilders.standaloneSetup(this.companyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":null,\"name\":null,\"users\":null}"));
    }

    /**
     * Method under test: {@link CompanyController#getById(String)}
     */
    @Test
    public void testGetById2() throws Exception {
        when(this.companyService.getById((String) any())).thenReturn(new CompanyDto());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/getById")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new String()));
        MockMvcBuilders.standaloneSetup(this.companyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":null,\"name\":null,\"users\":null}"));
    }


}

