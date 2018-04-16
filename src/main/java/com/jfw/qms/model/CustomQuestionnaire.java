package com.jfw.qms.model;

public class CustomQuestionnaire {
    private Integer questionnaire_id;
    private String questionnaire_name;
    private Integer isEncrypt;

    public Integer getQuestionnaire_id() {
        return questionnaire_id;
    }

    public void setQuestionnaire_id(Integer questionnaire_id) {
        this.questionnaire_id = questionnaire_id;
    }

    public String getQuestionnaire_name() {
        return questionnaire_name;
    }

    public void setQuestionnaire_name(String questionnaire_name) {
        this.questionnaire_name = questionnaire_name;
    }

    public Integer getIsEncrypt() {
        return isEncrypt;
    }

    public void setIsEncrypt(Integer isEncrypt) {
        this.isEncrypt = isEncrypt;
    }

    @Override
    public String toString() {
        return "CustomQuestionnaire{" +
                "questionnaire_id=" + questionnaire_id +
                ", questionnaire_name='" + questionnaire_name + '\'' +
                ", isEncrypt=" + isEncrypt +
                '}';
    }
}
