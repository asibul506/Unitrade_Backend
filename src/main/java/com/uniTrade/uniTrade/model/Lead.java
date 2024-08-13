package com.uniTrade.uniTrade.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "Leads")
public class Lead {
    @Id
    private String _id;
    private int lId;
    private int userMatriculation; // Matriculation number of the user who created the lead
    private String content; // Content of the lead
    private List<String> imageUrls; // URLs for images associated with the lead
    private LocalDateTime createdAt; // Timestamp for when the lead was created
    private LocalDateTime lastUpdatedAt; // Timestamp for when the lead was last updated
    private List<String> comments; // Comments on the lead
    private List<String> likes; // Likes on the lead

    public Lead() {
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters

    public int getlId() {
        return lId;
    }

    public void setlId(int lId) {
        this.lId = lId;
    }

    public int getUserMatriculation() {
        return userMatriculation;
    }

    public void setUserMatriculation(int userMatriculation) {
        this.userMatriculation = userMatriculation;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(LocalDateTime lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public List<String> getLikes() {
        return likes;
    }

    public void setLikes(List<String> likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Lead{" +
                "LId='" + lId + '\'' +
                ", userMatriculation=" + userMatriculation +
                ", content='" + content + '\'' +
                ", imageUrls=" + imageUrls +
                ", createdAt=" + createdAt +
                ", lastUpdatedAt=" + lastUpdatedAt +
                ", comments=" + comments +
                ", likes=" + likes +
                '}';
    }
}
