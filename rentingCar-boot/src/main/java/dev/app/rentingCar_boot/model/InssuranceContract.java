package dev.app.rentingCar_boot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "inssurance_contract")
public class InssuranceContract {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String contractId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    @JsonBackReference
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inssurance_cia_id", nullable = false)
    private InssuranceCia inssuranceCia;

    private LocalDate startDate;
    private LocalDate endDate;

    // Constructores
    public InssuranceContract() {}

    public InssuranceContract(Car car, InssuranceCia inssuranceCia, LocalDate startDate, LocalDate endDate) {
        this.contractId = UUID.randomUUID().toString();
        this.car = car;
        this.inssuranceCia = inssuranceCia;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters y Setters
    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public InssuranceCia getInssuranceCia() {
        return inssuranceCia;
    }

    public void setInssuranceCia(InssuranceCia inssuranceCia) {
        this.inssuranceCia = inssuranceCia;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}