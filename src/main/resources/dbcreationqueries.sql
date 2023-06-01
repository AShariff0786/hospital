CREATE DATABASE hospital1;
CREATE TABLE IF NOT EXISTS `hospital1`.`Departments` (
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL);

CREATE TABLE IF NOT EXISTS `hospital1`.`Doctor` (
  employeeId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  position VARCHAR(45),
  Departments_id INT NOT NULL,
    FOREIGN KEY (`Departments_id`) REFERENCES `hospital1`.`Departments` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `hospital1`.`PatientMedicalChart` (
  `reportId` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `diagnosis` VARCHAR(45),
  `dateVisted` DATE);

CREATE TABLE IF NOT EXISTS `hospital1`.`Nurse` (
  `employeeId` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `position` VARCHAR(45),
  `Departments_id` INT NOT NULL,
    FOREIGN KEY (`Departments_id`) REFERENCES `hospital`.`Departments` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE IF NOT EXISTS `hospital1`.`Insurance` (
  `id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL);

CREATE TABLE IF NOT EXISTS `hospital1`.`Patient` (
  `id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45),
  `phoneNumber` VARCHAR(45),
  `Doctor_employeeId` INT NOT NULL,
  `PatientMedicalChart_reportId` INT NOT NULL,
  `Nurse_employeeId` INT NOT NULL,
  `Insurance_id` INT NOT NULL,
    FOREIGN KEY (`Doctor_employeeId`) REFERENCES `hospital1`.`Doctor` (`employeeId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (`PatientMedicalChart_reportId`) REFERENCES `hospital1`.`PatientMedicalChart` (`reportId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (`Nurse_employeeId`) REFERENCES `hospital1`.`Nurse` (`employeeId`)
	ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (`Insurance_id`) REFERENCES `hospital1`.`Insurance` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS `hospital1`.`Medication` (
  `id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `brand` VARCHAR(45));

CREATE TABLE IF NOT EXISTS `hospital1`.`Treatment` (
  `id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `cost` DECIMAL(4));

CREATE TABLE IF NOT EXISTS `hospital1`.`Appointment` (
  `id` INT PRIMARY KEY NOT NULL,
  `date` DATE,
  `time` TIME,
  `Patient_id` INT NOT NULL,
  `Doctor_employeeId` INT NOT NULL,
  `Nurse_employeeId` INT NOT NULL,
    FOREIGN KEY (`Patient_id`) REFERENCES `hospital1`.`Patient` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (`Doctor_employeeId`) REFERENCES `hospital1`.`Doctor` (`employeeId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (`Nurse_employeeId`) REFERENCES `hospital1`.`Nurse` (`employeeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE IF NOT EXISTS `hospital1`.`Lab` (
  `id` INT PRIMARY KEY NOT NULL,
  `testName` VARCHAR(45),
  `cost` DECIMAL(4));

CREATE TABLE IF NOT EXISTS `hospital1`.`MedicalBill` (
  `id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `amountDue` DECIMAL(4),
  `Patient_id` INT NOT NULL,
  `Insurance_id` INT NOT NULL,
  `Lab_id` INT NOT NULL,
  `Treatment_id` INT NOT NULL,
    FOREIGN KEY (`Patient_id`) REFERENCES `hospital1`.`Patient` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (`Insurance_id`) REFERENCES `hospital1`.`Insurance` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`Lab_id`) REFERENCES `hospital1`.`Lab` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`Treatment_id`) REFERENCES `hospital1`.`Treatment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `hospital1`.`Symptom` (
  `id` INT PRIMARY KEY NOT NULL,
  `name` VARCHAR(45) NULL);

CREATE TABLE IF NOT EXISTS `hospital1`.`PatientMedicalChart_has_Symptoms` (
  `id` INT PRIMARY KEY NOT NULL,
  `PatientMedicalChart_reportId` INT NOT NULL,
  `Symptoms_id` INT NOT NULL,
  `Start_Date` DATE,
  `End_Date` DATE,
    FOREIGN KEY (`PatientMedicalChart_reportId`) REFERENCES `hospital1`.`PatientMedicalChart` (`reportId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (`Symptoms_id`) REFERENCES `hospital1`.`Symptom` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `hospital1`.`MedicationPrescription` (
  `id` INT PRIMARY KEY NOT NULL,
  `Medication_id` INT NOT NULL,
  `PatientMedicalChart_reportId` INT NOT NULL,
  `Dosage` INT,
  `Number_Refills` INT,
    FOREIGN KEY (`Medication_id`) REFERENCES `hospital1`.`Medication` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`PatientMedicalChart_reportId`) REFERENCES `hospital1`.`PatientMedicalChart` (`reportId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
USE hospital1;

INSERT INTO Departments VALUES (1, "Cardiology");
INSERT INTO Doctor VALUES (1,"John" , "Physician" ,  1);
INSERT INTO Nurse VALUES (1,"Jane" , "Registered Nurse" ,  1);
INSERT INTO PatientMedicalChart VALUES (1,"Sprain Ankle" , "2023-01-01");
INSERT INTO Insurance VALUES (1,"Medicare" );
INSERT INTO Patient VALUES (1,"Jim" , "123-122 122st", "123-123-1234",  1, 1, 1, 1);
INSERT INTO Appointment VALUES (1,"2023-01-01", "12:00",1, 1, 1 );
INSERT INTO Lab VALUES (1, "Bloodwork", 432.00 );
INSERT INTO Treatment VALUES (1, "Surgery", 108.00 );
INSERT INTO MedicalBill VALUES (1, 813.00, 1, 1, 1, 1 );
INSERT INTO Medication VALUES (1, "Lisinopril", "Zestril");
INSERT INTO MedicationPrescription VALUES (1, 1, 1, 8, 3);
INSERT INTO Symptom VALUES (1, "Fever");
INSERT INTO PatientMedicalChart_Has_Symptoms VALUES (1, 1, 1, "2023-06-21", "2023-06-12");

INSERT INTO Departments VALUES (2, "Cardiology");
INSERT INTO Doctor VALUES (2,"John" , "Physician" ,  2);
INSERT INTO Nurse VALUES (2,"Jane" , "Registered Nurse" ,  2);
INSERT INTO PatientMedicalChart VALUES (2,"Sprain Ankle" , "2023-01-01");
INSERT INTO Patient VALUES (2,"Jim" , "123-122 122st", "123-123-1234",  2, 2, 2, 1);
INSERT INTO Patient VALUES (3,"Max" , "76-866 312st", "999-999-999",  2, 2, 2, 1);
INSERT INTO Appointment VALUES (2,"2023-01-01", "12:00", 2, 2, 2 );
INSERT INTO Lab VALUES (2, "Bloodwork", 432.00);
INSERT INTO Lab VALUES (4, "HBL Test", 1221.00 );
INSERT INTO Lab VALUES (3, "HBL Test", 1221.00 );
INSERT INTO MedicalBill VALUES (2, 813.00, 2, 1, 1, 1 );
INSERT INTO MedicalBill VALUES (5, 10.00, 1, 1, 1, 1 );
INSERT INTO MedicalBill VALUES (3, 1211.00, 3, 1, 1, 1 );
INSERT INTO Medication VALUES (2, "Lisinopril", "Zestril");
INSERT INTO Symptom VALUES (2, "Fever");