package com.se.codingchallengejava;

import com.se.codingchallengejava.services.PostCodeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostCodeServiceTest {

    private PostCodeService postCodeService;

    @BeforeEach
    public void setUp() {
        postCodeService = new PostCodeService();
    }

    @Test
    public void testGetBundesLand() {
        // Test für existierende Postleitzahlen
        assertEquals("Nordrhein-Westfalen", postCodeService.getBundesLand(51105));
        assertEquals("Bayern", postCodeService.getBundesLand(80336));
        assertEquals("Hessen", postCodeService.getBundesLand(60311));
        assertEquals("Berlin", postCodeService.getBundesLand(10115));
        assertEquals("Hamburg", postCodeService.getBundesLand(20095));
        assertEquals("Baden-Württemberg", postCodeService.getBundesLand(70173));
        assertEquals("Niedersachsen", postCodeService.getBundesLand(30159));
        assertEquals("Rheinland-Pfalz", postCodeService.getBundesLand(55116));
        assertEquals("Sachsen", postCodeService.getBundesLand(1067));
        assertEquals("Schleswig-Holstein", postCodeService.getBundesLand(24103));
        assertEquals("Saarland", postCodeService.getBundesLand(66111));
        assertEquals("Brandenburg", postCodeService.getBundesLand(14467));
        assertEquals("Mecklenburg-Vorpommern", postCodeService.getBundesLand(18055));
        assertEquals("Sachsen-Anhalt", postCodeService.getBundesLand(39104));
        assertEquals("Thüringen", postCodeService.getBundesLand(99084));
        assertEquals("Bremen", postCodeService.getBundesLand(28195));
    }
}
