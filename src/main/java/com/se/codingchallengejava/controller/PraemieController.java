package com.se.codingchallengejava.controller;

import com.se.codingchallengejava.services.PraemieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/praemie")
public class PraemieController {

    private PraemieService praemieService;

    @Autowired
    public PraemieController(PraemieService praemieService) {
        this.praemieService = praemieService;
    }

    @PostMapping("/calculate")
    public double calculatePraemie(
            @RequestParam int geschaetzteKilometer,
            @RequestParam int postleitzahlZulassungsstelle,
            @RequestParam String fahrzeugtyp) {
        return praemieService.calculatePraemie(geschaetzteKilometer, postleitzahlZulassungsstelle, fahrzeugtyp);
    }
}
