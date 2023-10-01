package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CommentsModel implements Serializable {

    @SerializedName("comment")
    List<CommentDataModel> comment;

    @SerializedName("attachment")
    List<AttachmentModel> attachment;

    public List<CommentDataModel> getComment() {
        return comment;
    }

    public void setComment(List<CommentDataModel> comment) {
        this.comment = comment;
    }

    public List<AttachmentModel> getAttachment() {
        return attachment;
    }

    public void setAttachment(List<AttachmentModel> attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "{" +
                "comment=" + comment +
                ", attachment=" + attachment +
                '}';
    }
}
