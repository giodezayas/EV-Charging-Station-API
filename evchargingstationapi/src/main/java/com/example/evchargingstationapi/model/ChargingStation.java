package com.example.evchargingstationapi.model;


import com.example.evchargingstationapi.enums.ChargerType;
import com.example.evchargingstationapi.enums.StationStatus;

import javax.persistence.*;

@Entity
@Table(name = "charging_station")
public class ChargingStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String stationId;

    @Column(nullable = false)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ChargerType chargerType;

    @Column(nullable = false)
    private int numberOfChargingPoints;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StationStatus status;

    // Constructor
    public ChargingStation() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ChargerType getChargerType() {
        return chargerType;
    }

    public void setChargerType(ChargerType chargerType) {
        this.chargerType = chargerType;
    }

    public int getNumberOfChargingPoints() {
        return numberOfChargingPoints;
    }

    public void setNumberOfChargingPoints(int numberOfChargingPoints) {
        this.numberOfChargingPoints = numberOfChargingPoints;
    }

    public StationStatus getStatus() {
        return status;
    }

    public void setStatus(StationStatus status) {
        this.status = status;
    }
}

