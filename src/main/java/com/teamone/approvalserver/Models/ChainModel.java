package com.teamone.approvalserver.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "chain", schema = "mydb")
public class ChainModel {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name ="chain_id", nullable = false )
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "document_id", nullable = false)
    private DocumentModel documentId;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel userId;

    @Basic
    @Column(name = "position", nullable = false)
    private int position;
    @Basic
    @Column(name = "approved", nullable = true)
    private boolean approved;

    @Basic
    @Column(name="comment", nullable = true)
    private String comment;


    @Basic
    @Column(name = "time_stamp", nullable = true)
    private Timestamp timeStamp;

    public ChainModel(int id, DocumentModel documentId, UserModel userId, int position, boolean approved, String comment, Timestamp timeStamp) {
        this.id = id;
        this.documentId = documentId;
        this.userId = userId;
        this.position = position;
        this.approved = approved;
        this.comment = comment;
        this.timeStamp = timeStamp;
    }

    public ChainModel(){}

    public void setId(int id) {this.id = id;}

    public int getId() {return id;}

    @JsonIgnore
    public DocumentModel getDocumentId() {
        return documentId;
    }


    public void setUserId( UserModel userId) {
        this.userId = userId;
    }


    public UserModel getUserId() {return userId;}

    @JsonIgnore
    public void setDocumentId(DocumentModel documentId) {
        this.documentId = documentId;
    }



    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean getApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChainModel that = (ChainModel) o;
        return documentId == that.documentId && userId == that.userId && position == that.position && Objects.equals(approved, that.approved) && Objects.equals(timeStamp, that.timeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentId, userId, position, approved, timeStamp);
    }


}
