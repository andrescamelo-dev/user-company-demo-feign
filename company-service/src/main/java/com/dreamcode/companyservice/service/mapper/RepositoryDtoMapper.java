package com.dreamcode.companyservice.service.mapper;

import com.dreamcode.companyservice.domain.dto.CompanyDto;
import com.dreamcode.companyservice.domain.model.Company;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;


public class RepositoryDtoMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(Company.class, CompanyDto.class)
                .byDefault().exclude("users")
                .register();
        factory.classMap(CompanyDto.class, Company.class)
                .byDefault().exclude("users")
                .register();
    }
}
