package com.solvd.hospital.service;

import com.solvd.hospital.model.patient.TreatmentData;

import java.io.File;

public interface ITreatmentDataParsingService {
    void marshalTreatmentData(TreatmentData treatmentData, File file);
    TreatmentData unmarshalTreatmentData(File file);
    void serializeTreatmentData(TreatmentData treatmentData, File file);

    TreatmentData deserializeTreatmentData(File file);
}
