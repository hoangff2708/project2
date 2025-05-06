package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.RentAreaEntity;
@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository{
	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS = "Hoang@2k4";
	@Override
	public List<RentAreaEntity> findValueById(Long id) {
		StringBuilder sql = new StringBuilder("SELECT value FROM rentarea WHERE buildingid = " + id);
		List<RentAreaEntity> rentAreaEntity = new ArrayList<>();
		
		try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	            Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery(sql.toString());
	            )
		{
			while(rs.next()) {
				RentAreaEntity rentArea = new RentAreaEntity();
				rentArea.setValue(rs.getInt("value"));
				rentAreaEntity.add(rentArea);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return rentAreaEntity;
	}

}
