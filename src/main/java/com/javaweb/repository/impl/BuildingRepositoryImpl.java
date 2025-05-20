package com.javaweb.repository.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionJDBCUtil;
import com.javaweb.utils.IntegerUtil;
import com.javaweb.utils.StringUtil;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
	
    public static void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
        Long staffId = buildingSearchBuilder.getStaffId();
        if(staffId != null) {
            sql.append(" INNER JOIN assignmentbuilding ON b.id = assignmentbuilding.buildingid ");
        }
        if (buildingSearchBuilder.getTypeCode() != null && buildingSearchBuilder.getTypeCode().size() != 0) {
            sql.append(" INNER JOIN buildingrenttype ON b.id = buildingrenttype.buildingid ");
            sql.append(" INNER JOIN renttype ON renttype.id = buildingrenttype.renttypeid ");
        }
        
    }

    public static void queryNomal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
    	try {
    		Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
    		for(Field item : fields) {
    			item.setAccessible(true);
    			String fieldName = item.getName();
    			if(fieldName.equals("staffId") || fieldName.equals("typeCode") ||
    			  fieldName.startsWith("area") ||
    			  fieldName.startsWith("rentPrice")) {
    				continue;
    			}
    			 Object value = item.get(buildingSearchBuilder);
    			 if(value != null) {
    				 if(item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer")) { 
    					 where.append(" AND b." + fieldName + " = " + value); 
    					 } 
    				 else if(item.getType().getName().equals("java.lang.String"))
    				 where.append(" AND b." + fieldName + " LIKE '%"+ value + "%' "); 
    		}
    		
    	}
    	}
    	catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }

    public static void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
        Long staffId = buildingSearchBuilder.getStaffId();
        if(staffId != null) {
            where.append(" AND assignmentbuilding.staffid = " + staffId);
        }
        Long rentAreaFrom = buildingSearchBuilder.getAreaFrom();
        Long rentAreaTo = buildingSearchBuilder.getAreaTo();
        if(rentAreaFrom != null || rentAreaTo != null ) {	
        	where.append(" AND EXISTS (SELECT * FROM rentarea r WHERE r.buildingid = b.id ");
        if (rentAreaFrom != null) {
            where.append(" AND r.value >= " + rentAreaFrom);
        }
        if (rentAreaTo != null) {
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
             if(buildingSearchBuilder.getTypeCode() != null && buildingSearchBuilder.getTypeCode().size() != 0) {
            	 where.append(" AND(");
            	 String sql = buildingSearchBuilder.getTypeCode().stream().map(it -> " renttype.code Like" + "'%" + it + "%' ").collect(Collectors.joining(" OR "));
            	 where.append(sql + " ) ");
             }
    }

    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sql = new StringBuilder("SELECT ")
            .append("b.id, b.name, b.districtid, b.floorarea, b.ward, b.street, ")
            .append("b.numberofbasement, b.direction, b.level, b.rentprice, ")
            .append("b.managername, b.managerphonenumber, b.servicefee, b.brokeragefee ")
            .append("FROM building b ");
        
        StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
        joinTable(buildingSearchBuilder, sql);
        queryNomal(buildingSearchBuilder, where);
        querySpecial(buildingSearchBuilder, where);
        
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