package org.example.entities;

import com.sun.istack.NotNull;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table
public class Reading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;


    @Column
    @NotNull
    private String product;


    @Column
    @NotNull
    private LocalDateTime date;

    @Column(nullable = false)
    private double value;

    public Reading(User user, String product, LocalDateTime date, double value){
        this.user = user;
        this.product = product;
        this.date = date;
        this.value = value;
    }

    public Reading(){}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getValue() {
        return value;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getProduct() {
        return product;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
