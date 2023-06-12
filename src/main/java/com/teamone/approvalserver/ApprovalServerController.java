package com.teamone.approvalserver;

import com.teamone.approvalserver.Models.DocumentModel;
import com.teamone.approvalserver.Services.DocumentService;
import com.teamone.approvalserver.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/V1Approved")
public class ApprovalServerController {

    private final DocumentService documentService;

    @GetMapping("/{userId}")
    public List<DocumentModel> getDocumentsByUser(@PathVariable(value="userId") Integer userId) {
        return documentService.getDocumentsByUser(userId);
    }



    UserService userService;

    @Autowired
    public ApprovalServerController(UserService userService, DocumentService documentService) {

        this.userService = userService;
        this.documentService = documentService;
    }
}
