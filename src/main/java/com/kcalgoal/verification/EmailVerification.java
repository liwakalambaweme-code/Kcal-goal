package com.kcalgoal.verification;

import jakarta.persistence.*;
import java.time.*;
import java.util.UUID;
import com.kcalgoal.user.User;


@Entity
@Table(name= "email_verification_codes")
public class EmailVerification {

    @Id 
    @GeneratedValue (strategy= GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name= "user_id", nullable= false)
    private User user;

    @Column(nullable= false, length= 254)
    private String email;

    @Column(nullable= false)
    private String codeHash;

    @Column(nullable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant expiresAt;

    @Column(nullable= false)
    private int failedAttempts =0;

    @Column 
    private Instant consumedAt;

    @Column
    private Instant invalidatedAt;
protected EmailVerification(){
    //space for jpa
}
    public EmailVerification(User user, String email, String codeHash, Instant expiresAt) {
    this.user = user;
    this.email = email;
    this.codeHash = codeHash;
    this.expiresAt= expiresAt;
}   
    @PrePersist
    void onCreate() {
    Instant now = Instant.now();
    this.createdAt = now;
}
    //getters
    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getEmail() {
        return email;
    }

    public String getCodeHash() {
        return codeHash;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public Instant getConsumedAt() {
        return consumedAt;
    }

    public Instant getInvalidatedAt() {
        return invalidatedAt;
    }
public void consume() {
    this.consumedAt = Instant.now();
}

public void invalidate(){
    this.invalidatedAt= Instant.now();

}
public void recordFailedAttempt(){
    this.failedAttempts = failedAttempts+ 1;
}


  
    
    
}

