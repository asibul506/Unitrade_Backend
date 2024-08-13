package com.uniTrade.uniTrade.repository;

import com.uniTrade.uniTrade.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByMatriculation(int matriculation);
}
