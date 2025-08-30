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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class CustomerEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(name = "fullname")
  private String fullname;
  
  @Column(name = "phone")
  private String phone;
  
  @Column(name = "email")
  private String email;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "assignmentcustomer",
  joinColumns = @JoinColumn (name = "staffid", nullable = false),
  inverseJoinColumns = @JoinColumn (name = "customerid", nullable = false))
private List<UserEntity> users = new ArrayList<>();
 


public List<UserEntity> getUsers() {
	return users;
}

public void setUsers(List<UserEntity> users) {
	this.users = users;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getFullname() {
	return fullname;
}

public void setFullname(String fullname) {
	this.fullname = fullname;
}

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}
  
  
}
