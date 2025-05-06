package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.DistrictEntity;
@Repository
public class DistrictRepositoryImpl implements DistrictRepository{
	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS = "Hoang@2k4";
    
	
	@Override
	public DistrictEntity findNameById(Long id) {
		DistrictEntity districtEntity = new DistrictEntity(); // Khai báo biến ở phạm vi phương thức
	    StringBuilder sql = new StringBuilder("SELECT name FROM district WHERE district.id = " + id);
	    
	    try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	            Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery(sql.toString());
	            )
	    {
	       while(rs.next()){
	    	   districtEntity.setName(rs.getString("name"));
	       }
	    }
	    catch(SQLException e) {
	        e.printStackTrace();
	    }
	    return districtEntity;
	}

}
