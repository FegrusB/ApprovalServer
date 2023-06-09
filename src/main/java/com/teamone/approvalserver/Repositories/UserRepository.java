package com.teamone.approvalserver.Repositories;

import com.teamone.approvalserver.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
}
