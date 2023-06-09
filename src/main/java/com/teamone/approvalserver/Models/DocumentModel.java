package com.teamone.approvalserver.Models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "documents", schema = "doc_approval")
public class DocumentModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "document_id", nullable = false)
    private int documentId;
    @Basic
    @Column(name = "customer", nullable = false, length = 30)
    private String customer;
    @Basic
    @Column(name = "project", nullable = false, length = 30)
    private String project;
    @Basic
    @Column(name = "name", nullable = false, length = 30)
    private String name;
    @Basic
    @Column(name = "description", nullable = true, length = -1)
    private String description;
    @Basic
    @Column(name = "document_link", nullable = true, length = 255)
    private String documentLink;
    @Basic
    @Column(name = "current_approver", nullable = true)
    private Integer currentApprover;
    @Basic
    @Column(name = "originator", nullable = true)
    private Integer originator;

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDocumentLink() {
        return documentLink;
    }

    public void setDocumentLink(String documentLink) {
        this.documentLink = documentLink;
    }

    public Integer getCurrentApprover() {
        return currentApprover;
    }

    public void setCurrentApprover(Integer currentApprover) {
        this.currentApprover = currentApprover;
    }

    public Integer getOriginator() {
        return originator;
    }

    public void setOriginator(Integer originator) {
        this.originator = originator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentModel that = (DocumentModel) o;
        return documentId == that.documentId && Objects.equals(customer, that.customer) && Objects.equals(project, that.project) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(documentLink, that.documentLink) && Objects.equals(currentApprover, that.currentApprover) && Objects.equals(originator, that.originator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentId, customer, project, name, description, documentLink, currentApprover, originator);
    }
}
