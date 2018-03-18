package com.jfw.qms.model;

import java.util.List;

public class Area {
    private List<AreaProvince> provinceList;
    private List<AreaCity> cityList;
    private List<AreaDistrict> districtList;

    public List<AreaProvince> getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(List<AreaProvince> provinceList) {
        this.provinceList = provinceList;
    }

    public List<AreaCity> getCityList() {
        return cityList;
    }

    public void setCityList(List<AreaCity> cityList) {
        this.cityList = cityList;
    }

    public List<AreaDistrict> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(List<AreaDistrict> districtList) {
        this.districtList = districtList;
    }
}
