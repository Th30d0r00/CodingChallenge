package com.se.codingchallengejava.services;

import com.se.codingchallengejava.Entities.Praemie;
import com.se.codingchallengejava.repositories.PraemieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PraemieService {

private Map<String, Double> bundeslandFaktor;
private Map<String, Double> fahrzeugTypFaktor;
private final CSVReaderService csvReaderService;
private final PraemieRepository praemieRepository;

@Autowired
public PraemieService(CSVReaderService csvReaderService, PraemieRepository praemieRepository) {
    this.csvReaderService = csvReaderService;
    this.praemieRepository = praemieRepository;
    initBunderslandFaktor();
    initfahrzeugTypFaktor();
}

    public double calculatePraemie(int geschaetzteKilometer, int postleitzahlZulassungsstelle, String fahrzeugtyp) {
        double faktorKilometer;
        double faktorBundesland = 0;
        double faktorFahrzeugTyp = 0;

        if (geschaetzteKilometer < 5001) {
            faktorKilometer = 0.5;
        } else if (geschaetzteKilometer < 10001) {
            faktorKilometer = 1.0;
        } else if (geschaetzteKilometer < 20001) {
            faktorKilometer = 1.5;
        } else {
            faktorKilometer = 2;
        }

        csvReaderService.loadCSV("src/main/resources/postcodes.csv");

        for (Map.Entry<String, Double> entry : bundeslandFaktor.entrySet()) {
            if (entry.getKey().equals(csvReaderService.getBundesLand(postleitzahlZulassungsstelle))) {
                faktorBundesland = entry.getValue();
            }
        }

        for (Map.Entry<String, Double> entry : fahrzeugTypFaktor.entrySet()) {
            if (entry.getKey().equals(fahrzeugtyp)) {
                faktorFahrzeugTyp = entry.getValue();
            }
        }

        double berechnetePraemie = faktorKilometer * faktorBundesland * faktorFahrzeugTyp;

        Praemie praemie = new Praemie(geschaetzteKilometer, postleitzahlZulassungsstelle, fahrzeugtyp, berechnetePraemie);
        praemieRepository.save(praemie);

        return berechnetePraemie;
    }

    public void initBunderslandFaktor() {
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

    private void initfahrzeugTypFaktor() {
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
