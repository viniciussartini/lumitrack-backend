package br.com.backend.Lumitrack.models;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "lt_area")
public class Area implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double squareArea;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL)
    private List<Device> devices = new ArrayList<>();

    public Area(){}

    public Area(Long id, String name, Double squareArea, Building building) {
        this.id = id;
        this.name = name;
        this.squareArea = squareArea;
        this.building = building;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSquareArea() {
        return squareArea;
    }

    public void setSquareArea(Double squareArea) {
        this.squareArea = squareArea;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public TreeMap<Instant, Double> getDailyConsumption() {
        TreeMap<Instant, Double> areaDailyConsumption = new TreeMap<>();
        for(Device device : devices) {
            TreeMap<Instant, Double> deviceConsumption = device.getDailyConsumption();
            for(Instant date : deviceConsumption.keySet()) {
                Double consumptionValue = deviceConsumption.get(date);
                areaDailyConsumption.merge(date, consumptionValue, Double::sum);
            }
        }
        return areaDailyConsumption;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Area other = (Area) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
}
