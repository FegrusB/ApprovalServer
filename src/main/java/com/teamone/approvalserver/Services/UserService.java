package com.teamone.approvalserver.Services;

import com.teamone.approvalserver.Models.UserModel;
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
    private final  UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Optional<UserModel>> getUsersByDocument(Integer documentId) {

        return userRepository.findAllByChainList_documentId(documentId);
    }
}
