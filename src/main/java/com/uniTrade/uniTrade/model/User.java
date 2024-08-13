package com.uniTrade.uniTrade.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "Users")
public class User {
    @Id
    @JsonProperty("_id")
    private String _id;
    private int matriculation;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String email;
    private String password;
    private List<String> role;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;

    public User() {
        this.createdAt = LocalDateTime.now();
        this.role = new ArrayList<>();
        this.role.add("user");
    }

    public User(String _id, int matriculation, String firstName, String lastName, LocalDate dob, String email, String password, List<String> role, LocalDateTime createdAt) {
        this._id = _id;
        this.matriculation = matriculation;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
        this.password = password;
        this.role = role;
        /*this.role = (role != null && !role.isEmpty()) ? role : new ArrayList<>();
        if (this.role.isEmpty()) {
            this.role.add("user");
        }*/
        this.createdAt = createdAt;
        //this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
    }

    public LocalDateTime getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(LocalDateTime lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public int getMatriculation() {
        return matriculation;
    }

    public void setMatriculation(int matriculation) {
        this.matriculation = matriculation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + _id + '\'' +
                ", matriculation='" + matriculation + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", createdAt=" + createdAt +
                ", lastUpdatedAt=" + lastUpdatedAt +
                '}';
    }
}
