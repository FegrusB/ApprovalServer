Feature: DocInfo
  The DocInfo endpoint returns the document model of a given documentID.

  Scenario:
  //   Given the collection of documents
  //      | document_id | customer | project        | name | description           | document_link                                                                                                                                     | current_approver | originator | finished |
  //      | 1           | HMRC     | Springboot-app | doc1 | "Value: £400,000,000Z | sharepoint.com/test                                                                                                                               | 4                | 3          | 0        |
  //      | 2           | DWP      | AWS Migration  | ToR  | "Value: £200,000,000  | https://techtalentacademy524.sharepoint.com/:w:/s/Version1-NewcastleAcademy2-ProjectTeamA/ER1z5bgQgJNCov1ynRq3mHsBMhRzl_DC8h_TsxSjK6r0Yw?e=GP2o4V | 4                | 3          |0

    When Document_id 1 is passed in to retrieve the document model

    Then The document detail is retrieved
      | document_id | customer | project        | name | description           | document_link                                                                                                                                     | current_approver | originator | finished |
      | 1           | HMRC     | Springboot-app | doc1 | "Value: £400,000,000Z | sharepoint.com/test                                                                                                                               | 4                | 3          | 0        |
