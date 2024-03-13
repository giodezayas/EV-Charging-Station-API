package com.example.evchargingstationapi.model;


import com.example.evchargingstationapi.enums.ChargerType;
import com.example.evchargingstationapi.enums.StationStatus;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

@Table
public class ChargingStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String stationId;

    @Column(nullable = false)
    @Embedded
    private Location location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected ChargerType chargerType;

    @OneToMany(mappedBy = "chargingStation", cascade = CascadeType.ALL)
    private List<ChargingPoint> chargingPoints = new ArrayList<>();

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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ChargerType getChargerType() {
        return chargerType;
    }

    public void setChargerType(ChargerType chargerType) {
        this.chargerType = chargerType;
    }

    public List<ChargingPoint> getChargingPoints() {
        return chargingPoints;
    }

    public void setChargingPoints(List<ChargingPoint> chargingPoints) {
        this.chargingPoints = chargingPoints;
    }

    public StationStatus getStatus() {
        return status;
    }

    public void setStatus(StationStatus status) {
        this.status = status;
    }

    public void addChargingPoint(ChargingPoint chargingPoint) {
        chargingPoints.add(chargingPoint);
        chargingPoint.setChargingStation(this);
    }
}

