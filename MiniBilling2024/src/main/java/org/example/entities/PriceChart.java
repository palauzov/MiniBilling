package org.example.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class PriceChart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotNull
    private String product;

    @Column
    @NotNull
    private LocalDate startDate;

    @Column
    @NotNull
    private LocalDate endDate;

     @Column(nullable = false)
    private double price;

    public PriceChart(String product, LocalDate startDate, LocalDate endDate, double price){
        this.product = product;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }
    public PriceChart(){}

    public String getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
