CREATE DATABASE Hospital1;
CREATE TABLE IF NOT EXISTS `hospital1`.`Departments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);

CREATE TABLE IF NOT EXISTS `hospital1`.`Doctor` (
  `employeeId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `position` VARCHAR(45) NULL,
  `Departments_id` INT NULL,
  PRIMARY KEY (`employeeId`),
  UNIQUE INDEX `id_UNIQUE` (`employeeId` ASC) VISIBLE,
  INDEX `fk_Doctor_Departments1_idx` (`Departments_id` ASC) VISIBLE,
  CONSTRAINT `fk_Doctor_Departments1`
    FOREIGN KEY (`Departments_id`)
    REFERENCES `hospital`.`Departments` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE IF NOT EXISTS `hospital1`.`PatientMedicalChart` (
  `reportId` INT NOT NULL AUTO_INCREMENT,
  `diagnosis` VARCHAR(45) NULL,
  `date` DATE NULL,
  PRIMARY KEY (`reportId`),
  UNIQUE INDEX `reportId_UNIQUE` (`reportId` ASC) VISIBLE);


CREATE TABLE IF NOT EXISTS `hospital1`.`Nurse` (
  `employeeId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `position` VARCHAR(45) NULL,
  `Departments_id` INT NULL,
  PRIMARY KEY (`employeeId`),
  UNIQUE INDEX `id_UNIQUE` (`employeeId` ASC) VISIBLE,
  INDEX `fk_Nurse_Departments1_idx` (`Departments_id` ASC) VISIBLE,
  CONSTRAINT `fk_Nurse_Departments1`
    FOREIGN KEY (`Departments_id`)
    REFERENCES `hospital`.`Departments` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE IF NOT EXISTS `hospital1`.`Insurance` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idInsurance_UNIQUE` (`id` ASC) VISIBLE);


