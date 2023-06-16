package com.solvd.hospital.service;

import com.solvd.hospital.model.patient.TreatmentData;

import java.io.File;

public interface ITreatmentDataParsingService {
    void marshalTreatmentData(TreatmentData treatmentData, String file);
    TreatmentData unmarshalTreatmentData(String file);
    void serializeTreatmentData(TreatmentData treatmentData, String file);

    TreatmentData deserializeTreatmentData(String file);
}
