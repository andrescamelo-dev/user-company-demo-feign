package com.dreamcode.companyservice.domain.dto;

import com.dreamcode.companyservice.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    String id;
    String name;
    List<User> users;
}
