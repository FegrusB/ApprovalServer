package com.teamone.approvalserver.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "mydb")
public class UserModel {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Basic
    @Column(name = "user_name", nullable = false, length = 100)
    private String userName;
    @Basic
    @Column(name = "email", nullable = true, length = 100)
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userId")
    private List<ChainModel> chainList;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    @JsonIgnore
    public List<ChainModel> getChainList() {
        return chainList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel that = (UserModel) o;
        return userId == that.userId && Objects.equals(userName, that.userName) && Objects.equals(email, that.email);
    }

    public boolean equals(Integer userId) {
        return userId == getUserId();
    }
    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, email);
    }
}
