package com.uniTrade.uniTrade.controller;

import com.uniTrade.uniTrade.model.Lead;
import com.uniTrade.uniTrade.model.User;
import com.uniTrade.uniTrade.repository.LeadRepository;
import com.uniTrade.uniTrade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/leads")
public class LeadController {

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/leads")
    public ResponseEntity<List<Lead>> getAllLeads() {
        List<Lead> leads = leadRepository.findAllByOrderByCreatedAtDesc();
        return new ResponseEntity<>(leads, HttpStatus.OK);
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

    @GetMapping("/lead-by-id/{lId}")
    public ResponseEntity<Lead> getLeadByLeadId(@PathVariable int lId) {
        Optional<Lead> leadOptional = leadRepository.findBylId(lId);

        return leadOptional.map(lead -> new ResponseEntity<>(lead, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create-lead")
    public ResponseEntity<Lead> createLead(@RequestBody Lead lead) {
        Optional<User> userOptional = userRepository.findByMatriculation(lead.getUserMatriculation());

        // Ensure the lead has a valid user matriculation number
        if (userOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println("Creating lead");
        System.out.println("Lead: " + lead);
        Lead newLead = leadRepository.save(lead);
        return new ResponseEntity<>(newLead, HttpStatus.CREATED);
    }

    @PutMapping("/update/{lId}")
    public ResponseEntity<Lead> updateLead(@PathVariable int lId, @RequestBody Lead lead) {
        Optional<Lead> leadOptional = leadRepository.findBylId(lId);

        if (leadOptional.isPresent()) {
            Lead leadToUpdate = leadOptional.get();
            leadToUpdate.setContent(lead.getContent());
            //leadToUpdate.setUserMatriculation(lead.getUserMatriculation());
            leadToUpdate.setCreatedAt(lead.getCreatedAt());
            leadToUpdate.setContent(lead.getContent());
            leadToUpdate.setImageUrls(lead.getImageUrls());
            leadToUpdate.setComments(lead.getComments());
            leadToUpdate.setLikes(lead.getLikes());

            if (leadToUpdate.getLastUpdatedAt() == null) {
                leadToUpdate.setLastUpdatedAt(LocalDateTime.now());
            }

            return new ResponseEntity<>(leadRepository.save(leadToUpdate), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{matriculation}/{lId}")
    public ResponseEntity<Void> deleteLead(@PathVariable int matriculation, @PathVariable int lId) {
        Optional<Lead> leadOptional = leadRepository.findBylId(lId);

        if (leadOptional.isPresent()) {
            Lead lead = leadOptional.get();
            if (lead.getUserMatriculation() == matriculation) {
                leadRepository.deleteBylId(lead.getlId());

/*              System.out.println("Lead deleted: " + lead.getlId());
                System.out.println("Mat: " + matriculation);
                System.out.println("Lead Id: " + lId);
                System.out.println(lead.toString());
                */
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
