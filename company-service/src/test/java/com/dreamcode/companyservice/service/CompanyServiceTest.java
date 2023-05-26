package com.dreamcode.companyservice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.dreamcode.companyservice.client.UserClient;
import com.dreamcode.companyservice.domain.dto.CompanyDto;
import com.dreamcode.companyservice.domain.model.Company;
import com.dreamcode.companyservice.repository.CompanyRepository;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {CompanyService.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class CompanyServiceTest {
    @MockBean
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyService companyService;

    @MockBean
    private UserClient userClient;

    /**
     * Method under test: {@link CompanyService#getAll()}
     */
    @Test
    public void testGetAll() {
        when(this.companyRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(this.companyService.getAll().isEmpty());
        verify(this.companyRepository).findAll();
    }

    /**
     * Method under test: {@link CompanyService#getAll()}
     */
    @Test
    public void testGetAll2() {
        when(this.userClient.getByCompany((String) any())).thenReturn(new ArrayList<>());

        Company company = new Company();
        company.setId("42");
        company.setName("Name");

        ArrayList<Company> companyList = new ArrayList<>();
        companyList.add(company);
        when(this.companyRepository.findAll()).thenReturn(companyList);
        List<CompanyDto> actualAll = this.companyService.getAll();
        assertEquals(1, actualAll.size());
        CompanyDto getResult = actualAll.get(0);
        assertEquals("42", getResult.getId());
        assertTrue(getResult.getUsers().isEmpty());
        assertEquals("Name", getResult.getName());
        verify(this.userClient).getByCompany((String) any());
        verify(this.companyRepository).findAll();
    }

    /**
     * Method under test: {@link CompanyService#getAll()}
     */
    @Test
    public void testGetAll3() {
        when(this.userClient.getByCompany((String) any())).thenReturn(new ArrayList<>());

        Company company = new Company();
        company.setId("42");
        company.setName("Name");

        Company company1 = new Company();
        company1.setId("42");
        company1.setName("Name");

        ArrayList<Company> companyList = new ArrayList<>();
        companyList.add(company1);
        companyList.add(company);
        when(this.companyRepository.findAll()).thenReturn(companyList);
        List<CompanyDto> actualAll = this.companyService.getAll();
        assertEquals(2, actualAll.size());
        CompanyDto getResult = actualAll.get(0);
        assertTrue(getResult.getUsers().isEmpty());
        CompanyDto getResult1 = actualAll.get(1);
        assertEquals("Name", getResult1.getName());
        assertEquals("42", getResult1.getId());
        assertEquals("Name", getResult.getName());
        assertEquals("42", getResult.getId());
        verify(this.userClient, atLeast(1)).getByCompany((String) any());
        verify(this.companyRepository).findAll();
    }

    /**
     * Method under test: {@link CompanyService#getAll()}
     */
    @Test
    public void testGetAll4() {
        when(this.userClient.getByCompany((String) any())).thenReturn(new ArrayList<>());
        Company company = mock(Company.class);
        when(company.getId()).thenReturn("42");
        when(company.getName()).thenReturn("Name");
        doNothing().when(company).setId((String) any());
        doNothing().when(company).setName((String) any());
        company.setId("42");
        company.setName("Name");

        ArrayList<Company> companyList = new ArrayList<>();
        companyList.add(company);
        when(this.companyRepository.findAll()).thenReturn(companyList);
        List<CompanyDto> actualAll = this.companyService.getAll();
        assertEquals(1, actualAll.size());
        CompanyDto getResult = actualAll.get(0);
        assertEquals("42", getResult.getId());
        assertTrue(getResult.getUsers().isEmpty());
        assertEquals("Name", getResult.getName());
        verify(this.userClient).getByCompany((String) any());
        verify(this.companyRepository).findAll();
        verify(company).getId();
        verify(company, atLeast(1)).getName();
        verify(company).setId((String) any());
        verify(company).setName((String) any());
    }

    /**
     * Method under test: {@link CompanyService#create(CompanyDto)}}
     *
     * @return CompanyDto
     **/
    @Test
    public void testCreate() {
        Company company = new Company();
        company.setId("42");
        company.setName("Name");
        when(this.companyRepository.save((Company) any())).thenReturn(company);
        CompanyDto actual = this.companyService.create(new CompanyDto());
        assertEquals("42", actual.getId());
        assertEquals("Name", actual.getName());
        verify(this.companyRepository).save((Company) any());
    }


    /**
     * Method under test: {@link CompanyService#getById(String)}
     */
    @Test
    public void testGetById() {
        Company company = new Company();
        company.setId("42");
        company.setName("Name");
        Optional<Company> ofResult = Optional.of(company);
        when(this.companyRepository.findById((String) any())).thenReturn(ofResult);
        CompanyDto actualById = this.companyService.getById("42");
        assertEquals("42", actualById.getId());
        assertEquals("Name", actualById.getName());
        verify(this.companyRepository).findById((String) any());
    }

    /**
     * Method under test: {@link CompanyService#getById(String)}
     */
    @Test
    public void testGetById2() {
        Company company = new Company("42", ":{UUU}");
        company.setId("42");
        company.setName("Name");
        Optional<Company> ofResult = Optional.of(company);
        when(this.companyRepository.findById((String) any())).thenReturn(ofResult);
        CompanyDto actualById = this.companyService.getById("42");
        assertEquals("42", actualById.getId());
        assertEquals("Name", actualById.getName());
        verify(this.companyRepository).findById((String) any());
    }

    /**
     * Method under test: {@link CompanyService#getById(String)}
     */
    @Test
    public void testGetById3() {
        Company company = mock(Company.class);
        when(company.getId()).thenReturn("42");
        when(company.getName()).thenReturn("Name");
        doNothing().when(company).setId((String) any());
        doNothing().when(company).setName((String) any());
        company.setId("42");
        company.setName("Name");
        Optional<Company> ofResult = Optional.of(company);
        when(this.companyRepository.findById((String) any())).thenReturn(ofResult);
        CompanyDto actualById = this.companyService.getById("42");
        assertEquals("42", actualById.getId());
        assertEquals("Name", actualById.getName());
        verify(this.companyRepository).findById((String) any());
        verify(company).getId();
        verify(company).getName();
        verify(company).setId((String) any());
        verify(company).setName((String) any());
    }

    /**
     * Method under test: {@link CompanyService#getById(String)}
     */
    @Test
    public void testGetById4() {
        Company company = mock(Company.class);
        when(company.getId()).thenReturn(":{UUU}");
        when(company.getName()).thenReturn("Name");
        doNothing().when(company).setId((String) any());
        doNothing().when(company).setName((String) any());
        company.setId("42");
        company.setName("Name");
        Optional<Company> ofResult = Optional.of(company);
        when(this.companyRepository.findById((String) any())).thenReturn(ofResult);
        CompanyDto actualById = this.companyService.getById("42");
        assertEquals(":{UUU}", actualById.getId());
        assertEquals("Name", actualById.getName());
        verify(this.companyRepository).findById((String) any());
        verify(company).getId();
        verify(company).getName();
        verify(company).setId((String) any());
        verify(company).setName((String) any());
    }


}

