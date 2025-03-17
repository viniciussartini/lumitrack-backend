package br.com.backend.Lumitrack.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.backend.Lumitrack.models.enums.BuildingType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "lt_building")
public class Building implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer buildingType;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String name;
    private String address;
    private String cep;
    private String city;
    private String state;
    private Double totalArea;
    //private List<Area> areas = new ArrayList<>();

    public Building(){}

    public Building(Long id, BuildingType buildingType, User user, String name, String address, String cep, String city,
            String state, Double totalArea) {
        this.id = id;
        setBuildingType(buildingType);
        this.user = user;
        this.name = name;
        this.address = address;
        this.cep = cep;
        this.city = city;
        this.state = state;
        this.totalArea = totalArea;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BuildingType getBuildingType() {
        return BuildingType.valueOf(buildingType);
    }

    public void setBuildingType(BuildingType buildingType) {
        if (buildingType != null) {
            this.buildingType = buildingType.getCode();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(Double totalArea) {
        this.totalArea = totalArea;
    }

    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
/*
    public List<Area> getAreas() {
        return areas;
    }

 */


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
        Building other = (Building) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
