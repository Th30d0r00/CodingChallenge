package com.se.codingchallengejava;

import com.se.codingchallengejava.repositories.PraemieRepository;
import com.se.codingchallengejava.services.PostCodeService;
import com.se.codingchallengejava.services.PraemieService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PraemieServiceDatabaseTest {

    @Autowired
    private PraemieRepository praemieRepository;

    @Autowired
    private PraemieService praemieService;

    @AfterEach
    public void tearDown() {
        praemieRepository.deleteAll();
    }

    @Test
    public void testCalculatePraemie() {
        int geschaetzteKilometer = 1000;
        int postleitzahlZulassungsstelle = 51105;
        String fahrzeugtyp = "Kleinwagen";

        double praemie = praemieService.calculatePraemie(geschaetzteKilometer, postleitzahlZulassungsstelle, fahrzeugtyp);

        assertEquals(0.75, praemie);
        assertEquals(1, praemieRepository.count());
    }
}
