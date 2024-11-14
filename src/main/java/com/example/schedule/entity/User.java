package com.example.schedule.entity;


import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.processing.Pattern;

@Getter
@Entity
@Table(name = "user")
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    public User() {
    }

    public User(String username , String password , String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }


}
