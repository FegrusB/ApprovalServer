package com.teamone.approvalserver.Repositories;

import com.teamone.approvalserver.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * This Repository Inserts user objects into the database.
 */
public interface UserRepository extends JpaRepository<UserModel, Integer> {

}
