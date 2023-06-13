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
       Optional <UserModel> userModelOptional = userRepository.findById(userId);
        if (!userModelOptional.isPresent()) {
// TODO
        }
        UserModel userModel = userModelOptional.get();

       return documentRepository.findAllByChainList_userId(userModel);
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
        Optional <UserModel> userModelOptional = userRepository.findById(userId);
        if (!userModelOptional.isPresent()) {
// TODO
        }
        UserModel userModel = userModelOptional.get();
        //set chain (approved = true, timestamp = now)
        ChainModel currentChain = chainRepository.getByDocumentIdAndUserId(currentDoc, userModel);
        currentChain.setApproved(true);
        currentChain.setTimeStamp(new Timestamp(System.currentTimeMillis()));
        chainRepository.save(currentChain);

        //set document current user = next in chain
        currentDoc.UpdateToNextApprover();
        documentRepository.save(currentDoc);
    }

    public void addDocument(DocumentModel documentModel) {

        documentRepository.save(documentModel);

    }
}
