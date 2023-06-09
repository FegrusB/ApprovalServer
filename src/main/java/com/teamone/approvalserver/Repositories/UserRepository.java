package com.teamone.approvalserver.Repositories;

import com.teamone.approvalserver.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This Repository Inserts user objects into the database.
 */
public interface UserRepository extends JpaRepository<UserModel, Integer> {
}
