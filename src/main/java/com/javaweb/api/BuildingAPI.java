package com.javaweb.api;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.model.BuildingDTO;
import com.javaweb.model.BuildingRequestDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.service.BuildingService; 
    @RestController 
    @PropertySource("classpath:application.properties")
	@Transactional
public class BuildingAPI { 
    @Autowired
	private BuildingService buildingService;
    @Autowired
	private BuildingRepository buildingRepository;

	
	
	
	
	  @GetMapping(value = "/api/building/") 
	  public List<BuildingDTO> getBuilding(@RequestParam Map<String, Object> params,
	                                       @RequestParam(name = "typeCode", required = false) 
	   List<String> typeCode){
	   List<BuildingDTO> result = buildingService.findAll(params, typeCode); 
	   return result; }
	 
	 
	 
	
	/*
	 * @GetMapping(value = "/api/building/{name}/{street}") public BuildingDTO
	 * getBuildingById(@PathVariable String name ,
	 * 
	 * @PathVariable String street ) { BuildingDTO result = new BuildingDTO();
	 * List<BuildingEntity> building =
	 * buildingRepository.findByNameContainingAndStreet(name, street); return
	 * result; }
	 */
	
	@PutMapping(value = "/api/building/")
	public void updateBuilding(@RequestBody BuildingRequestDTO buildingRequestDTO) {
		BuildingEntity builEntity = buildingRepository.findById(buildingRequestDTO.getId()).get();
		builEntity.setName(buildingRequestDTO.getName());
		builEntity.setWard(buildingRequestDTO.getWard());
		builEntity.setStreet(buildingRequestDTO.getStreet());
        DistrictEntity districtEntity = new DistrictEntity();
        districtEntity.setId(buildingRequestDTO.getDistrictId());
        builEntity.setDistrict(districtEntity);
        buildingRepository.save(builEntity);
        System.out.print("ok");
	}
	
	@DeleteMapping(value = "/api/building/{ids}")
	public void deleteBuilding(@PathVariable List<Long> ids) {
		buildingRepository.deleteByIdIn(ids);
	}
}