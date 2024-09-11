package org.example.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class StandingCharge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int quantity;
    @Column
    private LocalDateTime startDate;
    @Column
    private LocalDateTime endDate;
    @Column
    private double price;
    @Column
    private double amountWithoutVat;
    @Column
    private double vat;
    @Column
    private double amountWithVat;


    public StandingCharge(){}
    public StandingCharge(int quantity, double price, LocalDateTime startDate, LocalDateTime endDate,
                          double amountWithoutVat,double vat, double amountWithVat){
        this.quantity = quantity;
        this.price = price;
        this.amountWithoutVat = amountWithoutVat;
        this.vat = vat;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amountWithVat = amountWithVat;
    }

    public double getAmountWithoutVat() {
        return amountWithoutVat;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAmountWithVat() {
        return amountWithVat;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setAmountWithoutVat(double amountWithoutVat) {
        this.amountWithoutVat = amountWithoutVat;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setAmountWithVat(double amountWithVat) {
        this.amountWithVat = amountWithVat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
