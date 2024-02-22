package com.elolympus.views.Administracion;

import com.elolympus.data.Administracion.Persona;
import com.elolympus.services.PersonaService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import java.util.List;


public class PersonasView2 extends VerticalLayout implements BeforeEnterObserver {
//
//    private final PersonaService PersonaService;
//    private BeanValidationBinder<Persona> binder;
//    private Persona persona;
//
//    public PersonasView2(PersonaService PersonaService) {
//        this.PersonaService = PersonaService;
//        try {
//            // Configure Form
//            binder = new BeanValidationBinder<>(Persona.class);
//            // Bind fields. This is where you'd define e.g. validation rules
//            binder.bindInstanceFields(this);
//        }catch (Exception e){
//            System.out.println("ERRORRRR: " + e.getMessage());
//        }
//        onRefresh();
//    }
//
//    @Override
//    public void onBtnFiltrar() {
//        onRefresh();
//
//    }
//
//    @Override
//    public void onRefresh() {
//        String dni = txtDni.getValue();
//        String apellidos = txtApellidos.getValue();
//        String nombres = txtNombres.getValue();
//        List<Persona> personasActivas = PersonaService.numDocumnetoNombresApellidosActivosContainsIgnoreCase(dni,nombres,apellidos);
//        gridPersonas.setItems(personasActivas);
//        gridPersonas.getDataProvider().refreshAll();
//
//    }
//
//    @Override
//    public void onBtnSave() {
//        try {
//            if (this.persona == null) {
//                this.persona = new Persona();
//            }
//            binder.writeBean(this.persona);
//            PersonaService.update(this.persona);
//            clearForm();
//            refreshGrid();
//            Notification.show("Datos actualizados");
//            UI.getCurrent().navigate(PersonasView2.class);
//        } catch (ObjectOptimisticLockingFailureException exception) {
//            Notification n = Notification.show(
//                    "Error al actualizar los datos. Alguien más actualizó el registro mientras usted hacía cambios.");
//            n.setPosition(Notification.Position.MIDDLE);
//            n.addThemeVariants(NotificationVariant.LUMO_ERROR);
//        } catch (ValidationException validationException) {
//            Notification.show("No se pudieron actualizar los datos. Compruebe nuevamente que todos los valores sean válidos.");
//        }
//    }
//
//    @Override
//    public void onBtnCancel() {
//        this.clearForm();
//        this.refreshGrid();
//    }
//
//    @Override
//    public void onBtnDelete() {
//        try {
//            if (this.persona == null) {
//                this.persona = new Persona();
//            }else{this.persona.setActivo(false);}
//
//            binder.writeBean(this.persona);
//            PersonaService.update(this.persona);
//            clearForm();
//            refreshGrid();
//            Notification.show("Persona Eliminada");
//            UI.getCurrent().navigate(PersonasView2.class);
//        } catch (ObjectOptimisticLockingFailureException exception) {
//            Notification n = Notification.show(
//                    "Error al Eliminar. Alguien más actualizó el registro mientras usted hacía cambios.");
//            n.setPosition(Notification.Position.MIDDLE);
//            n.addThemeVariants(NotificationVariant.LUMO_ERROR);
//        } catch (ValidationException validationException) {
//            Notification.show("No se pudo Eliminar a la persona. Compruebe nuevamente.");
//        }
//    }
//
//    @Override
//    public void asSingleSelect(Persona persona, Button btnSave) {
//        if (persona != null) {
//            btnSave.setText("Actualizar");
//            UI.getCurrent().navigate(String.format(this.PERSONA_EDIT_ROUTE_TEMPLATE, persona.getId()));
//        } else {
//            clearForm();
//            UI.getCurrent().navigate(PersonasView2.class);
//            btnSave.setText("Guardar");
//        }
//    }
//
//    private void refreshGrid() {
//        gridPersonas.select(null);
//        List<Persona> personasActivas = PersonaService.numDocumnetoNombresApellidosActivosContainsIgnoreCase("","","");
//        gridPersonas.setItems(personasActivas);
//        //gridPersonas.getDataProvider().refreshAll();
//    }
//
//    private void clearForm() {
//        populateForm(null);
//        txtDni.setValue("");
//        txtApellidos.setValue("");
//        txtNombres.setValue("");
//    }
//
//    private void populateForm(Persona value) {
//        this.persona = value;
//        binder.readBean(this.persona);
//    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
//        Optional<Long> PersonaId = event.getRouteParameters().get(this.PERSONA_ID).map(Long::parseLong);
//        if (PersonaId.isPresent()) {
//            Optional<Persona> samplePersonFromBackend = PersonaService.get(PersonaId.get());
//            if (samplePersonFromBackend.isPresent()) {
//                populateForm(samplePersonFromBackend.get());
//            } else {
//                Notification.show(
//                        String.format("The requested samplePerson was not found, ID = %s", PersonaId.get()), 3000,
//                        Notification.Position.BOTTOM_START);
//                // when a row is selected but the data is no longer available,
//                // refresh grid
//                refreshGrid();
//                event.forwardTo(EditarTablaView.class);
//            }
//        }
    }
}