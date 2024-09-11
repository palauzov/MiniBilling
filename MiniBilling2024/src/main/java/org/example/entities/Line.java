package org.example.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Table
@Entity
public class Line {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "index_value")
    private int index;

    @Column
    private int quantity;

    @Column
    private double amount;

    public Line(int index, int quantity, double amount){
        this.index = index;
        this.quantity = quantity;
        this.amount = amount;
    }
    public Line(){}



    public double getAmount() {
        return amount;
    }

    public int getIndex() {
        return index;
    }

    public int getQuantity() {
        return quantity;
    }


    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
