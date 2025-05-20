package com.javaweb.converter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.utils.MapUtil;

@Component
public class BuildingSearchBuilderConverter {
      public BuildingSearchBuilder toBuildingSearchBuilder(Map<String, Object> params, List<String> typeCode) {
    	  BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
    			                                                                 .setName(MapUtil.getObject(params, "name", String.class))
    			                                                                 .setAreaFrom(MapUtil.getObject(params, "areaFrom", Long.class))
    			                                                                 .setAreaTo(MapUtil.getObject(params, "areaTo", Long.class))
    			                                                                 .setDistrictId(MapUtil.getObject(params, "districtId", Long.class))
    			                                                                 .setFloorArea(MapUtil.getObject(params, "floorArea", Long.class))
    			                                                                 .setManagerName(MapUtil.getObject(params, "managerName", String.class))
    			                                                                 .setManagerPhoneName(MapUtil.getObject(params, "managerPhoneName", String.class))
    			                                                                 .setNumberOfBasement(MapUtil.getObject(params, "numberOfBasement", Long.class))
    			                                                                 .setPriceFrom(MapUtil.getObject(params, "priceFrom", Long.class))
    			                                                                 .setPriceTo(MapUtil.getObject(params, "priceTo", Long.class))
    			                                                                 .setStaffId(MapUtil.getObject(params, "staffId", Long.class))
    			                                                                 .setStreet(MapUtil.getObject(params, "street", String.class))
    			                                                                 .setTypeCode(typeCode)
    			                                                                 .setWard(MapUtil.getObject(params,"ward", String.class))
    			                                                                 .build();
    	  return buildingSearchBuilder;
      }
}
