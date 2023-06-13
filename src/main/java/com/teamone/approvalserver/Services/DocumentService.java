package com.teamone.approvalserver.Services;

import com.teamone.approvalserver.Models.ChainModel;
import com.teamone.approvalserver.Models.DocumentModel;
import com.teamone.approvalserver.Repositories.ChainRepository;
import com.teamone.approvalserver.Repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * This Service constructs Document and chain objects to be Inserted into the database.
 */
@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final ChainRepository chainRepository;

    @Autowired
    public DocumentService(DocumentRepository documentRepository, ChainRepository chainRepository) {
        this.documentRepository = documentRepository;
        this.chainRepository = chainRepository;
    }

    /**
     * This method returns all of the information about a specific document
     * @param documentId
     * @return
     */
    public Optional<DocumentModel> getDocumentInfo(Integer documentId) {
       return documentRepository.findById(documentId);
    }

    /**
     * This method returns the list of documents associated to a specific user
     * @param userId
     * @return
     */
    public List<Optional<DocumentModel>> getDocumentsByUser(Integer userId) {
       return documentRepository.findAllByChainList_userId(userId);
    }

    /**
     * This method will submit a users approval and update the chain and document tables in the database
     * @param userId
     * @param documentId
     */
    public void approveDocument(Integer userId, Integer documentId) {
        //check user table for position
        Optional<DocumentModel> currentDocOptional = documentRepository.findById(documentId);
        if (!currentDocOptional.isPresent()) {
//          TODO
        }

        DocumentModel currentDoc = currentDocOptional.get();
        if (userId.equals(currentDoc.getCurrentApprover())) {
//          TODO
        }

        //set chain (approved = true, timestamp = now)
        ChainModel currentChain = chainRepository.getByDocumentIdAndUserId(currentDoc, userId);
        currentChain.setApproved(true);
        currentChain.setTimeStamp(new Timestamp(System.currentTimeMillis()));
        chainRepository.save(currentChain);

        //set document current user = next in chain
        currentDoc.UpdateToNextApprover();
        documentRepository.save(currentDoc);
    }

    /**
     * This method deletes a document from the document table by documentId.
     * @param documentId
     */
    public void deleteDocument(Integer documentId) {
        documentRepository.deleteById(documentId);
    }
}
