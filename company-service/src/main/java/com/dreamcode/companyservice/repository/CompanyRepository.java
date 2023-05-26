package com.dreamcode.companyservice.repository;

import com.dreamcode.companyservice.domain.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<Company, String> {

}
