package com.elolympus.views.Administracion;

import com.elolympus.data.Administracion.Persona;
import com.elolympus.data.SamplePerson;
import com.elolympus.services.PersonaService;
import com.elolympus.views.editartabla.EditarTablaView;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class PersonasView extends PersonasUI implements BeforeEnterObserver {

    private final PersonaService PersonaService;
    private BeanValidationBinder<Persona> binder;
    private Persona persona;

    public PersonasView(PersonaService PersonaService) {
        this.PersonaService = PersonaService;
        try {
            // Configure Form
            binder = new BeanValidationBinder<>(Persona.class);
            // Bind fields. This is where you'd define e.g. validation rules
            binder.bindInstanceFields(this);
        }catch (Exception e){
            System.out.println("ERRORRRR: " + e.getMessage());
        }
        onRefresh();
    }

    @Override
    public void onBtnFiltrar() {

    }

    @Override
    public void onRefresh() {
        String dni = txtDni.getValue();
        String apellidos = txtApellidos.getValue();
        String nombres = txtNombres.getValue();
        listPersonas.clear();
        //listPersonas.addAll(Services.getAlumno().listByCicloActual(ciclo.id,dni,apellidos,nombres));
        gridPersonas.setItems(query -> PersonaService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query))).stream());
        gridPersonas.getDataProvider().refreshAll();

    }

    @Override
    public void onBtnSave() {
        try {
            if (this.persona == null) {
                this.persona = new Persona();
            }
            binder.writeBean(this.persona);
            PersonaService.update(this.persona);
            clearForm();
            refreshGrid();
            Notification.show("Datos actualizados");
            UI.getCurrent().navigate(PersonasView.class);
            onRefresh();
        } catch (ObjectOptimisticLockingFailureException exception) {
            Notification n = Notification.show(
                    "Error al actualizar los datos. Alguien más actualizó el registro mientras usted hacía cambios.");
            n.setPosition(Notification.Position.MIDDLE);
            n.addThemeVariants(NotificationVariant.LUMO_ERROR);
        } catch (ValidationException validationException) {
            Notification.show("No se pudieron actualizar los datos. Compruebe nuevamente que todos los valores sean válidos.");
        }
    }

    @Override
    public void onBtnCancel() {
        this.clearForm();
        this.refreshGrid();
    }

    @Override
    public void onBtnDelete() {
        try {
            if (this.persona == null) {
                this.persona = new Persona();
            }else{this.persona.setActivo(false);}

            binder.writeBean(this.persona);
            PersonaService.update(this.persona);
            clearForm();
            refreshGrid();
            Notification.show("Persona Eliminada");
            UI.getCurrent().navigate(PersonasView.class);
            onRefresh();
        } catch (ObjectOptimisticLockingFailureException exception) {
            Notification n = Notification.show(
                    "Error al Eliminar. Alguien más actualizó el registro mientras usted hacía cambios.");
            n.setPosition(Notification.Position.MIDDLE);
            n.addThemeVariants(NotificationVariant.LUMO_ERROR);
        } catch (ValidationException validationException) {
            Notification.show("No se pudo Eliminar a la persona. Compruebe nuevamente.");
        }
    }

    @Override
    public void asSingleSelect(Persona persona) {
        if (persona != null) {
            UI.getCurrent().navigate(String.format(this.PERSONA_EDIT_ROUTE_TEMPLATE, persona.getId()));
        } else {
            clearForm();
            UI.getCurrent().navigate(PersonasView.class);
        }
    }

    private void refreshGrid() {
        gridPersonas.select(null);
        gridPersonas.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Persona value) {
        this.persona = value;
        binder.readBean(this.persona);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<Long> PersonaId = event.getRouteParameters().get(this.PERSONA_ID).map(Long::parseLong);
        if (PersonaId.isPresent()) {
            Optional<Persona> samplePersonFromBackend = PersonaService.get(PersonaId.get());
            if (samplePersonFromBackend.isPresent()) {
                populateForm(samplePersonFromBackend.get());
            } else {
                Notification.show(
                        String.format("The requested samplePerson was not found, ID = %s", PersonaId.get()), 3000,
                        Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                refreshGrid();
                event.forwardTo(EditarTablaView.class);
            }
        }
    }
}