package com.javaweb.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "buildingrenttype")
public class BuildingRentTypeEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	
	@ManyToOne
	@JoinColumn(name = "buildingid")
	private BuildingEntity building;
	
	@ManyToOne
	@JoinColumn(name = "renttypeid")
	private BuildingEntity renttype;

    // Getters and Setters\
	
	
    public Long getId() {
        return id;
    }

    public BuildingEntity getBuilding() {
		return building;
	}

	public void setBuilding(BuildingEntity building) {
		this.building = building;
	}

	public BuildingEntity getRenttype() {
		return renttype;
	}

	public void setRenttype(BuildingEntity renttype) {
		this.renttype = renttype;
	}

	public void setId(Long id) {
        this.id = id;
    }

}