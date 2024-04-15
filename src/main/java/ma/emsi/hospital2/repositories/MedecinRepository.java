package ma.emsi.hospital2.repositories;

import ma.emsi.hospital2.entities.Medecin;
import ma.emsi.hospital2.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    Medecin findByNom(String name);
}
