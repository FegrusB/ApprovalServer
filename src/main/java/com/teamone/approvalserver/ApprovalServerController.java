package com.teamone.approvalserver;

import com.teamone.approvalserver.Models.DocumentModel;
import com.teamone.approvalserver.Services.DocumentService;
import com.teamone.approvalserver.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/V1Approved")
public class ApprovalServerController {

    private final DocumentService documentService;
    private final UserService userService;

    @Autowired
    public ApprovalServerController(UserService userService, DocumentService documentService) {
        this.userService = userService;
        this.documentService = documentService;
    }

    @GetMapping("/docs/docInfo")
    public Optional<DocumentModel> getDocumentInfo(@RequestParam Integer documentId) {
            return documentService.getDocumentInfo(documentId);
    }

    @GetMapping("/docs/byUser")
    public List<Optional<DocumentModel>> getDocumentsByUser(@RequestParam Integer userId) {
        List<Optional<DocumentModel>> out  = documentService.getDocumentsByUser(userId);
        return out;
    }

}
