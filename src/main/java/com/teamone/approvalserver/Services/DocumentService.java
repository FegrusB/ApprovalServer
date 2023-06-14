package com.teamone.approvalserver.Services;

import com.teamone.approvalserver.Models.ChainModel;
import com.teamone.approvalserver.Models.DocumentModel;
import com.teamone.approvalserver.Models.UserModel;
import com.teamone.approvalserver.Repositories.ChainRepository;
import com.teamone.approvalserver.Repositories.DocumentRepository;
import com.teamone.approvalserver.Repositories.UserRepository;
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
    private final UserRepository userRepository;
    @Autowired
    public DocumentService(DocumentRepository documentRepository, ChainRepository chainRepository, UserRepository userRepository) {
        this.documentRepository = documentRepository;
        this.chainRepository = chainRepository;
        this.userRepository = userRepository;
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
       UserModel userModel = userRepository.findById(userId).get();
       return documentRepository.findAllByChainList_userId(userModel);
    }


    /**
     * This method will submit a users approval and update the chain and document tables in the database
     * @param userId
     * @param documentId
     */
    public void approveDocument(Integer userId, Integer documentId) {

        //check user table for position
        DocumentModel currentDoc = documentRepository.findById(documentId).get();
        UserModel userModel = userRepository.findById(userId).get();

        //set chain (approved = true, timestamp = now)
        ChainModel currentChain = chainRepository.getByDocumentIdAndUserId(currentDoc, userModel);
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
        //get the document model out of the database
        DocumentModel documentModel = documentRepository.getReferenceById(documentId);

        //itterate through the chainlist and delete each chain entity
        chainRepository.deleteAll(documentModel.getChainList());
        documentRepository.delete(documentModel);
     }
  
    public void addDocument(DocumentModel documentModel) {
        //save document to the database and auto generate an ID
        documentRepository.save(documentModel);

        //itterate through the chainList adding document ID and then adding to database
        for(ChainModel chainModel : documentModel.getChainList()) {
            chainModel.setDocumentId(documentModel);
            chainRepository.save(chainModel);
        }
    }

    /**
     * This method gets all documents created by an originator
     * @param originator
     * @return
     */
    public List<Optional<DocumentModel>> getDocumentsByOriginator(Integer originator) {
        return documentRepository.findAllByOriginator(originator);
    }

}
