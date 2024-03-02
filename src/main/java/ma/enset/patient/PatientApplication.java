package ma.enset.patient;

import ma.enset.patient.entities.Medecin;
import ma.enset.patient.entities.Patient;
import ma.enset.patient.repositories.MedecinRepository;
import ma.enset.patient.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class PatientApplication implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private MedecinRepository medecinRepository;
    public static void main(String[] args) {
        SpringApplication.run(PatientApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        medecinRepository.save(
                new Medecin(null,"Naime",new Date()));
        medecinRepository.save(
                new Medecin(null,"Toto",new Date()));
        medecinRepository.save(
                new Medecin(null,"fifi",new Date()));
        medecinRepository.save(
                new Medecin(null,"rara",new Date()));
        //CONSULTER TOUS LES MEDECINS
        List<Medecin> medecins=medecinRepository.findAll();
        medecins.forEach(m->{
            System.out.println(m.getId());
            System.out.println(m.getNom());
            System.out.println(m.getDateNaissance());
        });
        System.out.println("chercher un medecin by id");
        Medecin medecn = medecinRepository.findById(1L).orElse(null);
        if(medecn!=null){
            System.out.println(medecn.getNom());
        }
        //MAJ d'un medecin
        System.out.println("MAJ le medecin");
        medecn.setNom("Ilyas");
        medecinRepository.save(medecn);
        //Supprimer un medecin
        medecinRepository.deleteById(1L);
        //Consulter un medecin
        System.out.println("chercher un medecin by nom");
        List<Medecin> medecin = medecinRepository.findByNom("fifi");
        medecin.forEach(n->{
            System.out.println(n.getId());
            System.out.println(n.getDateNaissance());
        });


      /*  patientRepository.save(
                new Patient(null,"Ibtihal",new Date(),Math.random()>0.5?true:false,(int)(Math.random()*100)));
        patientRepository.save(
                new Patient(null,"meryem",new Date(),Math.random()>0.5?true:false,(int)(Math.random()*100)));
        patientRepository.save(
                new Patient(null,"zineb",new Date(),Math.random()>0.5?true:false,(int)(Math.random()*100)));
        patientRepository.save(
                new Patient(null,"yassine",new Date(),Math.random()>0.5?true:false,(int)(Math.random()*100)));
        //CONSULTER TOUS LES Patients
        System.out.println("lister tous les patients");
        List<Patient> patients=patientRepository.findAll();
        patients.forEach(p->{
            System.out.println(p.getId());
            System.out.println(p.getNom());
            System.out.println(p.getDateNaissance());
            System.out.println(p.getScore());
        });
        System.out.println("chercher un patient by id");
        //Consulter un patient
        Patient patient = patientRepository.findById(1L).orElse(null);
        if(patient!=null){
            System.out.println(patient.getNom());
        }
        System.out.println("MAJ un patient");
        patient.setNom("Ilyas");
        patientRepository.save(patient);
        //Supprimer un patient
        patientRepository.deleteById(3L);*/
        for(int i = 0; i<50; i++){
            patientRepository.save(
                    new Patient(null,"Ibtihal",new Date(),Math.random()>0.5?true:false,(int)(Math.random()*100)));
        }
        Page<Patient> patients = patientRepository.findAll(PageRequest.of(1,10));
        System.out.println("total pages : "+patients.getTotalPages());
        System.out.println("Total element :"+patients.getTotalElements());
        System.out.println("Num Page :"+patients.getNumber());
        List<Patient> content = patients.getContent();
        Page<Patient> byMalade = patientRepository.findByMalade(true,PageRequest.of(0,5));
        //List<Patient> patientList=patientRepository.chercherPatients("%I%",60);
        byMalade.forEach((p->{
            System.out.println("================================================");
            System.out.println(p.getId());
            System.out.println(p.getNom());
            System.out.println(p.getScore());
            System.out.println(p.getDateNaissance());
            System.out.println(p.isMalade());
        }));
        System.out.println("**************************");
        Patient patient=patientRepository.findById(1L).orElse(null);
        if(patient!=null){
            System.out.println(patient.getNom());
            System.out.println(patient.isMalade());
        }
        patient.setScore(870);
        patientRepository.save(patient);
        patientRepository.deleteById(1L);

    }
}
