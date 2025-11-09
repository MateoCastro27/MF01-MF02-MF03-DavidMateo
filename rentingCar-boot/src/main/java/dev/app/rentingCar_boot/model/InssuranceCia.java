package dev.app.rentingCar_boot.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "inssurance_cia")
public class InssuranceCia {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String name;
    private String description;
    private int qtyEmployee;
    private boolean isActive;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "inssurance_cia_delegations", joinColumns = @JoinColumn(name = "inssurance_cia_id"))
    @Column(name = "delegation", length = 500)
    private List<String> delegations = new ArrayList<>();

    // Constructores
    public InssuranceCia() {
        // No asignar id manualmente, dejar que @GeneratedValue lo haga
    }

    public InssuranceCia(String name) {
        this.name = name;
    }

    // Getters & Setters
    public List<String> getDelegations() {
        return delegations;
    }

    public void setDelegations(List<String> delegations) {
        this.delegations = delegations;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQtyEmployee() {
        return qtyEmployee;
    }

    public void setQtyEmployee(int qtyEmployee) {
        this.qtyEmployee = qtyEmployee;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

