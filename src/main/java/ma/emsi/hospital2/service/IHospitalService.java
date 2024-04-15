package ma.emsi.hospital2.service;

import ma.emsi.hospital2.entities.Consultation;
import ma.emsi.hospital2.entities.Medecin;
import ma.emsi.hospital2.entities.Patient;
import ma.emsi.hospital2.entities.RendezVous;

public interface IHospitalService {
    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRDV(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);
}
