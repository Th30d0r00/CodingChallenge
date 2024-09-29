package com.se.codingchallengejava.services;

import com.se.codingchallengejava.Entities.Praemie;
import com.se.codingchallengejava.repositories.PraemieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/*
* Service zum Berechnen der Prämie
* */

@Service
public class PraemieService {

private Map<String, Double> bundeslandFaktor;
private Map<String, Double> fahrzeugTypFaktor;
private final PostCodeService postCodeService;
private final PraemieRepository praemieRepository;

@Autowired
public PraemieService(PostCodeService postCodeService, PraemieRepository praemieRepository) {
    this.postCodeService = postCodeService;
    this.praemieRepository = praemieRepository;
    initBundeslandFaktor();
    initFahrzeugTypFaktor();
}

    public double calculatePraemie(int geschaetzteKilometer, int postleitzahlZulassungsstelle, String fahrzeugtyp) {
        double faktorKilometer;
        double faktorBundesland;
        double faktorFahrzeugTyp;

        if (geschaetzteKilometer < 5001) {
            faktorKilometer = 0.5;
        } else if (geschaetzteKilometer < 10001) {
            faktorKilometer = 1.0;
        } else if (geschaetzteKilometer < 20001) {
            faktorKilometer = 1.5;
        } else {
            faktorKilometer = 2;
        }

        String bundesland = postCodeService.getBundesLand(postleitzahlZulassungsstelle);
        faktorBundesland = bundeslandFaktor.getOrDefault(bundesland, 0.0);

        if (faktorBundesland == 0.0) {
            throw new IllegalArgumentException("Postleitzahl nicht gefunden.");
        }

        faktorFahrzeugTyp = fahrzeugTypFaktor.getOrDefault(fahrzeugtyp, 0.0);

        double berechnetePraemie = faktorKilometer * faktorBundesland * faktorFahrzeugTyp;

        Praemie praemie = new Praemie(geschaetzteKilometer, postleitzahlZulassungsstelle, fahrzeugtyp, berechnetePraemie);
        praemieRepository.save(praemie);

        return berechnetePraemie;
    }

    /*
    * Initialisierung der Faktoren für Bundesland und Fahrzeugtyp. Der Bundeslandfaktor richtet sich nach der Fläche
    * eines Bundeslandes. Der Fahrzeugtyp Faktor richtet sich nach der Größe des Fahrzeugs und dem Verbrauch.
    * */

    public void initBundeslandFaktor() {
        bundeslandFaktor = new HashMap<>();
        bundeslandFaktor.put("Bayern", 1.8);
        bundeslandFaktor.put("Niedersachsen", 1.7);
        bundeslandFaktor.put("Baden-Württemberg", 1.6);
        bundeslandFaktor.put("Nordrhein-Westfalen", 1.5);
        bundeslandFaktor.put("Brandenburg", 1.4);
        bundeslandFaktor.put("Mecklenburg-Vorpommern", 1.3);
        bundeslandFaktor.put("Hessen", 1.2);
        bundeslandFaktor.put("Sachsen-Anhalt", 1.1);
        bundeslandFaktor.put("Rheinland-Pfalz", 1.0);
        bundeslandFaktor.put("Sachsen", 0.9);
        bundeslandFaktor.put("Thüringen", 0.8);
        bundeslandFaktor.put("Schleswig-Holstein", 0.7);
        bundeslandFaktor.put("Saarland", 0.6);
        bundeslandFaktor.put("Berlin", 0.5);
        bundeslandFaktor.put("Hamburg", 0.4);
        bundeslandFaktor.put("Bremen", 0.3);
    }

    public void initFahrzeugTypFaktor() {
        fahrzeugTypFaktor = new HashMap<>();
        fahrzeugTypFaktor.put("Kleinwagen", 1.0);
        fahrzeugTypFaktor.put("Mittelklassewagen", 1.1);
        fahrzeugTypFaktor.put("Oberklassewagen", 1.3);
        fahrzeugTypFaktor.put("SUV", 1.4);
        fahrzeugTypFaktor.put("Sportwagen", 1.5);
        fahrzeugTypFaktor.put("Elektroauto", 0.8);
        fahrzeugTypFaktor.put("Hybridfahrzeug", 0.9);
        fahrzeugTypFaktor.put("Lieferwagen", 1.2);
        fahrzeugTypFaktor.put("LKW", 1.6);
        fahrzeugTypFaktor.put("Motorrad", 0.7);
    }
}
