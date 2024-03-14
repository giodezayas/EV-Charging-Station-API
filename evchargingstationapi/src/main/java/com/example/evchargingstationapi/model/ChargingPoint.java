package com.example.evchargingstationapi.model;

import com.example.evchargingstationapi.enums.PowerLevel;
import com.example.evchargingstationapi.enums.StationStatus;

import javax.persistence.*;

@Entity
@Table(name = "charging_points")
public class ChargingPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "charging_station_id")
    private ChargingStation chargingStation;

    @Column(nullable = false)
    private String identifier;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PowerLevel powerLevel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StationStatus status;

    // Constructor vacío
    public ChargingPoint() {
    }

    // Constructor con todos los atributos
    public ChargingPoint(String identifier, PowerLevel powerLevel, StationStatus status) {
        this.identifier = identifier;
        this.powerLevel = powerLevel;
        this.status = status;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ChargingStation getChargingStation() {
        return chargingStation;
    }

    public void setChargingStation(ChargingStation chargingStation) {
        this.chargingStation = chargingStation;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public PowerLevel getPowerLevel() {
        return powerLevel;
    }

    public void setPowerLevel(PowerLevel powerLevel) {
        this.powerLevel = powerLevel;
    }

    public StationStatus getStatus() {
        return status;
    }

    public void setStatus(StationStatus status) {
        this.status = status;
    }
}
