package com.kcalgoal.user;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity 
@Table (name= "users")
public class User {

    @Id 
    @GeneratedValue (strategy= GenerationType.UUID)
    private UUID id;

    @Column(nullable= false, length = 30)
    private String username;

    @Column(nullable = false, length=254)
    private String email;

    @Column(nullable= false)
    private String passwordHash;

    private Instant emailVerifiedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable= false, length= 20)
    private UserStatus status = UserStatus.ACTIVE;
    
    @Column(nullable= false, length= 20)
    private String termsVersion;

    @Column(nullable = false)
    private Instant termsAcceptedAt;

    private Instant deletionRequestedAt;

    @Column(nullable = false, updatable= false)
    private Instant createdAt;

    @Column(nullable =false)
    private Instant updatedAt;

    protected User(){
        //for JPA to load rows
    }

    public User (String username, String email, String passwordHash, String termsVersion, Instant termsAcceptedAt){
        this.username = username;
        this.email= email;
        this.passwordHash= passwordHash;
        this.termsVersion= termsVersion;
        this.termsAcceptedAt= termsAcceptedAt;

    }

    @PrePersist 
    void onCreate(){
        Instant now = Instant.now();
        this.createdAt= now;
        this.updatedAt= now;

    }

    @PreUpdate void onUpdate(){
        this.updatedAt = Instant.now();

    }
    public UUID getId(){
        return id;
    }
    public String getUsername(){
        return username;
    }
    public String getEmail(){
        return email;
    }
    public String getPasswordHash(){
        return passwordHash;
    }
    public Instant getEmailVerifiedAt(){
        return emailVerifiedAt;
    }
    public UserStatus getStatus(){
        return status;
    }    
    public Instants getTermsVersion(){
        return termsVersion;
    }
    public 
    public void setUsername(String username){
        this.username = username;
    }

    public void setEmail(String email){
     this.email= email;}
    
     public void setPasswordHash(String passwordHash){
        this.passwordHash= passwordHash;}

     public void setTermsVersion(String termsVersion){   
        this.termsVersion= termsVersion;}

     public void setTermsAcceptedAt(Instant termsAcceptedAt){
         this.termsAcceptedAt= termsAcceptedAt;

     }   
       
}
