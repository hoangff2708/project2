package com.javaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "building")
public class BuildingEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "street")
    private String street;
    
    @Column(name = "ward")
    private String ward;
    
    @Column(name = "managername")
    private String managerName;
    
    @Column(name = "managerphonenumber")
    private String manager;
    
    @Column(name = "rentprice")
    private String rentPrice;
    
	@ManyToOne
    @JoinColumn(name = "districtid")
    private DistrictEntity district;

    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
    private List<RentAreaEntity> rentAreaEntities = new ArrayList<>();
    
    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
    private List<BuildingRentTypeEntity> buildingRentTypeEntities = new ArrayList<>();
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "assignmentbuilding",
	           joinColumns = @JoinColumn (name = "userid", nullable = false),
	           inverseJoinColumns = @JoinColumn (name = "buildingid", nullable = false))
    private List<UserEntity> users = new ArrayList<>();

    
	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	public List<BuildingRentTypeEntity> getBuildingRentTypeEntities() {
		return buildingRentTypeEntities;
	}

	public void setBuildingRentTypeEntities(List<BuildingRentTypeEntity> buildingRentTypeEntities) {
		this.buildingRentTypeEntities = buildingRentTypeEntities;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(String rentPrice) {
		this.rentPrice = rentPrice;
	}


    	
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public DistrictEntity getDistrict() {
		return district;
	}

	public void setDistrict(DistrictEntity district) {
		this.district = district;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }


 
	public List<RentAreaEntity> getRentAreaEntities() {
		return rentAreaEntities;
	}

	public void setRentAreaEntities(List<RentAreaEntity> rentAreaEntities) {
		this.rentAreaEntities = rentAreaEntities;
	}

}