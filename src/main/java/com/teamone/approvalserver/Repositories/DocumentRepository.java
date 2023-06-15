package com.teamone.approvalserver.Repositories;

import com.teamone.approvalserver.Models.DocumentModel;
import com.teamone.approvalserver.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * This Repository Inserts document objects into the database.
 */
@Repository
public interface DocumentRepository extends JpaRepository<DocumentModel, Integer> {
    List<Optional<DocumentModel>> findAllByChainList_userId(UserModel userId);
    List<Optional<DocumentModel>> findAllByOriginator(int userId);
    List<Optional<DocumentModel>> findAllByCurrentApprover(int userId);

}
