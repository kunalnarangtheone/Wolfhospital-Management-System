SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS Beds;
DROP TABLE IF EXISTS BillingRecords;
DROP TABLE IF EXISTS BillingStatements;
DROP TABLE IF EXISTS Patients;
DROP TABLE IF EXISTS StaffMembers;
DROP TABLE IF EXISTS Tests;
DROP TABLE IF EXISTS Treatments;
DROP TABLE IF EXISTS Visits;
DROP TABLE IF EXISTS Wards;
SET FOREIGN_KEY_CHECKS = 1;



CREATE TABLE IF NOT EXISTS StaffMembers (
    StaffID           INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Name              VARCHAR(255) NOT NULL,
    DOB               DATE         NOT NULL,
    Gender            VARCHAR(255) NOT NULL,
    JobTitle          VARCHAR(255) NOT NULL,
    ProfessionalTitle VARCHAR(255) NOT NULL,
    Department        VARCHAR(255) NOT NULL,
    PhoneNumber       VARCHAR(15)  NOT NULL,
    Address           VARCHAR(255) NOT NULL
);


CREATE TABLE IF NOT EXISTS Patients (
    PatientID               INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    SSN                     CHAR(9)               UNIQUE,
    Name                    VARCHAR(255) NOT NULL,
    DOB                     DATE         NOT NULL,
    Gender                  VARCHAR(255) NOT NULL,
    PhoneNumber             VARCHAR(15)  NOT NULL,
    Address                 VARCHAR(255) NOT NULL,
    ProcessingTreatmentPlan INT          NOT NULL,
    CompletingTreatment     VARCHAR(255) NOT NULL
);


CREATE TABLE IF NOT EXISTS Visits(
    VisitID   INT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    StartDate DATETIME NOT NULL,
    EndDate   DATETIME,
    PatientID INT      NOT NULL,
    UNIQUE(StartDate, PatientID),
    UNIQUE(EndDate, PatientID),
    FOREIGN KEY (PatientID) REFERENCES Patients(PatientID)
);


CREATE TABLE IF NOT EXISTS BillingStatements(
    BillingStatementID INT           NOT NULL PRIMARY KEY AUTO_INCREMENT,
    VisitID            INT           NOT NULL UNIQUE,
    RecipientSSN       CHAR(9)       NOT NULL,
    BillingAddress     VARCHAR(255),
    PaymentMethod      VARCHAR(255),
    CardNumber         CHAR(16),
    FOREIGN KEY (VisitID) REFERENCES Visits(VisitID)
);


CREATE TABLE IF NOT EXISTS BillingRecords (
    BillingRecordID      INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    RegistrationFee      INT          NOT NULL,
    AccommodationFee     INT          NOT NULL,
    BillingStatementID   INT          NOT NULL,
    MedicationPrescribed VARCHAR(255) NOT NULL,
    FOREIGN KEY (BillingStatementID) REFERENCES BillingStatements(BillingStatementID)
);


CREATE TABLE IF NOT EXISTS Tests(
    TestID    INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Diagnosis VARCHAR(255) NOT NULL,
    VisitID   INT          NOT NULL,
    StaffID   INT          NOT NULL,
    FOREIGN KEY (VisitID) REFERENCES Visits(VisitID),
    FOREIGN KEY (StaffID) REFERENCES StaffMembers(StaffID)
);


CREATE TABLE IF NOT EXISTS Treatments(
    TreatmentID  INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Prescription VARCHAR(255) NOT NULL,
    VisitID      INT          NOT NULL,
    StaffID      INT          NOT NULL,
    FOREIGN KEY (VisitID) REFERENCES Visits(VisitID),
    FOREIGN KEY (StaffID) REFERENCES StaffMembers(StaffID)
);


CREATE TABLE IF NOT EXISTS Wards(
    WardNumber INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Capacity   INT NOT NULL,
    Charges    INT NOT NULL,
    StaffID    INT NOT NULL,
    FOREIGN KEY (StaffID) REFERENCES StaffMembers(StaffID)
);


CREATE TABLE IF NOT EXISTS Beds (
    BedNumber  INT NOT NULL,
    WardNumber INT NOT NULL,
    PatientID  INT          UNIQUE,
    PRIMARY KEY (BedNumber, WardNumber),
    FOREIGN KEY (PatientID)  REFERENCES Patients(PatientID),
    FOREIGN KEY (WardNumber) REFERENCES Wards(WardNumber)   ON UPDATE CASCADE ON DELETE CASCADE
);
