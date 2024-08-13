package com.uniTrade.uniTrade.repository;

import com.uniTrade.uniTrade.model.Lead;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface LeadRepository extends MongoRepository<Lead, String> {
    List<Lead> findByUserMatriculation(int userMatriculation);

    Optional<Lead> findBylId(int lId);

    List<Lead> findAllByOrderByCreatedAtDesc();

    void deleteBylId(int lId);

    boolean existsBylId(int id);
}