package org.example.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Properties;

@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;
    @Column
    @NotNull
    private String name;

    @OneToOne
    private PriceChart priceChart;

    public User(String name,PriceChart priceChart){
        this.name = name;
        this.priceChart = priceChart;
    }

    public User(){}

    public PriceChart getPriceChart() {
        return priceChart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public void setPriceChart(PriceChart priceChart) {
        this.priceChart = priceChart;
    }
}
