package com.javaweb.builder;

import java.util.List;

public class BuildingSearchBuilder {
    private String name;
    private Long floorArea;
    private Long districtId;
    private String ward;
    private String street;
    private Long numberOfBasement;
    private List<String> typeCode;
    private String managerName;
    private String managerPhoneNumber;  // Đã đổi tên
    private Long areaFrom;
    private Long areaTo;
    private Long rentPriceFrom;        // Đã đổi tên
    private Long rentPriceTo;          // Đã đổi tên
    private Long staffId;
    
    private BuildingSearchBuilder(Builder builder) {
        this.name = builder.name;
        this.floorArea = builder.floorArea;
        this.districtId = builder.districtId;
        this.ward = builder.ward;
        this.street = builder.street;
        this.numberOfBasement = builder.numberOfBasement;
        this.typeCode = builder.typeCode;
        this.managerName = builder.managerName;
        this.managerPhoneNumber = builder.managerPhoneNumber;  // Đã đổi tên
        this.areaFrom = builder.areaFrom;
        this.areaTo = builder.areaTo;
        this.rentPriceFrom = builder.rentPriceFrom;           // Đã đổi tên
        this.rentPriceTo = builder.rentPriceTo;               // Đã đổi tên
        this.staffId = builder.staffId;
    }

    // Các getter
    public String getName() { return name; }
    public Long getFloorArea() { return floorArea; }
    public Long getDistrictId() { return districtId; }
    public String getWard() { return ward; }
    public String getStreet() { return street; }
    public Long getNumberOfBasement() { return numberOfBasement; }
    public List<String> getTypeCode() { return typeCode; }
    public String getManagerName() { return managerName; }
    public String getManagerPhoneNumber() { return managerPhoneNumber; }  // Đã đổi tên
    public Long getAreaFrom() { return areaFrom; }
    public Long getAreaTo() { return areaTo; }
    public Long getRentPriceFrom() { return rentPriceFrom; }              // Đã đổi tên
    public Long getRentPriceTo() { return rentPriceTo; }                  // Đã đổi tên
    public Long getStaffId() { return staffId; }

    public static class Builder {
        private String name;
        private Long floorArea;
        private Long districtId;
        private String ward;
        private String street;
        private Long numberOfBasement;
        private List<String> typeCode;
        private String managerName;
        private String managerPhoneNumber;  // Đã đổi tên
        private Long areaFrom;
        private Long areaTo;
        private Long rentPriceFrom;        // Đã đổi tên
        private Long rentPriceTo;          // Đã đổi tên
        private Long staffId;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        public Builder setFloorArea(Long floorArea) {
            this.floorArea = floorArea;
            return this;
        }
        public Builder setDistrictId(Long districtId) {
            this.districtId = districtId;
            return this;
        }
        public Builder setWard(String ward) {
            this.ward = ward;
            return this;
        }
        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }
        public Builder setNumberOfBasement(Long numberOfBasement) {
            this.numberOfBasement = numberOfBasement;
            return this;
        }
        public Builder setTypeCode(List<String> typeCode) {
            this.typeCode = typeCode;
            return this;
        }
        public Builder setManagerName(String managerName) {
            this.managerName = managerName;
            return this;
        }
        public Builder setManagerPhoneNumber(String managerPhoneNumber) {  // Đã đổi tên
            this.managerPhoneNumber = managerPhoneNumber;
            return this;
        }
        public Builder setAreaFrom(Long areaFrom) {
            this.areaFrom = areaFrom;
            return this;
        }
        public Builder setAreaTo(Long areaTo) {
            this.areaTo = areaTo;
            return this;
        }
        public Builder setRentPriceFrom(Long rentPriceFrom) {              // Đã đổi tên
            this.rentPriceFrom = rentPriceFrom;
            return this;
        }
        public Builder setRentPriceTo(Long rentPriceTo) {                  // Đã đổi tên
            this.rentPriceTo = rentPriceTo;
            return this;
        }
        public Builder setStaffId(Long staffId) {
            this.staffId = staffId;
            return this;
        }
        public BuildingSearchBuilder build() {
            return new BuildingSearchBuilder(this);
        }
    }
}