package com.auth0.samples.authapi.springbootauthupdated.user;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApplicationUserRepository extends MongoRepository<ApplicationUser,Long> {
    ApplicationUser findByUsername(String username);
}
