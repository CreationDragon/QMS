package com.jfw.qms.entity;

import javax.persistence.Entity;

@Entity
@javax.persistence.Table(name = "disease_symptom", schema = "qms", catalog = "")
@javax.persistence.IdClass(com.jfw.qms.entity.DiseaseSymptomPK.class)
public class DiseaseSymptom {
    private int symptomId;

    @javax.persistence.Id
    @javax.persistence.Column(name = "symptom_id", nullable = false)
    public int getSymptomId() {
        return symptomId;
    }

    public void setSymptomId(int symptomId) {
        this.symptomId = symptomId;
    }

    private int diseaseId;

    @javax.persistence.Id
    @javax.persistence.Column(name = "disease_id", nullable = false)
    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
    }

    private String symptomName;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "symptom_name", nullable = true, length = 20)
    public String getSymptomName() {
        return symptomName;
    }

    public void setSymptomName(String symptomName) {
        this.symptomName = symptomName;
    }

    private String symptomOptionA;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "symptom_optionA", nullable = true, length = 2)
    public String getSymptomOptionA() {
        return symptomOptionA;
    }

    public void setSymptomOptionA(String symptomOptionA) {
        this.symptomOptionA = symptomOptionA;
    }

    private String symptomOptionB;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "symptom_optionB", nullable = true, length = 2)
    public String getSymptomOptionB() {
        return symptomOptionB;
    }

    public void setSymptomOptionB(String symptomOptionB) {
        this.symptomOptionB = symptomOptionB;
    }

    private String symptomOptionC;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "symptom_optionC", nullable = true, length = 2)
    public String getSymptomOptionC() {
        return symptomOptionC;
    }

    public void setSymptomOptionC(String symptomOptionC) {
        this.symptomOptionC = symptomOptionC;
    }

    private String symptomOptionD;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "symptom_optionD", nullable = true, length = 2)
    public String getSymptomOptionD() {
        return symptomOptionD;
    }

    public void setSymptomOptionD(String symptomOptionD) {
        this.symptomOptionD = symptomOptionD;
    }

    private String symptomDescribe;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "symptom_describe", nullable = true, length = 200)
    public String getSymptomDescribe() {
        return symptomDescribe;
    }

    public void setSymptomDescribe(String symptomDescribe) {
        this.symptomDescribe = symptomDescribe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiseaseSymptom that = (DiseaseSymptom) o;

        if (symptomId != that.symptomId) return false;
        if (diseaseId != that.diseaseId) return false;
        if (symptomName != null ? !symptomName.equals(that.symptomName) : that.symptomName != null) return false;
        if (symptomOptionA != null ? !symptomOptionA.equals(that.symptomOptionA) : that.symptomOptionA != null)
            return false;
        if (symptomOptionB != null ? !symptomOptionB.equals(that.symptomOptionB) : that.symptomOptionB != null)
            return false;
        if (symptomOptionC != null ? !symptomOptionC.equals(that.symptomOptionC) : that.symptomOptionC != null)
            return false;
        if (symptomOptionD != null ? !symptomOptionD.equals(that.symptomOptionD) : that.symptomOptionD != null)
            return false;
        if (symptomDescribe != null ? !symptomDescribe.equals(that.symptomDescribe) : that.symptomDescribe != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = symptomId;
        result = 31 * result + diseaseId;
        result = 31 * result + (symptomName != null ? symptomName.hashCode() : 0);
        result = 31 * result + (symptomOptionA != null ? symptomOptionA.hashCode() : 0);
        result = 31 * result + (symptomOptionB != null ? symptomOptionB.hashCode() : 0);
        result = 31 * result + (symptomOptionC != null ? symptomOptionC.hashCode() : 0);
        result = 31 * result + (symptomOptionD != null ? symptomOptionD.hashCode() : 0);
        result = 31 * result + (symptomDescribe != null ? symptomDescribe.hashCode() : 0);
        return result;
    }
}
