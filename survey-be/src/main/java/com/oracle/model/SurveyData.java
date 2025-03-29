package com.oracle.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public class SurveyData {

    @NotNull
    private Integer age;

    @NotNull
    private Gender gender;

    @NotEmpty
    private String region;

    @NotEmpty
    private String surveyID;

    @Min(1)
    @Max(5)
    @NotNull
    private Integer score;

    public SurveyData() {
    }

    public SurveyData(Integer age, Gender gender, String region, String surveyID, Integer score) {
        this.age = age;
        this.gender = gender;
        this.region = region;
        this.surveyID = surveyID;
        this.score = score;
    }


    @JsonProperty
    public Integer getAge() {
        return age;
    }

    @JsonProperty
    public void setAge(Integer age) {
        this.age = age;
    }

    @JsonProperty
    public Gender getGender() {
        return gender;
    }

    @JsonProperty
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @JsonProperty
    public String getRegion() {
        return region;
    }

    @JsonProperty
    public void setRegion(String region) {
        this.region = region;
    }

    @JsonProperty
    public String getSurveyID() {
        return surveyID;
    }

    @JsonProperty
    public void setSurveyID(String surveyID) {
        this.surveyID = surveyID;
    }

    @JsonProperty
    public Integer getScore() {
        return score;
    }

    @JsonProperty
    public void setScore(Integer score) {
        this.score = score;
    }

    public enum Gender {
        male, female, other
    }
}