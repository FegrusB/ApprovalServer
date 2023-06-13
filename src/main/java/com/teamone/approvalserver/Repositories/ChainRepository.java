package com.teamone.approvalserver.Repositories;

import com.teamone.approvalserver.Models.ChainModel;
import com.teamone.approvalserver.Models.DocumentModel;
import com.teamone.approvalserver.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This Repository Inserts chain objects into the database.
 */
@Repository
public interface ChainRepository extends JpaRepository<ChainModel, Integer> {
    ChainModel getByDocumentIdAndUserId(DocumentModel documentId, UserModel userModel);



}
