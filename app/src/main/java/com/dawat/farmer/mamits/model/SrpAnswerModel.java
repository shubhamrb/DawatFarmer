package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SrpAnswerModel implements Serializable {

    @SerializedName("question_id")
    String question_id;

    @SerializedName("point_for_yes")
    String point_for_yes;

    @SerializedName("point_for_no")
    String point_for_no;

    @SerializedName("question")
    String question;

    @SerializedName("subquestion")
    List<SubAnswer> subquestion;


    public static class SubAnswer implements Serializable {
        @SerializedName("id")
        String id;

        @SerializedName("report_id")
        String report_id;

        @SerializedName("question_id")
        String question_id;

        @SerializedName("subquestion_id")
        String subquestion_id;

        @SerializedName("point_for_yes")
        String point_for_yes;

        @SerializedName("point_for_no")
        String point_for_no;

        @SerializedName("question")
        String question;

        @SerializedName("subquestion")
        String subquestion;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getReport_id() {
            return report_id;
        }

        public void setReport_id(String report_id) {
            this.report_id = report_id;
        }

        public String getQuestion_id() {
            return question_id;
        }

        public void setQuestion_id(String question_id) {
            this.question_id = question_id;
        }

        public String getSubquestion_id() {
            return subquestion_id;
        }

        public void setSubquestion_id(String subquestion_id) {
            this.subquestion_id = subquestion_id;
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

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getSubquestion() {
            return subquestion;
        }

        public void setSubquestion(String subquestion) {
            this.subquestion = subquestion;
        }

        @Override
        public String toString() {
            return "{" +
                    "id='" + id + '\'' +
                    ", report_id='" + report_id + '\'' +
                    ", question_id='" + question_id + '\'' +
                    ", subquestion_id='" + subquestion_id + '\'' +
                    ", point_for_yes='" + point_for_yes + '\'' +
                    ", point_for_no='" + point_for_no + '\'' +
                    ", question='" + question + '\'' +
                    ", subquestion='" + subquestion + '\'' +
                    '}';
        }
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
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

    public List<SubAnswer> getSubquestion() {
        return subquestion;
    }

    public void setSubquestion(List<SubAnswer> subquestion) {
        this.subquestion = subquestion;
    }

    @Override
    public String toString() {
        return "{" +
                "question_id='" + question_id + '\'' +
                ", point_for_yes='" + point_for_yes + '\'' +
                ", point_for_no='" + point_for_no + '\'' +
                ", question='" + question + '\'' +
                ", subquestion=" + subquestion +
                '}';
    }
}
