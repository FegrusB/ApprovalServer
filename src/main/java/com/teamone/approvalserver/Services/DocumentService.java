package com.teamone.approvalserver.Services;

import com.teamone.approvalserver.Models.ChainModel;
import com.teamone.approvalserver.Models.DocumentModel;
import com.teamone.approvalserver.Services.Email.EmailDetails;
import com.teamone.approvalserver.Models.UserModel;
import com.teamone.approvalserver.Repositories.ChainRepository;
import com.teamone.approvalserver.Repositories.DocumentRepository;
import com.teamone.approvalserver.Repositories.UserRepository;
import com.teamone.approvalserver.Services.Email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
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
    private final EmailService emailService;
    @Autowired
    public DocumentService(DocumentRepository documentRepository, ChainRepository chainRepository, UserRepository userRepository,EmailService emailService) {
        this.documentRepository = documentRepository;
        this.chainRepository = chainRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
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
    public void approveDocument(Integer userId, Integer documentId){
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

        ChainModel chain = null;

        for (ChainModel chainLoop: userModel.getChainList()){
            if(chainLoop.getDocumentId().getDocumentId() == documentId){
                chain = chainLoop;
            }
        }
        chain.setApproved(true);
        chain.setTimeStamp(new Timestamp(System.currentTimeMillis()));

        chainRepository.save(chain);

        //set document current user = next in chain
        if(currentDoc.getChainList().get( (currentDoc.getChainList().size()) - 1).getUserId().equals(userId)) {
            UserModel originator = userRepository.getReferenceById(currentDoc.getOriginator());
            File auditReport = null;
            try {
                auditReport = ResourceUtils.getFile("classpath:V1ApprovedAudit.pdf");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            emailService.sendEmail(new EmailDetails(originator.getEmail(),currentDoc.getProject() + "-" + currentDoc.getCustomer() + "-" + currentDoc.getName(),"Audit document, see attached",auditReport));
            currentDoc.setFinished(true);
        } else {
            currentDoc.UpdateToNextApprover();
            UserModel next = userRepository.findById(currentDoc.getCurrentApprover()).get();
            emailService.sendEmail(new EmailDetails(next.getEmail(),currentDoc.getProject() + "-" + currentDoc.getCustomer() + "-" + currentDoc.getName(),"This document is awaiting your approval"));
            documentRepository.save(currentDoc);
        }


    }
  
    /**
     * This method deletes a document from the document table by documentId.
     * @param documentId
     */
    public void deleteDocument(Integer documentId) {
        //get the document model out of the database
        DocumentModel documentModel = documentRepository.getReferenceById(documentId);

        //itterate through the chainlist and delete each chain entity
        for(ChainModel chainModel : documentModel.getChainList()) {
            chainRepository.delete(chainModel);
        }
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
