CREATE DATABASE form_builder;

USE form_builder;

CREATE TABLE Users (
    id int NOT NULL AUTO_INCREMENT,
    emailId varchar(255),
    isAdmin boolean,
    createdAt datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updatedAt datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE SourceFormTemplates (
    id int NOT NULL AUTO_INCREMENT,
    type varchar(255),
    fields json,
    createdAt datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updatedAt datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE Sources (
    id int NOT NULL AUTO_INCREMENT,
    sourceFormTemplateId int NOT NULL,
    userId int NOT NULL,
    data json,
    createdAt datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updatedAt datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (sourceFormTemplateId) REFERENCES SourceFormTemplates(id),
    FOREIGN KEY (userId) REFERENCES Users(id)
);

INSERT INTO `form_builder`.`users` (`emailId`, `isAdmin`) VALUES ('u1@rudderstack.com', '1');

INSERT INTO `form_builder`.`users` (`emailId`, `isAdmin`) VALUES ('u2@myntra.com', '0');
