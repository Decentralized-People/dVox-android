package com.dpearth.dvox.smartcontract;

import java.math.BigInteger;
import java.util.Objects;

//!!! I need to annotate this class later
public class Comment {

    private BigInteger id;
    private String commentAuthor;
    private String commentMessage;


    private Boolean commentBan;

    public Comment() {
        this(null, null, null, null);
    }

    public Comment(BigInteger id, String commentAuthor, String commentMessage, Boolean commentBan) {
        this.id = id;
        this.commentAuthor = commentAuthor;
        this.commentMessage = commentMessage;
        this.commentBan = commentBan;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getCommentAuthor() {
        return commentAuthor;
    }

    public void setCommentAuthor(String commentAuthor) {
        this.commentAuthor = commentAuthor;
    }

    public String getCommentMessage() {
        return commentMessage;
    }

    public void setCommentMessage(String commentMessage) {
        this.commentMessage = commentMessage;
    }

    public Boolean getCommentBan() {
        return commentBan;
    }

    public void setCommentBan(Boolean commentBan) {
        this.commentBan = commentBan;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", commentAuthor='" + commentAuthor + '\'' +
                ", commentMessage='" + commentMessage + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) && Objects.equals(commentAuthor, comment.commentAuthor) && Objects.equals(commentMessage, comment.commentMessage);
    }
}
