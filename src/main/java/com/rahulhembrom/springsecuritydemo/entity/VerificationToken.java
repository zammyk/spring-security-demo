package com.rahulhembrom.springsecuritydemo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class VerificationToken {
    // Expiration in 10 minutes
    private static final int EXPIRATION_MINUTES = 10;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Date expirationTime;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_USER_VERIFY_TOKEN")
    )
    private User user;

    public VerificationToken(User user, String token){
        this.user = user;
        this.token = token;
        this.expirationTime = calculateExpirationTime(EXPIRATION_MINUTES);
    }

    public VerificationToken(String token){
        this.token = token;
        this.expirationTime = calculateExpirationTime(EXPIRATION_MINUTES);
    }

    private Date calculateExpirationTime(int expirationMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE,expirationMinutes);
        return new Date(calendar.getTime().getTime());
    }
}
