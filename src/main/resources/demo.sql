INSERT INTO StaffMembers(StaffID, Name, DOB, Gender, JobTitle, ProfessionalTitle, Department, PhoneNumber, Address)
VALUES
       (100, 'Mary',   '1979-04-01', 'Female', 'Doctor',           'Senior',         'Neurology',           '654', '90 ABC St , Raleigh NC 27'),
       (101, 'John',   '1974-04-01', 'Male',   'Billing staff',    '',               'Office',              '564', '798 XYZ St , Rochester NY 54'),
       (102, 'Carol',  '1964-04-01', 'Female', 'Nurse',            '',               'ER',                  '911', '351 MH St , Greensboro NC 27'),
       (103, 'Emma',   '1964-04-01', 'Female', 'Doctor',           'Senior surgeon', 'Oncological Surgery', '546', '49 ABC St , Raleigh NC 27'),
       (104, 'Ava',    '1964-04-01', 'Female', 'Front Desk Staff', '',               'Office',              '777', '425 RG St , Raleigh NC 27'),
       (105, 'Peter',  '1967-04-01', 'Male',   'Doctor',           'Anesthetist',    'Oncological Surgery', '724', '475 RG St , Raleigh NC 27'),
       (106, 'Olivia', '1992-04-01', 'Female', 'Nurse',            '',               'Neurology',           '799', '325 PD St , Raleigh NC 27');



INSERT INTO Patients(PatientID, SSN, Name, DOB, Gender, PhoneNumber, Address, ProcessingTreatmentPlan, CompletingTreatment)
VALUES
       (1001, '000011234', 'David',  '1980-01-30', 'Male',   '919-123-3324', '69 ABC St , Raleigh NC 27730', 20, 'no'),
       (1002, '000021234', 'Sarah',  '1971-01-30', 'Female', '919-563-3478', '81 DEF St , Cary NC 27519',    20, 'no'),
       (1003, '000031234', 'Joseph', '1987-01-30', 'Male',   '919-957-2199', '31 OPG St , Cary NC 27519',    10, 'no'),
       (1004, '000041234', 'Lucy',   '1985-01-30', 'Female', '919-838-7123', '10 TBC St , Raleigh NC 27730',  5, 'yes');


INSERT INTO Wards(WardNumber, Capacity, Charges, StaffID)
VALUES
       (001, 4,  50, 102),
       (002, 4,  50, 102),
       (003, 2, 100, 106),
       (004, 2, 100, 106);


INSERT INTO Beds(WardNumber, BedNumber, PatientID)
VALUES
       (001, 1, 1001),
       (001, 2, 1003),
       (001, 3, NULL),
       (001, 4, NULL),
       (002, 1, 1002),
       (002, 2, NULL),
       (002, 3, NULL),
       (002, 4, NULL),
       (003, 1, NULL),
       (003, 2, NULL),
       (004, 1, NULL),
       (004, 2, NULL);


INSERT INTO Visits(VisitID, StartDate, EndDate, PatientID)
VALUES
       (1, '2019-03-01',  NULL,        1001),
       (2, '2019-03-10',  NULL,        1002),
       (3, '2019-03-15',  NULL,        1003),
       (4, '2019-03-17', '2019-03-21', 1004);


INSERT INTO BillingStatements(BillingStatementID, VisitID, RecipientSSN, BillingAddress, PaymentMethod, CardNumber)
VALUES
       (1, 1, '000011234', '69 ABC St , Raleigh NC 27730', 'Credit card', '4044875409613234'),
       (2, 2, '000021234', '81 DEF St , Cary NC 27519',    'Credit card', '4401982398541143'),
       (3, 3, '000031234', '31 OPG St , Cary NC 27519',    'Check',       NULL),
       (4, 4, '000041234', '10 TBC St. Raleigh NC 27730',  'Credit card', '4044987612349123');


INSERT INTO BillingRecords(BillingRecordID, BillingStatementID, RegistrationFee, AccommodationFee, MedicationPrescribed)
VALUES
       (1, 1, 10000,     0, 'yes'),
       (2, 2, 10000,     0, 'yes'),
       (3, 3, 10000,     0, 'yes'),
       (4, 4, 10000, 40000, 'yes');


INSERT INTO Tests(TestID, Diagnosis, VisitID, StaffID)
VALUES
       (1, 'Hospitalization',          1, 100),
       (2, 'Hospitalization',          2, 100),
       (3, 'Hospitalization',          3, 100),
       (4, 'Surgeon, Hospitalization', 4, 103);


INSERT INTO Treatments(TreatmentID, Prescription, VisitID, StaffID)
VALUES
       (1, 'nervine',   1, 100),
       (2, 'nervine',   2, 100),
       (3, 'nervine',   3, 100),
       (4, 'analgesic', 4, 103);
