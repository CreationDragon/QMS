package com.jfw.qms.model;

import javax.persistence.*;

@Entity
@Table(name = "area_province", schema = "qms", catalog = "")
public class AreaProvince {
    private int provinceId;
    private String provinceName;

    @Id
    @Column(name = "province_id", nullable = false)
    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    @Basic
    @Column(name = "province_name", nullable = true, length = 20)
    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AreaProvince that = (AreaProvince) o;

        if (provinceId != that.provinceId) return false;
        if (provinceName != null ? !provinceName.equals(that.provinceName) : that.provinceName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = provinceId;
        result = 31 * result + (provinceName != null ? provinceName.hashCode() : 0);
        return result;
    }

}
