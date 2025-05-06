package com.javaweb.converter;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
@Component
public class BuildingDTOConverter {
	@Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private RentAreaRepository rentAreaRepository;
    @Autowired
    private ModelMapper modelMapper;
    public BuildingDTO toBuildingDTO(BuildingEntity item) {
    	BuildingDTO building = modelMapper.map(item, BuildingDTO.class);
		DistrictEntity districtEntity = districtRepository.findNameById(item.getDistrictId());
		building.setAddress(item.getStreet() + ", " + item.getWard() + ", " + districtEntity.getName());
		building.setRentArea(rentAreaRepository.findValueById(item.getId()).stream().map(entity -> String.valueOf(entity.getValue())).collect(Collectors.joining(",")));
		/*
		 * building.setManagerName(item.getManagerName());
		 * building.setManagerPhoneNumber(item.getManagerPhoneNumber());
		 * building.setFloorArea(item.getFloorArea()); building.setId(item.getId());
		 * building.setNumberOfBasement(item.getNumberOfBasement());
		 * building.setName(item.getName()); building.setRentPrice(item.getRentPrice());
		 * building.setServiceFee(item.getServiceFee());
		 * building.setBrokerageFee(item.getBrokerageFee());
		 */
    	
		return building;
    }
}
