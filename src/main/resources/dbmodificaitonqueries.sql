UPDATE Departments SET Name = " Neurology" WHERE id = 2;
UPDATE Doctor SET Name = " Matt", Position = "Neurosurgeons", Departments_Id = 2 WHERE employeeId = 2;
UPDATE Nurse SET Name = " Ashley", Position = "Certified Nursing Assistant", Departments_Id = 2 WHERE employeeId = 2;
UPDATE PatientMedicalChart SET Diagnosis = "Mild Concussion", DateVisted = "2023-12-21" WHERE reportId = 2;
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
    JOIN Lab ON MedicalBill.Lab_id = Lab.id
    JOIN Departments ON Patient.Doctor_employeeId = Doctor.employeeId AND Doctor.Departments_id = Departments.id
    JOIN MedicationPrescription ON MedicationPrescription.PatientMedicalChart_reportId = patientmedicalchart.reportId
    JOIN Medication ON MedicationPrescription.Medication_id = medication.id
	JOIN SymptomInformation ON SymptomInformation.PatientMedicalChart_reportId = patientmedicalchart.reportId
    JOIN Symptom ON SymptomInformation.Symptoms_id = symptom.id
    JOIN Treatment ON MedicalBill.Insurance_id = Treatment.id;

SELECT * FROM appointment, departments, doctor, insurance, lab, medicalbill, medication,
		MedicationPrescription, nurse, patient, patientmedicalchart, 
        SymptomInformation, symptom, treatment;
        
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
SELECT P.Name AS Patient_Name, PMC.Diagnosis, PMC.dateVisted FROM Patient P
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
    
SELECT id, MIN(cost) FROM Lab
	GROUP BY id;
SELECT id, MAX(cost) FROM Lab
	GROUP BY id;
SELECT id, SUM(cost) FROM Lab
	GROUP BY id;
SELECT id, AVG(cost) FROM Lab
	GROUP BY id;
SELECT id, COUNT(cost) FROM Lab
	GROUP BY id;
SELECT Patient_id, Min(amountDue) FROM MedicalBill
	GROUP BY Patient_id;
SELECT Patient_id, Max(amountDue) FROM MedicalBill
	GROUP BY Patient_id;
    
SELECT id, MIN(cost) FROM Lab
	GROUP BY id HAVING MIN(cost) < 51;
SELECT id, MAX(cost) FROM Lab
	GROUP BY id HAVING MAX(cost) > 50;
SELECT id, SUM(cost) FROM Lab
	GROUP BY id HAVING SUM(cost) >1000;
SELECT id, AVG(cost) FROM Lab
	GROUP BY id HAVING AVG(cost) > 400;
SELECT testName, COUNT(cost) FROM Lab
	GROUP BY testName HAVING COUNT(cost) > 1;
SELECT id, Min(amountDue) FROM MedicalBill
	GROUP BY id HAVING MIN(amountDue) > 11;
SELECT id, Max(amountDue) FROM MedicalBill
	GROUP BY id HAVING MAX(amountDue) < 900;