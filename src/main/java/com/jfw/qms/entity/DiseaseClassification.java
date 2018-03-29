package com.jfw.qms.entity;

import javax.persistence.*;

@Entity
@Table(name = "disease_classification", schema = "qms", catalog = "")
public class DiseaseClassification {
    private int diseaseId;
    private String diseaseName;
    private String diseaseDescribe;

    @Id
    @Column(name = "disease_id", nullable = false)
    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
    }

    @Basic
    @Column(name = "disease_name", nullable = true, length = 50)
    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    @Basic
    @Column(name = "disease_describe", nullable = true, length = 200)
    public String getDiseaseDescribe() {
        return diseaseDescribe;
    }

    public void setDiseaseDescribe(String diseaseDescribe) {
        this.diseaseDescribe = diseaseDescribe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiseaseClassification that = (DiseaseClassification) o;

        if (diseaseId != that.diseaseId) return false;
        if (diseaseName != null ? !diseaseName.equals(that.diseaseName) : that.diseaseName != null) return false;
        if (diseaseDescribe != null ? !diseaseDescribe.equals(that.diseaseDescribe) : that.diseaseDescribe != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = diseaseId;
        result = 31 * result + (diseaseName != null ? diseaseName.hashCode() : 0);
        result = 31 * result + (diseaseDescribe != null ? diseaseDescribe.hashCode() : 0);
        return result;
    }
}
