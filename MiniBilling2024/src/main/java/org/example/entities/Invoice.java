package org.example.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "document_number_sequence_generator")
    @SequenceGenerator(name = "document_number_sequence_generator",
            sequenceName = "document_number_sequence", allocationSize = 1, initialValue = 10000)
    private Long documentNumber;

    @Column
    @NotNull
    private LocalDateTime date;


    @ManyToOne
    @NotNull
    private User user;

    @OneToMany
    @NotNull
    private List<Line> lines;

    @ManyToOne
    private StandingCharge standingCharge;

    @Column(nullable = false)
    private double vat;

    @Column(nullable = false)
    private double amountWithoutVat;

    @Column(nullable = false)
   private double finalAmount;


   public Invoice(LocalDateTime date, User user, StandingCharge standingCharge, double amountWithoutVat, double vat, double finalAmount){
       this.date = date;
       this.user = user;
       this.lines = new ArrayList<>();
       this.standingCharge = standingCharge;
       this.finalAmount = finalAmount;
       this.vat = vat;
       this.amountWithoutVat = amountWithoutVat;
   }

   public Invoice(){

   }

    public LocalDateTime getDate() {
        return date;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public List<Line> getLines() {
        return lines;
    }


    public User getUser() {
        return user;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }


    public void setFinalAmount(double finalAmount) {
        this.finalAmount = finalAmount;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(Long documentNumber) {
        this.documentNumber = documentNumber;
    }

    public double getAmountWithoutVat() {
        return amountWithoutVat;
    }

    public double getVat() {
        return vat;
    }

    public void setAmountWithoutVat(double amountWithoutVat) {
        this.amountWithoutVat = amountWithoutVat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public StandingCharge getStandingCharge() {
        return standingCharge;
    }

    public void setStandingCharge(StandingCharge standingCharge) {
        this.standingCharge = standingCharge;
    }
}
