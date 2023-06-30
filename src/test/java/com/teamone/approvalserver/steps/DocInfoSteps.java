package com.teamone.approvalserver.steps;

import com.teamone.approvalserver.Models.DocumentModel;
import com.teamone.approvalserver.Repositories.DocumentRepository;
import com.teamone.approvalserver.CucumberBootstrap;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DocInfoSteps extends CucumberBootstrap {

    @Autowired
    private DocumentRepository documentRepository;

    @DataTableType
    public DocumentModel documentModelEntry(Map<String, String> entry){
        return new DocumentModel(Integer.parseInt(entry.get("document_id")),entry.get("customer"),entry.get("project"),
                entry.get("name"),entry.get("description"),entry.get("document_link"),Integer.parseInt(entry.get("current_approver")),
                Integer.parseInt(entry.get("originator")),Boolean.parseBoolean(entry.get("finished")));
    }

    @When("Document_id {int} is passed in to retrieve the document model")
    public void get_document_model_by_id(int doumentId){
        ResponseEntity<DocumentModel> responseEntity = testRestTemplate.getForEntity("/V1Approved/docs/docInfo?documentId=" + doumentId,DocumentModel.class,doumentId);
        assertNotNull(responseEntity.getBody());
        assertEquals(responseEntity.getBody().getDocumentId(), doumentId);
    }

    @Then("^The document detail is retrieved")
    public void document_model_retrieved(DataTable dataTable){
        dataTable.asList(DocumentModel.class).forEach(documentModel -> {
            Optional<DocumentModel> documentModelOptional = documentRepository.findById(documentModel.getDocumentId());
            if(documentModelOptional.isPresent()){
                assertEquals(documentModelOptional.get().getName(),documentModel.getName());
                assertEquals(documentModelOptional.get().getCurrentApprover(), documentModel.getCurrentApprover());
            }
        });
    }

}
