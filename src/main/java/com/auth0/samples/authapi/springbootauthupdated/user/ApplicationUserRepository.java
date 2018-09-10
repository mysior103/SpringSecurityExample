package com.auth0.samples.authapi.springbootauthupdated.user;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApplicationUserRepository extends MongoRepository<ApplicationUser,String> {
    ApplicationUser findByUsername(String username);
}
