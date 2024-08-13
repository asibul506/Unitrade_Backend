package com.uniTrade.uniTrade.controller;

import com.uniTrade.uniTrade.model.Lead;
import com.uniTrade.uniTrade.model.User;
import com.uniTrade.uniTrade.repository.LeadRepository;
import com.uniTrade.uniTrade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/leads")
public class LeadController {

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create-lead")
    public ResponseEntity<Lead> createLead(@RequestBody Lead lead) {
        Optional<User> userOptional = userRepository.findByMatriculation(lead.getUserMatriculation());

        // Ensure the lead has a valid user matriculation number
        if (userOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println("Lead: " + lead);
        Lead newLead = leadRepository.save(lead);
        return new ResponseEntity<>(newLead, HttpStatus.CREATED);
    }

    @GetMapping("/lead-by-user/{matriculation}")
    public ResponseEntity<List<Lead>> getLeadsByUser(@PathVariable int matriculation) {
        List<Lead> leads = leadRepository.findByUserMatriculation(matriculation);

        System.out.println("Matriculation: " + matriculation);
        System.out.println("Leads found: " + leads.size());

        if (leads.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(leads, HttpStatus.OK);
        }
    }


    @GetMapping("/leads")
    public ResponseEntity<List<Lead>> getAllLeads() {
        List<Lead> leads = leadRepository.findAllByOrderByCreatedAtDesc();
        return new ResponseEntity<>(leads, HttpStatus.OK);
    }

    @GetMapping("/lead-by-id/{lId}")
    public ResponseEntity<Lead> getLeadByLeadId(@PathVariable int lId) {
        Optional<Lead> leadOptional = leadRepository.findByLId(lId);

        return leadOptional.map(lead -> new ResponseEntity<>(lead, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<Lead> updateLead(@PathVariable String id, @RequestBody Lead lead) {
        if (!lRepo.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        lead.setlId(Integer.parseInt((String)id));
        Lead updatedLead = lRepo.save(lead);
        return new ResponseEntity<>(updatedLead, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLead(@PathVariable String id) {
        if (!lRepo.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        lRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }*/

}