CREATE TABLE IF NOT EXISTS `hospital1`.`Patient` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NULL,
  `phoneNumber` VARCHAR(45) NULL,
  `Doctor_employeeId` INT NOT NULL,
  `PatientMedicalChart_reportId` INT NOT NULL,
  `Nurse_employeeId` INT NOT NULL,
  `Insurance_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_Patient_Doctor_idx` (`Doctor_employeeId` ASC) VISIBLE,
  INDEX `fk_Patient_PatientMedicalChart1_idx` (`PatientMedicalChart_reportId` ASC) VISIBLE,
  INDEX `fk_Patient_Nurse1_idx` (`Nurse_employeeId` ASC) VISIBLE,
  INDEX `fk_Patient_Insurance1_idx` (`Insurance_id` ASC) VISIBLE,
  CONSTRAINT `fk_Patient_Doctor`
    FOREIGN KEY (`Doctor_employeeId`)
    REFERENCES `hospital`.`Doctor` (`employeeId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Patient_PatientMedicalChart1`
    FOREIGN KEY (`PatientMedicalChart_reportId`)
    REFERENCES `hospital`.`PatientMedicalChart` (`reportId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Patient_Nurse1`
    FOREIGN KEY (`Nurse_employeeId`)
    REFERENCES `hospital`.`Nurse` (`employeeId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Patient_Insurance1`
    FOREIGN KEY (`Insurance_id`)
    REFERENCES `hospital`.`Insurance` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS `hospital1`.`Medication` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `brand` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);


CREATE TABLE IF NOT EXISTS `hospital1`.`Treatment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idTreatment_UNIQUE` (`id` ASC) VISIBLE);


CREATE TABLE IF NOT EXISTS `hospital1`.`Appointment` (
  `id` INT NOT NULL,
  `date` DATE NULL,
  `time` TIME NULL,
  `Patient_id` INT NOT NULL,
  PRIMARY KEY (`id`, `Patient_id`),
  INDEX `fk_Appointment_Patient1_idx` (`Patient_id` ASC) VISIBLE,
  CONSTRAINT `fk_Appointment_Patient1`
    FOREIGN KEY (`Patient_id`)
    REFERENCES `hospital`.`Patient` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS `hospital1`.`MedicalBill` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `amountDue` DECIMAL(4) NULL,
  `Patient_id` INT NOT NULL,
  `Insurance_id` INT NOT NULL,
  PRIMARY KEY (`id`, `Patient_id`, `Insurance_id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_MedicalBill_Patient1_idx` (`Patient_id` ASC) VISIBLE,
  INDEX `fk_MedicalBill_Insurance1_idx` (`Insurance_id` ASC) VISIBLE,
  CONSTRAINT `fk_MedicalBill_Patient1`
    FOREIGN KEY (`Patient_id`)
    REFERENCES `hospital`.`Patient` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_MedicalBill_Insurance1`
    FOREIGN KEY (`Insurance_id`)
    REFERENCES `hospital`.`Insurance` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS `hospital1`.`Lab` (
  `id` INT NOT NULL,
  `testName` VARCHAR(45) NULL,
  `cost` DECIMAL(4) NULL,
  `Patient_id` INT NOT NULL,
  PRIMARY KEY (`id`, `Patient_id`),
  INDEX `fk_Lab_Patient1_idx` (`Patient_id` ASC) VISIBLE,
  CONSTRAINT `fk_Lab_Patient1`
    FOREIGN KEY (`Patient_id`)
    REFERENCES `hospital`.`Patient` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


CREATE TABLE IF NOT EXISTS `hospital1`.`Symptom` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `hospital1`.`Doctor_has_Appointment` (
  `Doctor_employeeId` INT NOT NULL,
  `Appointment_id` INT NOT NULL,
  `Appointment_Patient_id` INT NOT NULL,
  PRIMARY KEY (`Doctor_employeeId`, `Appointment_id`, `Appointment_Patient_id`),
  INDEX `fk_Doctor_has_Appointment_Appointment1_idx` (`Appointment_id` ASC, `Appointment_Patient_id` ASC) VISIBLE,
  INDEX `fk_Doctor_has_Appointment_Doctor1_idx` (`Doctor_employeeId` ASC) VISIBLE,
  CONSTRAINT `fk_Doctor_has_Appointment_Doctor1`
    FOREIGN KEY (`Doctor_employeeId`)
    REFERENCES `hospital`.`Doctor` (`employeeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Doctor_has_Appointment_Appointment1`
    FOREIGN KEY (`Appointment_id` , `Appointment_Patient_id`)
    REFERENCES `hospital`.`Appointment` (`id` , `Patient_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `hospital1`.`Nurse_has_Appointment` (
  `Nurse_employeeId` INT NOT NULL,
  `Appointment_id` INT NOT NULL,
  `Appointment_Patient_id` INT NOT NULL,
  PRIMARY KEY (`Nurse_employeeId`, `Appointment_id`, `Appointment_Patient_id`),
  INDEX `fk_Nurse_has_Appointment_Appointment1_idx` (`Appointment_id` ASC, `Appointment_Patient_id` ASC) VISIBLE,
  INDEX `fk_Nurse_has_Appointment_Nurse1_idx` (`Nurse_employeeId` ASC) VISIBLE,
  CONSTRAINT `fk_Nurse_has_Appointment_Nurse1`
    FOREIGN KEY (`Nurse_employeeId`)
    REFERENCES `hospital`.`Nurse` (`employeeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Nurse_has_Appointment_Appointment1`
    FOREIGN KEY (`Appointment_id` , `Appointment_Patient_id`)
    REFERENCES `hospital`.`Appointment` (`id` , `Patient_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `hospital1`.`PatientMedicalChart_has_Symptoms` (
  `PatientMedicalChart_reportId` INT NOT NULL,
  `Symptoms_id` INT NOT NULL,
  PRIMARY KEY (`PatientMedicalChart_reportId`, `Symptoms_id`),
  INDEX `fk_PatientMedicalChart_has_Symptoms_Symptoms1_idx` (`Symptoms_id` ASC) VISIBLE,
  INDEX `fk_PatientMedicalChart_has_Symptoms_PatientMedicalChart1_idx` (`PatientMedicalChart_reportId` ASC) VISIBLE,
  CONSTRAINT `fk_PatientMedicalChart_has_Symptoms_PatientMedicalChart1`
    FOREIGN KEY (`PatientMedicalChart_reportId`)
    REFERENCES `hospital`.`PatientMedicalChart` (`reportId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PatientMedicalChart_has_Symptoms_Symptoms1`
    FOREIGN KEY (`Symptoms_id`)
    REFERENCES `hospital`.`Symptom` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `hospital1`.`PatientMedicalChart_has_Treatment` (
  `PatientMedicalChart_reportId` INT NOT NULL,
  `Treatment_id` INT NOT NULL,
  PRIMARY KEY (`PatientMedicalChart_reportId`, `Treatment_id`),
  INDEX `fk_PatientMedicalChart_has_Treatment_Treatment1_idx` (`Treatment_id` ASC) VISIBLE,
  INDEX `fk_PatientMedicalChart_has_Treatment_PatientMedicalChart1_idx` (`PatientMedicalChart_reportId` ASC) VISIBLE,
  CONSTRAINT `fk_PatientMedicalChart_has_Treatment_PatientMedicalChart1`
    FOREIGN KEY (`PatientMedicalChart_reportId`)
    REFERENCES `hospital`.`PatientMedicalChart` (`reportId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PatientMedicalChart_has_Treatment_Treatment1`
    FOREIGN KEY (`Treatment_id`)
    REFERENCES `hospital`.`Treatment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `hospital1`.`Medication_has_PatientMedicalChart` (
  `Medication_id` INT NOT NULL,
  `PatientMedicalChart_reportId` INT NOT NULL,
  PRIMARY KEY (`Medication_id`, `PatientMedicalChart_reportId`),
  INDEX `fk_Medication_has_PatientMedicalChart_PatientMedicalChart1_idx` (`PatientMedicalChart_reportId` ASC) VISIBLE,
  INDEX `fk_Medication_has_PatientMedicalChart_Medication1_idx` (`Medication_id` ASC) VISIBLE,
  CONSTRAINT `fk_Medication_has_PatientMedicalChart_Medication1`
    FOREIGN KEY (`Medication_id`)
    REFERENCES `hospital`.`Medication` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Medication_has_PatientMedicalChart_PatientMedicalChart1`
    FOREIGN KEY (`PatientMedicalChart_reportId`)
    REFERENCES `hospital`.`PatientMedicalChart` (`reportId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

USE hospital1;

INSERT INTO Departments VALUES (1, "Cardiology");
INSERT INTO Doctor VALUES (1,"John" , "Physician" ,  1);
INSERT INTO Nurse VALUES (1,"Jane" , "Registered Nurse" ,  1);
INSERT INTO PatientMedicalChart VALUES (1,"Sprain Ankle" , "2023-01-01");
INSERT INTO Insurance VALUES (1,"Medicare" );
INSERT INTO Patient VALUES (1,"Jim" , "123-122 122st", "123-123-1234",  1, 1, 1, 1);
INSERT INTO Appointment VALUES (1,"2023-01-01", "12:00",1 );
INSERT INTO Doctor_Has_Appointment VALUES (1, 1, 1 );
INSERT INTO Nurse_Has_Appointment VALUES (1, 1, 1 );
INSERT INTO Lab VALUES (1, "Bloodwork", 432.00, 1 );
INSERT INTO MedicalBill VALUES (1, 813.00, 1, 1 );
INSERT INTO Medication VALUES (1, "Lisinopril", "Zestril");
INSERT INTO Medication_Has_PatientMedicalChart VALUES (1, 1 );
INSERT INTO Symptom VALUES (1, "Fever");
INSERT INTO PatientMedicalChart_Has_Symptoms VALUES (1, 1);
INSERT INTO PatientMedicalChart_Has_Treatment VALUES (1,1);
INSERT INTO Treatment VALUES (1, "Surgery");

INSERT INTO Departments VALUES (2, "Cardiology");
INSERT INTO Doctor VALUES (2,"John" , "Physician" ,  2);
INSERT INTO Nurse VALUES (2,"Jane" , "Registered Nurse" ,  2);
INSERT INTO PatientMedicalChart VALUES (2,"Sprain Ankle" , "2023-01-01");
INSERT INTO Patient VALUES (2,"Jim" , "123-122 122st", "123-123-1234",  2, 2, 2, 1);
INSERT INTO Patient VALUES (3,"Max" , "76-866 312st", "999-999-999",  2, 2, 2, 1);
INSERT INTO Appointment VALUES (2,"2023-01-01", "12:00", 2 );
INSERT INTO Lab VALUES (2, "Bloodwork", 432.00, 2 );
INSERT INTO Lab VALUES (4, "Cholesterol", 821.00, 1 );
INSERT INTO Lab VALUES (3, "HBL Test", 21.00, 1 );
INSERT INTO MedicalBill VALUES (2, 813.00, 2, 1 );
INSERT INTO MedicalBill VALUES (5, 10.00, 1, 1 );
INSERT INTO MedicalBill VALUES (3, 1211.00, 3, 1 );
INSERT INTO Medication VALUES (2, "Lisinopril", "Zestril");
INSERT INTO Symptom VALUES (2, "Fever");

UPDATE Departments SET Name = " Neurology" WHERE id = 2;
UPDATE Doctor SET Name = " Matt", Position = "Neurosurgeons", Departments_Id = 2 WHERE employeeId = 2;
UPDATE Nurse SET Name = " Ashley", Position = "Certified Nursing Assistant", Departments_Id = 2 WHERE employeeId = 2;
UPDATE PatientMedicalChart SET Diagnosis = "Mild Concussion", Date = "2023-12-21" WHERE reportId = 2;
UPDATE Patient SET Name = " Bob", Address = "111-11 111st", phoneNumber= "444-444-4444" ,PatientMedicalChart_reportId = 2 WHERE id = 2;
UPDATE Appointment SET Date = " 2022-09-12", Time= "11:00" WHERE id = 2;
UPDATE Lab SET testName = " Lipid Panel", cost = 41 WHERE id = 2;
UPDATE MedicalBill SET amountDue = 1232 WHERE id = 2;
UPDATE Medication SET Name = "Levothyroxine", Brand = "Synthiroid" WHERE id = 2;
UPDATE Symptom SET Name = "Cough" WHERE id = 2;

DELETE FROM Doctor WHERE employeeId=2;
DELETE FROM Departments WHERE id=2;
DELETE FROM Nurse WHERE employeeId=2;
DELETE FROM PatientMedicalChart WHERE reportId=2;
DELETE FROM Patient WHERE id=2;
DELETE FROM Appointment WHERE id=2;
DELETE FROM Lab WHERE id=2;
DELETE FROM MedicalBill WHERE id=2;
DELETE FROM Medication WHERE id=2;
DELETE FROM Symptom WHERE id=2;

ALTER TABLE Symptom ADD COLUMN temp varchar(45);
ALTER TABLE Symptom RENAME COLUMN temp TO temp2;
ALTER TABLE Symptom RENAME COLUMN temp2 TO temp;
ALTER TABLE Symptom MODIFY temp int;
ALTER TABLE Symptom DROP COLUMN temp;

SELECT * FROM Patient 
	JOIN appointment ON Patient.id = Appointment.Patient_id
	JOIN Insurance ON Patient.Insurance_id = Insurance.id
	JOIN Doctor ON Patient.Doctor_employeeId = Doctor.employeeId
	JOIN Nurse ON Patient.Nurse_employeeId = Nurse.employeeId
    JOIN PatientMedicalChart ON Patient.PatientMedicalChart_reportId = PatientMedicalChart.reportId
    JOIN MedicalBill ON Patient.id = MedicalBill.Patient_id
    JOIN Lab ON Patient.id = Lab.Patient_id
    JOIN Departments ON Patient.Doctor_employeeId = Doctor.employeeId AND Doctor.Departments_id = Departments.id
    JOIN medication_has_patientmedicalchart ON medication_has_patientmedicalchart.PatientMedicalChart_reportId = patientmedicalchart.reportId
    JOIN Medication ON medication_has_patientmedicalchart.Medication_id = medication.id
	JOIN patientmedicalchart_has_symptoms ON patientmedicalchart_has_symptoms.PatientMedicalChart_reportId = patientmedicalchart.reportId
    JOIN patientmedicalchart_has_treatment ON patientmedicalchart_has_treatment.PatientMedicalChart_reportId = patientmedicalchart.reportId
    JOIN Symptom ON patientmedicalchart_has_symptoms.Symptoms_id = symptom.id
    JOIN Treatment ON patientmedicalchart_has_treatment.Treatment_id = Treatment.id;

SELECT * FROM appointment, departments, doctor, doctor_has_appointment, insurance, lab, medicalbill, medication,
		medication_has_patientmedicalchart, nurse, nurse_has_appointment, patient, patientmedicalchart, 
        patientmedicalchart_has_symptoms, symptom, treatment;
        
SELECT * FROM Patient P
	LEFT JOIN Doctor D ON P.Doctor_employeeId = D.employeeID;
SELECT * FROM Departments Dep
	LEFT JOIN Doctor D ON Dep.id = D.Departments_id;
SELECT * FROM Patient P
	LEFT JOIN Insurance I ON P.Insurance_id = I.id;
SELECT * FROM Patient P
	LEFT JOIN PatientMedicalChart PMC ON P.PatientMedicalChart_reportId = PMC.reportId;
SELECT * FROM Patient P
	LEFT JOIN Nurse N ON P.Nurse_employeeId = N.employeeID;
    
SELECT * FROM Patient P
	RIGHT JOIN Doctor D ON P.Doctor_employeeId = D.employeeID;
SELECT * FROM Departments Dep
	RIGHT JOIN Doctor D ON Dep.id = D.Departments_id;
SELECT * FROM Patient P
	RIGHT JOIN Insurance I ON P.Insurance_id = I.id;
SELECT * FROM Patient P
	RIGHT JOIN PatientMedicalChart PMC ON P.PatientMedicalChart_reportId = PMC.reportId;
SELECT * FROM Patient P
	RIGHT JOIN Nurse N ON P.Nurse_employeeId = N.employeeID;
    
SELECT P.Name AS Patient_Name, D.Name AS Doctor_Name FROM Patient P
	CROSS JOIN Doctor D ON P.Doctor_employeeId = D.employeeID;
SELECT Dep.Name AS Department_Name, D.Name AS Doctor_Name FROM Departments Dep
	CROSS JOIN Doctor D ON Dep.id = D.Departments_id;
SELECT P.Name AS Patient_Name, I.Name AS Insurance_Name FROM Patient P
	CROSS JOIN Insurance I ON P.Insurance_id = I.id;
SELECT P.Name AS Patient_Name, PMC.Diagnosis, PMC.Date FROM Patient P
	CROSS JOIN PatientMedicalChart PMC ON P.PatientMedicalChart_reportId = PMC.reportId;
SELECT P.Name AS Patient_Name, N.Name AS Nurse_Name FROM Patient P 
	CROSS JOIN Nurse N ON P.Nurse_employeeId = N.employeeID;
    
SELECT * FROM Patient P
	JOIN Doctor D ON P.Doctor_employeeId = D.employeeID;
SELECT * FROM Departments Dep
	JOIN Doctor D ON Dep.id = D.Departments_id;
SELECT * FROM Patient P
	JOIN Insurance I ON P.Insurance_id = I.id;
SELECT * FROM Patient P
	JOIN PatientMedicalChart PMC ON P.PatientMedicalChart_reportId = PMC.reportId;
SELECT * FROM Patient P
	JOIN Nurse N ON P.Nurse_employeeId = N.employeeID;
    
SELECT Patient_id, MIN(cost) FROM Lab
	GROUP BY Patient_id;
SELECT Patient_id, MAX(cost) FROM Lab
	GROUP BY Patient_id;
SELECT Patient_id, SUM(cost) FROM Lab
	GROUP BY Patient_id;
SELECT Patient_id, AVG(cost) FROM Lab
	GROUP BY Patient_id;
SELECT Patient_id, COUNT(cost) FROM Lab
	GROUP BY Patient_id;
SELECT Patient_id, Min(amountDue) FROM MedicalBill
	GROUP BY Patient_id;
SELECT Patient_id, Max(amountDue) FROM MedicalBill
	GROUP BY Patient_id;
    
SELECT id, MIN(cost) FROM Lab
	GROUP BY id HAVING MIN(cost) < 51;
SELECT id, MAX(cost) FROM Lab
	GROUP BY id HAVING MAX(cost) > 50;
SELECT Patient_id, SUM(cost) FROM Lab
	GROUP BY Patient_id HAVING SUM(cost) >1000;
SELECT Patient_id, AVG(cost) FROM Lab
	GROUP BY Patient_id HAVING AVG(cost) > 400;
SELECT Patient_id, COUNT(cost) FROM Lab
	GROUP BY Patient_id HAVING COUNT(cost) > 1;
SELECT Patient_id, Min(amountDue) FROM MedicalBill
	GROUP BY Patient_id HAVING MIN(amountDue) > 11;
SELECT Patient_id, Max(amountDue) FROM MedicalBill
	GROUP BY Patient_id HAVING MAX(amountDue) < 900;
    