package com.dreamcode.companyservice.client;

import com.dreamcode.companyservice.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;

@FeignClient(name = "userservice", url = "${customer.url}")
public interface UserClient {
    @GetMapping("/")
    List<User> getAll();

    @GetMapping("/{company}")
    List<User> getByCompany(@PathVariable("company") String company);

    @Component
    @Slf4j
    class HystrixClientFallback implements UserClient {

        @Override
        public List<User> getAll() {
            log.error("Error getAll");
            throw new RuntimeException();
        }

        @Override
        public List<User> getByCompany(String company) {
            log.error("Error getByCompany with company {}", company);
            return Collections.emptyList();
        }
    }
}
