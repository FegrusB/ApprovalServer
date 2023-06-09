package com.teamone.approvalserver.Repositories;

import com.teamone.approvalserver.Models.DocumentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This Repository Inserts document objects into the database.
 */
@Repository
public interface DocumentRepository extends JpaRepository<DocumentModel, Integer> {
}
