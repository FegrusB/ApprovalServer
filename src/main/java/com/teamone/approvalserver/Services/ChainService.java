package com.teamone.approvalserver.Services;

import com.teamone.approvalserver.Models.ChainModel;
import com.teamone.approvalserver.Models.DocumentModel;
import com.teamone.approvalserver.Models.UserModel;
import com.teamone.approvalserver.Repositories.ChainRepository;
import com.teamone.approvalserver.Repositories.UserRepository;
import com.teamone.approvalserver.Repositories.DocumentRepository;
import com.teamone.approvalserver.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class ChainService {

    private final DocumentRepository documentRepository;
    private final UserRepository userRepository;
    private final ChainRepository chainRepository;



    @Autowired
    public ChainService(DocumentRepository documentRepository, UserRepository userRepository, ChainRepository chainRepository) {
        this.documentRepository = documentRepository;
        this.userRepository = userRepository;
        this.chainRepository = chainRepository;

    }

    public List<UserModel> getUsersByDocument(Integer documentId) {
        List<UserModel> users = new ArrayList<>();
        //check user table for position
        Optional<DocumentModel> currentDocOptional = documentRepository.findById(documentId);
        if (!currentDocOptional.isPresent()) {
// TODO
        }

        DocumentModel currentDoc = currentDocOptional.get();

        for(ChainModel chainModel: currentDoc.getChainList()){
            users.add(chainModel.getUserId());
        }
        return users;
    }

    public void addComment(Integer userId, Integer documentId, String comment){
        UserModel userModel = userRepository.getReferenceById(userId);
        DocumentModel documentModel = documentRepository.getReferenceById(documentId);
        ChainModel chainModel = chainRepository.getByDocumentIdAndUserId(documentModel, userModel);

        chainModel.setComment(comment);
        chainRepository.save(chainModel);
    }

}
