package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SrpQuestionModel implements Serializable {

    @SerializedName("subquestion_id")
    String subquestion_id;

    @SerializedName("question_id")
    String question_id;

    @SerializedName("question_english")
    String question_english;

    @SerializedName("question_hindi")
    String question_hindi;

    @SerializedName("point_for_yes")
    String point_for_yes;

    @SerializedName("point_for_no")
    String point_for_no;


    public String getSubquestion_id() {
        return subquestion_id;
    }

    public void setSubquestion_id(String subquestion_id) {
        this.subquestion_id = subquestion_id;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getQuestion_english() {
        return question_english;
    }

    public void setQuestion_english(String question_english) {
        this.question_english = question_english;
    }

    public String getQuestion_hindi() {
        return question_hindi;
    }

    public void setQuestion_hindi(String question_hindi) {
        this.question_hindi = question_hindi;
    }

    public String getPoint_for_yes() {
        return point_for_yes;
    }

    public void setPoint_for_yes(String point_for_yes) {
        this.point_for_yes = point_for_yes;
    }

    public String getPoint_for_no() {
        return point_for_no;
    }

    public void setPoint_for_no(String point_for_no) {
        this.point_for_no = point_for_no;
    }

    @Override
    public String toString() {
        return "{" +
                "subquestion_id='" + subquestion_id + '\'' +
                ", question_id='" + question_id + '\'' +
                ", question_english='" + question_english + '\'' +
                ", question_hindi='" + question_hindi + '\'' +
                ", point_for_yes='" + point_for_yes + '\'' +
                ", point_for_no='" + point_for_no + '\'' +
                '}';
    }
}
