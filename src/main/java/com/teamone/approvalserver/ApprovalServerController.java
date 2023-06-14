package com.teamone.approvalserver;

import com.teamone.approvalserver.Models.DocumentModel;
import com.teamone.approvalserver.Models.UserModel;
import com.teamone.approvalserver.Services.ChainService;
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
    private final ChainService chainService;
    @Autowired
    public ApprovalServerController(UserService userService, DocumentService documentService, ChainService chainService) {
        this.userService = userService;
        this.documentService = documentService;
        this.chainService = chainService;
    }

    @GetMapping("/docs/docInfo")
    public Optional<DocumentModel> getDocumentInfo(@RequestParam Integer documentId) {
            return documentService.getDocumentInfo(documentId);
    }

    @GetMapping("/docs/byUser")
    public List<Optional<DocumentModel>> getDocumentsByUser(@RequestParam Integer userId) {
        return documentService.getDocumentsByUser(userId);
    }

    @PutMapping("/approval")
    public void approveDocument(@RequestParam Integer userId, @RequestParam Integer documentId) {
        documentService.approveDocument(userId, documentId);
    }

    @DeleteMapping("/delete/{documentId}")
    public void deleteDocument(@PathVariable(name="documentId") Integer documentId) {
        documentService.deleteDocument(documentId);
    }
    /**
     * This Method will add a document to approval process including it's chain of approvers.
     * @param documentModel
     */
    @PostMapping("/addDocument")
    public void addDocument(@RequestBody DocumentModel documentModel) {
        documentService.addDocument(documentModel);
    }

    @GetMapping("/chain/users")
    public List<UserModel> getUsersByDocuments(@RequestParam Integer documentId) {
        return chainService.getUsersByDocument(documentId);
    }
    @GetMapping("/docs/byOriginator")
    public List<Optional<DocumentModel>> getDocumentsByOriginator(@RequestParam Integer originator) {
        return documentService.getDocumentsByOriginator(originator);
    }


}
