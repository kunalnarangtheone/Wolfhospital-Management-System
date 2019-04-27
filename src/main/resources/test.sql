INSERT INTO StaffMembers(Name, DOB, Gender, JobTitle, ProfessionalTitle, Department, PhoneNumber, Address)
VALUES
    ('John 117', '1951-03-07', 'Male', 'Doctor', 'Spartan', 'UNSC', '1800EnergySword', 'Pillar of Autumn'),
    ('John 118', '1951-03-07', 'Male', 'Doctor', 'Spartan', 'UNSC', '1800EnergySword', 'Pillar of Autumn'),
    ('John 119', '1951-03-07', 'Male', 'Doctor', 'Spartan', 'UNSC', '1800EnergySword', 'Pillar of Autumn'),
    ('John 120', '1951-03-07', 'Male', 'Doctor', 'Spartan', 'UNSC', '1800EnergySword', 'Pillar of Autumn'),
    ('John 117', '1951-03-07', 'Male', 'Nurse',  'Spartan', 'UNSC', '1800EnergySword', 'Pillar of Autumn'),
    ('John 118', '1951-03-07', 'Male', 'Nurse',  'Spartan', 'UNSC', '1800EnergySword', 'Pillar of Autumn'),
    ('John 119', '1951-03-07', 'Male', 'Nurse',  'Spartan', 'UNSC', '1800EnergySword', 'Pillar of Autumn'),
    ('John 120', '1951-03-07', 'Male', 'Nurse',  'Spartan', 'UNSC', '1800EnergySword', 'Pillar of Autumn');


INSERT INTO Patients(SSN, Name, DOB, Gender, PhoneNumber, Address, ProcessingTreatmentPlan, CompletingTreatment)
VALUES
    ('123456789', 'Link', '1998-03-23', 'Male',   '1-800Hyrule', 'Kokiri Forest', 10, 'no'),
    ('123456799', 'Link', '1998-03-23', 'Male',   '1-800Hyrule', 'Kokiri Forest', 10, 'no'),
    ('123456999', 'Link', '1998-03-23', 'Male',   '1-800Hyrule', 'Kokiri Forest', 10, 'no'),
    ('123459999', 'Link', '1998-03-23', 'Male',   '1-800Hyrule', 'Kokiri Forest', 10, 'no'),
    ('900000000', 'Mary', '1992-03-23', 'Female', '1-919',       'Gorman St.',    10, 'yes'),
    ('900000001', 'Sue',  '1992-03-23', 'Female', '1-919',       'Gorman St.',    10, 'yes');


INSERT INTO Visits(StartDate, EndDate, PatientID)
VALUES
    ('1998-03-23 00:00:01', '1998-03-24 12:34:56', 1),
    ('1998-03-23 00:00:02', '1998-03-24 12:34:56', 2),
    ('1998-03-23 00:00:03', '1998-03-24 12:34:56', 3),
    ('1998-03-23 00:00:04', '1998-03-24 12:34:56', 4),
    ('2018-03-23 10:00:00', '2018-03-25 12:00:00', 5),
    ('2019-01-01',           NULL,                 1);


INSERT INTO BillingStatements(VisitID, RecipientSSN, BillingAddress, PaymentMethod, CardNumber)
VALUES
    (1, '123456789', '890 Oval Drive', 'Credit card', '1234567890123456'),
    (2, '123456789', '891 Oval Drive', 'Credit card', '1234567890123456'),
    (3, '123456789', '892 Oval Drive', 'Credit card', '1234567890123456'),
    (4, '123456789', '892 Oval Drive', 'Credit card', '1234567890123456'),
    (5, '123456789', '893 Oval Drive', 'Credit card', '1234567890123456');


INSERT INTO BillingRecords(BillingStatementID, RegistrationFee, AccommodationFee, MedicationPrescribed)
VALUES
    (1, 1000, 2000, 'yes'),
    (2, 1100, 2000, 'yes'),
    (3, 1200, 2000, 'yes'),
    (4, 1300, 2000, 'yes');


INSERT INTO Tests(Diagnosis, VisitID, StaffID)
VALUES
    ('Flu A',      1, 1),
    ('Flu B',      2, 2),
    ('Flu C',      3, 1),
    ('Flu D',      4, 2),
    ('Depression', 5, 1),
    ('Grim',       6, 1);

INSERT INTO Treatments(Prescription, VisitID, StaffID)
VALUES
    ('Vicodin',      1, 5),
    ('Tylenol',      2, 6),
    ('Ibuprofen',    3, 7),
    ('Advil',        4, 8),
    ('Escitalopram', 5, 5);


INSERT INTO Wards(Capacity, Charges, StaffID)
VALUES
    (1, 31, 5),
    (2, 31, 6),
    (3, 31, 7),
    (4, 31, 8);


INSERT INTO Beds(WardNumber, BedNumber, PatientID)
VALUES
    (1, 1, 1),
    (2, 1, 2),
    (2, 2, 3),
    (3, 1, 4),
    (3, 2, NULL),
    (3, 3, NULL);