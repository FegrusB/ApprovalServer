package com.teamone.approvalserver.Models;

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

    @Basic
    @Column(name = "document_id", nullable = false)
    private int documentId;
    @Basic
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Basic
    @Column(name = "position", nullable = false)
    private int position;
    @Basic
    @Column(name = "approved", nullable = true)
    private boolean approved;
    @Basic
    @Column(name = "time_stamp", nullable = true)
    private Timestamp timeStamp;


    public void setId(int id) {this.id = id;}

    public int getId() {return id;}

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
