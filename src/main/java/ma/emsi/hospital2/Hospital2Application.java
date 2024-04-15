package ma.emsi.hospital2;

import ma.emsi.hospital2.entities.*;
import ma.emsi.hospital2.repositories.ConsultationRepository;
import ma.emsi.hospital2.repositories.MedecinRepository;
import ma.emsi.hospital2.repositories.PatientRepository;
import ma.emsi.hospital2.repositories.RendezVousRepository;
import ma.emsi.hospital2.service.IHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class Hospital2Application {

	public static void main(String[] args) {
		SpringApplication.run(Hospital2Application.class, args);
	}

	@Bean
	CommandLineRunner start(IHospitalService hospitalService,
							PatientRepository patientRepository,
							MedecinRepository medecinRepository,
							RendezVousRepository rendezVousRepository){
		return args -> {
			Stream.of("Mohamed", "Hassan", "Najat")
					.forEach(name-> {
						Patient patient = new Patient();
						patient.setNom(name);
						patient.setDateNaissance(new Date());
						patient.setMalade(false);
						hospitalService.savePatient(patient);
					});

			Stream.of("Aymane", "Hanane", "Yasmine")
					.forEach(name-> {
						Medecin medecin = new Medecin();
						medecin.setNom(name);
						medecin.setEmail(name+"@gmail.com");
						medecin.setSpecialite(Math.random()>0.5?"Cardio":"Dentiste");
						hospitalService.saveMedecin(medecin);
					});

			Patient patient = patientRepository.findById(1L).orElse(null);
			Patient patient1 = patientRepository.findByNom("Mohamed");

			Medecin medecin = medecinRepository.findByNom("Yasmine");

			RendezVous rendezVous = new RendezVous();
			rendezVous.setDate(new Date());
			rendezVous.setStatus(StatusRDV.PENDING);
			rendezVous.setMedecin(medecin);
			rendezVous.setPatient(patient);
			RendezVous savedRDV = rendezVousRepository.save(rendezVous);
			System.out.println(savedRDV.getId());

			RendezVous rendezVous1 = rendezVousRepository.findById(1L).orElse(null);
			Consultation consultation = new Consultation();
			consultation.setDateConsultation(new Date());
			consultation.setRendezVous(rendezVous1);
			consultation.setRapport("Rapport de la consultation .....");
			hospitalService.saveConsultation(consultation);
		};
	}
}
