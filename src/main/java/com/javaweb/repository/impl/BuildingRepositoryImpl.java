package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.utils.ConnectionJDBCUtil;
import com.javaweb.utils.IntegerUtil;
import com.javaweb.utils.StringUtil;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {

   

    public static void joinTable(Map<String, Object> params, List<String> typeCode, StringBuilder sql) {
        String staffId = (String) params.get("staffId");
        if (StringUtil.checkString(staffId)) {
            sql.append(" INNER JOIN assignmentbuilding ON b.id = assignmentbuilding.buildingid ");
        }
        if (typeCode != null && typeCode.size() != 0) {
            sql.append(" INNER JOIN buildingrenttype ON b.id = buildingrenttype.buildingid ");
            sql.append(" INNER JOIN renttype ON renttype.id = buildingrenttype.renttypeid ");
        }
        
    }

    public static void queryNomal(Map<String, Object> params, StringBuilder where) {
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (!entry.getKey().equals("staffId") &&
                !entry.getKey().equals("typeCode") &&
                !entry.getKey().startsWith("area") &&
                !entry.getKey().startsWith("rentPrice")) {
                String value = entry.getValue().toString();
                if (StringUtil.checkString(value)) {
                    if (IntegerUtil.isNumber(value)) {
                        where.append(" AND b." + entry.getKey() + " = " + value);
                        break;
                    }
                    where.append(" AND b." + entry.getKey() + " LIKE '%" + value + "%' ");
                }
            }
        }
    }

    public static void querySpecial(Map<String, Object> params, List<String> typeCode, StringBuilder where) {
        String staffId = (String) params.get("staffId");
        if (StringUtil.checkString(staffId)) {
            where.append(" AND assignmentbuilding.staffid = " + staffId);
        }
        String rentAreaFrom = (String) params.get("areaFrom");
        String rentAreaTo = (String) params.get("areaTo");
        if(StringUtil.checkString(rentAreaFrom) == true || StringUtil.checkString(rentAreaTo) == true) {
        	where.append(" AND EXISTS (SELECT * FROM rentarea r WHERE r.buildingid = b.id ");
        if (StringUtil.checkString(rentAreaFrom)) {
            where.append(" AND r.value >= " + rentAreaFrom);
        }
        if (StringUtil.checkString(rentAreaTo)) {
            where.append(" AND r.value <= " + rentAreaTo);
        }
            where.append(" ) ");
        }
         //java7 
			/*
			 * if (typeCode != null && typeCode.size() != 0) {
			 * 
			 * List<String> code = new ArrayList<>();
			 *  for (String item : typeCode) {
			 * code.add("'" + item + "'"); 
			 * } 
			 * where.append(" AND renttype.code IN( " +
			 * String.join(",", code) + ")");
			    }
			 */
        	//java8
             if(typeCode != null && typeCode.size() != 0) {
            	 where.append(" AND(");
            	 String sql = typeCode.stream().map(it -> " renttype.code Like" + "'%" + it + "%' ").collect(Collectors.joining(" OR "));
            	 where.append(sql + " ) ");
             }
    }

    @Override
    public List<BuildingEntity> findAll(Map<String, Object> params, List<String> typeCode) {
        StringBuilder sql = new StringBuilder("SELECT ")
            .append("b.id, b.name, b.districtid, b.floorarea, b.ward, b.street, ")
            .append("b.numberofbasement, b.direction, b.level, b.rentprice, ")
            .append("b.managername, b.managerphonenumber, b.servicefee, b.brokeragefee ")
            .append("FROM building b ");
        
        StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
        joinTable(params, typeCode, sql);
        queryNomal(params, where);
        querySpecial(params, typeCode, where);
        
        sql.append(where)
           .append(" GROUP BY b.id");
        
        List<BuildingEntity> buildingEntities = new ArrayList<>();
        try (Connection conn = ConnectionJDBCUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql.toString())) {
            
            while (rs.next()) {
                BuildingEntity building = new BuildingEntity();
                building.setId(rs.getLong("id"));
                building.setName(rs.getString("name"));
                building.setStreet(rs.getString("street"));
                building.setWard(rs.getString("ward"));
                building.setDistrictId(rs.getLong("districtid"));
                building.setNumberOfBasement(rs.getInt("numberofbasement"));
                building.setManagerName(rs.getString("managername"));
                building.setManagerPhoneNumber(rs.getString("managerphonenumber"));
                building.setFloorArea(rs.getInt("floorarea"));
                building.setRentPrice(rs.getInt("rentprice"));
                building.setServiceFee(rs.getString("servicefee"));
                building.setBrokerageFee(rs.getDouble("brokeragefee"));
                buildingEntities.add(building);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buildingEntities;
    }
}