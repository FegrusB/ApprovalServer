package com.teamone.approvalserver.Models;

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

    public UserModel(){}

    public UserModel(Integer userId) {this.userId = userId;}

    public UserModel(int userId, String userName, String email, List<ChainModel> chainList) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.chainList = chainList;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel that = (UserModel) o;
        return userId == that.userId && Objects.equals(userName, that.userName) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, email);
    }
}
