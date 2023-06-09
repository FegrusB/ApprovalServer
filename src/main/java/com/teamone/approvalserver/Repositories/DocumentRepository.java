package com.teamone.approvalserver.Repositories;

import com.teamone.approvalserver.Models.DocumentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentModel, Integer> {
}
