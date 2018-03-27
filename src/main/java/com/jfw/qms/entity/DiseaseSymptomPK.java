package com.jfw.qms.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class DiseaseSymptomPK implements Serializable {
    private int symptomId;
    private int diseaseId;

    @Column(name = "symptom_id", nullable = false)
    @Id
    public int getSymptomId() {
        return symptomId;
    }

    public void setSymptomId(int symptomId) {
        this.symptomId = symptomId;
    }

    @Column(name = "disease_id", nullable = false)
    @Id
    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiseaseSymptomPK that = (DiseaseSymptomPK) o;

        if (symptomId != that.symptomId) return false;
        if (diseaseId != that.diseaseId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = symptomId;
        result = 31 * result + diseaseId;
        return result;
    }
}
