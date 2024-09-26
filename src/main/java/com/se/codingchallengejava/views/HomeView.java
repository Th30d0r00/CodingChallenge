package com.se.codingchallengejava.views;

import com.se.codingchallengejava.services.PraemieService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;


@Route("")
public class HomeView extends VerticalLayout {

    private TextField geschaetzteKilometer;
    private TextField postleitzahlZulassungsstelle;
    private TextField fahrzeugtyp;
    private Button berechnen;

    public HomeView(PraemieService praemieService) {

        createHeading();
        createFields();

        berechnen.addClickListener(e -> {
            int kilometer = Integer.parseInt(geschaetzteKilometer.getValue());
            int postleitzahl = Integer.parseInt(postleitzahlZulassungsstelle.getValue());
            String fahrzeug = fahrzeugtyp.getValue();
            praemieService.calculatePraemie(kilometer, postleitzahl, fahrzeug);
        });

    }
    public void createHeading() {
        H2 title = new H2("Willkommen beim Versicherungsprämienrechner");
        Paragraph description = new Paragraph("Um die Prämie für deine Versicherung zu berechnen, benötigen wir einige Informationen von dir. " +
                "Bitte fülle das Formular aus und klicke auf 'Berechnen'.");
        add(title, description);
    }

    public void createFields() {
        geschaetzteKilometer = new TextField("Geschätzte Kilometer pro Jahr");
        geschaetzteKilometer.setWidth("50%");
        postleitzahlZulassungsstelle = new TextField("Postleitzahl der Zulassungsstelle");
        postleitzahlZulassungsstelle.setWidth("50%");
        fahrzeugtyp = new TextField("Fahrzeugtyp");
        fahrzeugtyp.setWidth("50%");
        berechnen = new Button("Berechnen");
        add(geschaetzteKilometer, postleitzahlZulassungsstelle, fahrzeugtyp, berechnen);
        setSizeFull();
        setAlignItems(Alignment.CENTER);
    }

}
