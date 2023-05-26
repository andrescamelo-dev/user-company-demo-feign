package com.dreamcode.companyservice.controller;

import com.dreamcode.companyservice.domain.dto.CompanyDto;
import com.dreamcode.companyservice.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CompanyController {

    @Autowired
    CompanyService companyService;


    @GetMapping("/")
    List<CompanyDto> getAll() {
        return companyService.getAll();
    }

    @PostMapping("/create")
    CompanyDto create(@RequestBody CompanyDto companyDto) {
        return companyService.create(companyDto);
    }

    @PostMapping("/update")
    CompanyDto update(@RequestBody CompanyDto companyDto) {
        return companyService.update(companyDto);
    }

     //delete company endpoint
    @PostMapping("/delete")
    void delete(@RequestBody String id) {
        companyService.delete(id);
    }

    //get company by id endpoint
    @PostMapping("/getById")
    CompanyDto getById(@RequestBody String id) {
        return companyService.getById(id);
    }

}
