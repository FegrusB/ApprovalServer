package com.teamone.approvalserver.Services;

import com.teamone.approvalserver.Models.DocumentModel;
import com.teamone.approvalserver.Repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This Service constructs Document and chain objects to be Inserted into the database.
 */
@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

   public Optional<DocumentModel> getDocumentInfo(Integer documentId) {
       return documentRepository.findById(documentId);
   }

   public List<Optional<DocumentModel>> getDocumentsByUser(Integer userId) {
       return documentRepository.findAllByChainList_userId(userId);
    }
}
