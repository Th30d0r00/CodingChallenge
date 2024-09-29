package com.se.codingchallengejava;

import com.se.codingchallengejava.repositories.PraemieRepository;
import com.se.codingchallengejava.services.PostCodeService;
import com.se.codingchallengejava.services.PraemieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PraemieServiceFaktorTest {

    @Mock
    private PostCodeService postCodeService;

    @Mock
    private PraemieRepository praemieRepository;

    @InjectMocks
    private PraemieService praemieService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        praemieService.initBundeslandFaktor();
        praemieService.initFahrzeugTypFaktor();
    }

    @Test
    public void testCalculatePraemie_Kleinwagen_Bayern() {
        when(postCodeService.getBundesLand(80336)).thenReturn("Bayern"); // PLZ München, Bayern
        double result = praemieService.calculatePraemie(3000, 80336, "Kleinwagen");
        assertEquals(0.9, result); // faktorKilometer = 0.5, faktorBundesland = 1.8, faktorFahrzeugTyp = 1.0 -> 0.5 * 1.8 * 1.0 = 0.9
    }

    @Test
    public void testCalculatePraemie_Mittelklasse_Niedersachsen() {
        when(postCodeService.getBundesLand(30159)).thenReturn("Niedersachsen"); // PLZ Hannover, Niedersachsen
        double result = praemieService.calculatePraemie(8000, 30159, "Mittelklassewagen");
        assertEquals(1.87, result); // faktorKilometer = 1.0, faktorBundesland = 1.7, faktorFahrzeugTyp = 1.1 -> 1.0 * 1.7 * 1.1 = 1.87
    }
}
