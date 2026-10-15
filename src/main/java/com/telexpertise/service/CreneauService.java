package com.telexpertise.service;

import com.telexpertise.dao.CreneauDAO;
import com.telexpertise.enums.StatutCreneau;
import com.telexpertise.model.Creneau;
import com.telexpertise.model.Specialiste;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class CreneauService {

    // Les 6 créneaux fixes du brief (début de chaque créneau de 30 min)
    private static final LocalTime[] HEURES = {
            LocalTime.of(9, 0), LocalTime.of(9, 30), LocalTime.of(10, 0),
            LocalTime.of(10, 30), LocalTime.of(11, 0), LocalTime.of(11, 30)
    };

    private CreneauDAO creneauDAO = new CreneauDAO();

    // Tous les créneaux du spécialiste (les génère si besoin)
    public List<Creneau> creneauxDuSpecialiste(Specialiste specialiste) {
        genererSiBesoin(specialiste);
        return creneauDAO.findBySpecialiste(specialiste);
    }

    // Créneaux disponibles ET futurs uniquement (Stream API)
    public List<Creneau> creneauxDisponibles(Specialiste specialiste) {
        return creneauxDuSpecialiste(specialiste).stream()
                .filter(c -> c.getStatut() == StatutCreneau.DISPONIBLE)
                .filter(c -> estFutur(c))
                .collect(Collectors.toList());
    }

    // Un créneau est passé si sa date + heure de début est avant maintenant
    public boolean estFutur(Creneau creneau) {
        LocalDateTime debut = LocalDateTime.of(creneau.getDate(), creneau.getHeureDebut());
        return debut.isAfter(LocalDateTime.now());
    }

    public Creneau trouverParId(Long id) {
        return creneauDAO.findById(id);
    }

    // Réserver : le créneau devient INDISPONIBLE
    public void reserver(Creneau creneau) {
        creneau.setStatut(StatutCreneau.INDISPONIBLE);
        creneauDAO.update(creneau);
    }

    // Annuler : le créneau redevient DISPONIBLE
    public void liberer(Creneau creneau) {
        creneau.setStatut(StatutCreneau.DISPONIBLE);
        creneauDAO.update(creneau);
    }

    // Crée les créneaux des 7 prochains jours s'il n'y en a pas encore
    private void genererSiBesoin(Specialiste specialiste) {
        List<Creneau> existants = creneauDAO.findBySpecialiste(specialiste);

        for (int jour = 0; jour < 7; jour++) {
            LocalDate date = LocalDate.now().plusDays(jour);

            boolean dejaCree = existants.stream().anyMatch(c -> c.getDate().equals(date));
            if (dejaCree) {
                continue;
            }

            for (LocalTime heure : HEURES) {
                Creneau creneau = new Creneau();
                creneau.setSpecialiste(specialiste);
                creneau.setDate(date);
                creneau.setHeureDebut(heure);
                creneau.setHeureFin(heure.plusMinutes(30));
                creneau.setStatut(StatutCreneau.DISPONIBLE);
                creneauDAO.save(creneau);
            }
        }
    }
}