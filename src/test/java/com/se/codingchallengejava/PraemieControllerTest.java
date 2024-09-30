package com.se.codingchallengejava;

import com.se.codingchallengejava.services.PostCodeService;
import com.se.codingchallengejava.services.PraemieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class PraemieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PraemieService praemieService;


    @Test
    public void testCalculatePraemie() throws Exception {

        int geschaetzteKilometer = 3000;
        int postleitzahlZulassungsstelle = 80336;
        String fahrzeugtyp = "Kleinwagen";
        double erwartetePraemie = 0.9;

        when(praemieService.calculatePraemie(geschaetzteKilometer, postleitzahlZulassungsstelle, fahrzeugtyp)).
                thenReturn(erwartetePraemie);

        mockMvc.perform(post("/api/praemie/calculate")
                        .param("geschaetzteKilometer", String.valueOf(geschaetzteKilometer))
                        .param("postleitzahlZulassungsstelle", String.valueOf(postleitzahlZulassungsstelle))
                        .param("fahrzeugtyp", fahrzeugtyp))
                .andExpect(status().isOk())  // Überprüfen, ob der Status 200 OK ist
                .andExpect(content().string(String.valueOf(erwartetePraemie)));
    }

}
