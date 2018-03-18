package com.jfw.qms.entity;

import javax.persistence.*;

@Entity
@Table(name = "area_district", schema = "qms", catalog = "")
public class AreaDistrict {
    private int districtId;
    private Integer cityId;
    private String districtName;

    @Id
    @Column(name = "district_id", nullable = false)
    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    @Basic
    @Column(name = "city_id", nullable = true)
    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "district_name", nullable = true, length = 20)
    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AreaDistrict that = (AreaDistrict) o;

        if (districtId != that.districtId) return false;
        if (cityId != null ? !cityId.equals(that.cityId) : that.cityId != null) return false;
        if (districtName != null ? !districtName.equals(that.districtName) : that.districtName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = districtId;
        result = 31 * result + (cityId != null ? cityId.hashCode() : 0);
        result = 31 * result + (districtName != null ? districtName.hashCode() : 0);
        return result;
    }
}
