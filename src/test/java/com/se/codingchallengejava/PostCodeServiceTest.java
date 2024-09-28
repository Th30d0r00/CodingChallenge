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
    }
}
