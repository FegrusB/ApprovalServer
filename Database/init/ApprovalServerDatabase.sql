SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

USE doc_approval;

CREATE TABLE users (
    user_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(100) NOT NULL,
    email VARCHAR(100)
);

CREATE TABLE documents (
    document_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    customer VARCHAR(30) NOT NULL,
    project VARCHAR(30) NOT NULL,
    name VARCHAR(30) NOT NULL,
    description TEXT(500),
    document_link VARCHAR(255),
    current_approver INT,
    originator int,
    Foreign Key (originator) REFERENCES  users (user_id),
    FOREIGN KEY (current_approver) REFERENCES users (user_id)
);



CREATE TABLE chain (
    chain_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    document_id INT NOT NULL,
    user_id INT NOT NULL,
    position INT NOT NULL,
    approved BOOLEAN,
    time_stamp DATETIME,
    FOREIGN KEY (document_id) REFERENCES documents (document_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);

INSERT INTO documents (customer, project, name, description, document_link) VALUES ('HMRC', 'Springboot-app', 'doc1', 'A very important document', 'sharepoint.com/test');
INSERT INTO documents (customer, project, name, description, document_link) VALUES ('DWP', 'AWS Migration','Migration Plan','Overview of migration strategy', 'sharepoint.com/test');
INSERT INTO documents (customer, project, name, description, document_link) VALUES ('NASA', 'Machine learning project','Project Overview','Overview of requirements and timeline of project', 'sharepoint.com/test');
INSERT INTO documents (customer, project, name, description, document_link) VALUES ('V1 Newcastle', 'New office','Tenancy Agreement','Tenancy Contract for new office', 'sharepoint.com/test');

INSERT INTO users (user_name, email) VALUES ('Andrew H','Andrew.H@version1.com');
INSERT INTO users (user_name, email) VALUES ('Arsalan A','Arsalan.A@version1.com');
INSERT INTO users (user_name, email) VALUES ('Connor O','Connor.O@version1.com');
INSERT INTO users (user_name, email) VALUES ('Dan P','Dan.P@version1.com');
INSERT INTO users (user_name, email) VALUES ('Eunjung S','Eunjung.S@version1.com');
INSERT INTO users (user_name, email) VALUES ('Fergus B','Fergus.B@version1.com');
INSERT INTO users (user_name, email) VALUES ('Olfa K','Olfa.K@version1.com');
INSERT INTO users (user_name, email) VALUES ('Sumaiya A','Sumaiya.A@version1.com');
INSERT INTO users (user_name, email) VALUES ('Vasi C','Vasi.C@version1.com');

INSERT INTO chain (document_id, user_id, position) VALUES(1,1,1);
INSERT INTO chain (document_id, user_id, position) VALUES(1,4,2);
INSERT INTO chain (document_id, user_id, position) VALUES(1,9,3);
INSERT INTO chain (document_id, user_id, position) VALUES(1,3,4);

INSERT INTO chain (document_id, user_id, position) VALUES(2,6,1);
INSERT INTO chain (document_id, user_id, position) VALUES(2,5,2);
INSERT INTO chain (document_id, user_id, position) VALUES(2,8,3);

INSERT INTO chain (document_id, user_id, position) VALUES(3,2,1);

INSERT INTO chain (document_id, user_id, position) VALUES(4,1,1);
INSERT INTO chain (document_id, user_id, position) VALUES(4,2,2);
INSERT INTO chain (document_id, user_id, position) VALUES(4,3,3);
INSERT INTO chain (document_id, user_id, position) VALUES(4,4,4);
INSERT INTO chain (document_id, user_id, position) VALUES(4,5,5);
INSERT INTO chain (document_id, user_id, position) VALUES(4,6,6);
INSERT INTO chain (document_id, user_id, position) VALUES(4,7,7);
INSERT INTO chain (document_id, user_id, position) VALUES(4,8,8);
INSERT INTO chain (document_id, user_id, position) VALUES(4,9,9);
