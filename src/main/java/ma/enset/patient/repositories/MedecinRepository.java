package ma.enset.patient.repositories;

import ma.enset.patient.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface MedecinRepository extends JpaRepository<Medecin,Long> {
    public List<Medecin> findByNom(String n);
    List<Medecin> findByDateNaissanceBetween(Date d1, Date d2);
}
