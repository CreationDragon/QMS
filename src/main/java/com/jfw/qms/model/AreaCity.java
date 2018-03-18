package com.jfw.qms.model;

import javax.persistence.*;

@Entity
@Table(name = "area_city", schema = "qms", catalog = "")
public class AreaCity {
    private int cityId;
    private Integer provinceId;
    private String cityName;

    @Id
    @Column(name = "city_id", nullable = false)
    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "province_id", nullable = true)
    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    @Basic
    @Column(name = "city_name", nullable = true, length = 20)
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AreaCity areaCity = (AreaCity) o;

        if (cityId != areaCity.cityId) return false;
        if (provinceId != null ? !provinceId.equals(areaCity.provinceId) : areaCity.provinceId != null) return false;
        if (cityName != null ? !cityName.equals(areaCity.cityName) : areaCity.cityName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cityId;
        result = 31 * result + (provinceId != null ? provinceId.hashCode() : 0);
        result = 31 * result + (cityName != null ? cityName.hashCode() : 0);
        return result;
    }
}
