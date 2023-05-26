package com.dreamcode.companyservice.service;

import com.dreamcode.companyservice.client.UserClient;
import com.dreamcode.companyservice.domain.dto.CompanyDto;
import com.dreamcode.companyservice.domain.model.Company;
import com.dreamcode.companyservice.domain.model.User;
import com.dreamcode.companyservice.repository.CompanyRepository;
import com.dreamcode.companyservice.service.mapper.RepositoryDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    UserClient userClient;


    private RepositoryDtoMapper modelMapper;

    @PostConstruct
    void init() {
        companyRepository.deleteAll();
        companyRepository.save(Company.builder().name("BiGeek").build());
        companyRepository.save(Company.builder().name("Mirai").build());
        companyRepository.save(Company.builder().name("Tadaima").build());
    }

    public List<CompanyDto> getAll() {
        List<Company> allCompanies = companyRepository.findAll();
        return allCompanies
                .parallelStream()
                .map(company -> {
                    CompanyDto companyDto = CompanyDto.builder().id(company.getId()).name(company.getName()).build();
                    List<User> usersByCompany = userClient.getByCompany(company.getName());
                    companyDto.setUsers(usersByCompany);
                    return companyDto;
                })
                .collect(Collectors.toList());
    }

    //crete new company returning a CompanyDto object using modelMapper
    public CompanyDto create(CompanyDto companyDto) {
        if (companyDto == null) {
            throw new IllegalArgumentException("CompanyDto is null");
        }
        modelMapper = new RepositoryDtoMapper();
        Company company = modelMapper.map(companyDto, Company.class);
        company = companyRepository.save(company);
        return modelMapper.map(company, CompanyDto.class);
    }


    //update company with CompanyDto object
    public CompanyDto update(CompanyDto companyDto) {
        if (companyDto == null) {
            throw new IllegalArgumentException("CompanyDto is null");
        }
        modelMapper = new RepositoryDtoMapper();
        Company company = modelMapper.map(companyDto, Company.class);
        company = companyRepository.save(company);
        return modelMapper.map(company, CompanyDto.class);
    }


    //delete company
    public void delete(String id) {
        companyRepository.deleteById(id);
    }

    //get company by id with CompanyDto object
    public CompanyDto getById(String id) {
        Company company = companyRepository.findById(id).orElse(null);
        if (company == null) {
            throw new IllegalArgumentException("Company not found");
        }
        modelMapper = new RepositoryDtoMapper();
        return modelMapper.map(company, CompanyDto.class);
    }


}
