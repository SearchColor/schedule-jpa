package com.example.schedule.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    private String detail;

    public Schedule(){
    }

    public Schedule(String title, String detail){
        this.title = title;
        this.detail = detail;
    }

    public void updateTitleAndDetail(String title, String detail){
        this.title = title;
        this.detail = detail;
    }
}
