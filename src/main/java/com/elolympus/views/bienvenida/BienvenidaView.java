package com.elolympus.views.pruebas;

import com.elolympus.views.MainLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import jakarta.annotation.security.PermitAll;

@PageTitle("Prueba")
@Route(value = "Prueba", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@PermitAll
public class pruebaView extends VerticalLayout {

    private TextField name;
    private Button sayHello;

    public pruebaView() {
        setAlignItems(Alignment.CENTER); // Centra los componentes
        setJustifyContentMode(JustifyContentMode.CENTER); // Centra los componentes
        setSizeFull(); // Hace que el layout ocupe toda la pantalla

        H1 welcomeMessage = new H1("Bienvenido al Sistema Comercial");
        Paragraph instruction = new Paragraph("Selecciona una opción del menú para comenzar.");

        add(welcomeMessage, instruction);
    }
}



