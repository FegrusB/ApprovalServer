package com.teamone.approvalserver.Services;

import com.teamone.approvalserver.Models.DocumentModel;
import com.teamone.approvalserver.Models.UserModel;
import com.teamone.approvalserver.Repositories.DocumentRepository;
import com.teamone.approvalserver.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This Service User objects to be inserted into the database.
 */
@Service
public class UserService {
    private final DocumentRepository documentRepository;
    @Autowired
    public UserService(DocumentRepository documentRepository) {

        this.documentRepository = documentRepository;
    }

}
