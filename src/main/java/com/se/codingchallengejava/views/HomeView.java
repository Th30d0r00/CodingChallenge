package com.se.codingchallengejava.views;

import com.se.codingchallengejava.services.PraemieService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
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
            if (checkFahrzeugTyp() && checkIsEmpty() && zahlCheck()) {
                int kilometer = Integer.parseInt(geschaetzteKilometer.getValue());
                int postleitzahl = Integer.parseInt(postleitzahlZulassungsstelle.getValue());
                String fahrzeug = fahrzeugtyp.getValue();
                try {
                    praemieService.calculatePraemie(kilometer, postleitzahl, fahrzeug);
                } catch (IllegalArgumentException ex) {
                    Notification.show("Postleitzahl nicht gefunden. Versuche eine andere Postleitzahl.");
                }
            }
            clearForm();
        });

    }

    private void clearForm() {
        geschaetzteKilometer.clear();
        postleitzahlZulassungsstelle.clear();
        fahrzeugtyp.clear();
    }

    public boolean checkIsEmpty() {
        boolean status = true;
        if (geschaetzteKilometer.isEmpty() || postleitzahlZulassungsstelle.isEmpty() || fahrzeugtyp.isEmpty()) {
            Notification.show("Bitte fülle alle Felder aus.");
            status = false;
        }
        return status;
    }

    public boolean zahlCheck() {
        boolean status = true;
        try {
            Integer.parseInt(geschaetzteKilometer.getValue());
            Integer.parseInt(postleitzahlZulassungsstelle.getValue());
        } catch (NumberFormatException ex) {
            Notification.show("Bitte gib eine gültige Zahl ein.");
            status = false;
        }
        return status;
    }

    public boolean checkFahrzeugTyp() {
        boolean status = true;
        if (!fahrzeugtyp.getValue().equals("Kleinwagen") && !fahrzeugtyp.getValue().equals("Mittelklassewagen") &&
                !fahrzeugtyp.getValue().equals("Oberklassewagen") && !fahrzeugtyp.getValue().equals("SUV") &&
                !fahrzeugtyp.getValue().equals("Sportwagen") && !fahrzeugtyp.getValue().equals("Elektroauto") &&
                !fahrzeugtyp.getValue().equals("Hybridfahrzeug") && !fahrzeugtyp.getValue().equals("Lieferwagen") &&
                !fahrzeugtyp.getValue().equals("LKW") && !fahrzeugtyp.getValue().equals("Motorrad")){
            Notification.show("Bitte gib einen gültigen Fahrzeugtyp ein.");
            status = false;
        }
        return status;
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
