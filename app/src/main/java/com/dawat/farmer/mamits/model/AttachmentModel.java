package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AttachmentModel implements Serializable {

    @SerializedName("id")
    String id;

    @SerializedName("fk_farm_comment")
    String fk_farm_comment;

    @SerializedName("attachment")
    String attachment;

    @SerializedName("file_type")
    String file_type;

    @SerializedName("created_at")
    String created_at;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFk_farm_comment() {
        return fk_farm_comment;
    }

    public void setFk_farm_comment(String fk_farm_comment) {
        this.fk_farm_comment = fk_farm_comment;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", fk_farm_comment='" + fk_farm_comment + '\'' +
                ", attachment='" + attachment + '\'' +
                ", file_type='" + file_type + '\'' +
                ", created_at='" + created_at + '\'' +
                '}';
    }
}
