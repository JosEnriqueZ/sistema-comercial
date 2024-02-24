package com.elolympus.views.Administracion;

import com.elolympus.data.Administracion.Persona;
import com.elolympus.data.Administracion.Rol;
import com.elolympus.services.RolService;
import com.elolympus.views.MainLayout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;

import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@PageTitle("Roles")
@Route(value = "rol/:RolID?/:action?(edit)", layout = MainLayout.class)
@PermitAll
public class RolesView extends Div implements BeforeEnterObserver {
    private final RolService rolService;
    private BeanValidationBinder<Rol> binder;
    private Rol rol;

    public final String ROL_ID = "RolID";
    public final String ROL_EDIT_ROUTE_TEMPLATE = "rol/%s/edit";
    private TextField area = new TextField("Área");
    private TextField cargo = new TextField("Cargo");
    private TextField descripcion = new TextField("Descripción");
    private Checkbox canCreate = new Checkbox("Puede Crear");
    private Checkbox canRead = new Checkbox("Puede Leer");
    private Checkbox canUpdate = new Checkbox("Puede Actualizar");
    private Checkbox canDelete = new Checkbox("Puede Eliminar");

    private Button save = new Button("Guardar");
    private Button cancel = new Button("Cancelar");
    private Button delete = new Button("Eliminar", VaadinIcon.TRASH.create());

    private FormLayout formLayout = new FormLayout();

    private Grid<Rol> grid = new Grid<>(Rol.class);
    @Autowired
    public RolesView(RolService rolService) {
        this.rolService = rolService;
        try {
            // Configure Form
            binder = new BeanValidationBinder<>(Rol.class);
            // Bind fields. This is where you'd define e.g. validation rules
            binder.bindInstanceFields(this);
        }catch (Exception e){
            System.out.println("ERRORRRR: " + e.getMessage());
        }
        addClassNames("roles-view");
        setSizeFull();
        configureGrid();
        SplitLayout splitLayout = new SplitLayout(createGridLayout(), createEditorLayout());
        splitLayout.setSizeFull();
        add(splitLayout);
        updateList();
    }
    private void configureGrid() {

        grid = new Grid<>();
        grid.setClassName("grilla");
        grid.setHeight("86%");
        grid.addColumn(Rol::getArea).setHeader("Área");
        grid.addColumn(Rol::getCargo).setHeader("Cargo");
        grid.addColumn(Rol::getDescripcion).setHeader("Descripción");
        grid.addColumn(role -> role.getCanCreate() ? "Sí" : "No").setHeader("Puede Crear");
        grid.addColumn(role -> role.getCanRead() ? "Sí" : "No").setHeader("Puede Leer");
        grid.addColumn(role -> role.getCanUpdate() ? "Sí" : "No").setHeader("Puede Actualizar");
        grid.addColumn(role -> role.getCanDelete() ? "Sí" : "No").setHeader("Puede Eliminar");
        grid.asSingleSelect().addValueChangeListener(event -> editRole(event.getValue()));
    }
    private Component createEditorLayout() {
        Div editorDiv = new Div();
        editorDiv.setHeightFull();
        editorDiv.setClassName("editor-layout");
        Div div = new Div();
        div.setClassName("editor");
        editorDiv.add(div);
        formLayout.add(area, cargo, descripcion, canCreate, canRead, canUpdate, canDelete);

        save.addClickListener(event -> save());
        cancel.addClickListener(event -> clearForm());
        delete.addClickListener(event -> delete());

        delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        div.add(formLayout);
        createButtonLayout(editorDiv);
        return editorDiv;
    }
    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("button-layout");
        buttonLayout.add(save, cancel,delete);
        editorLayoutDiv.add(buttonLayout);
    }

    private Component createGridLayout() {
        Div wrapper = new Div();
        wrapper.setClassName("grid-wrapper");
        wrapper.setSizeFull();
        wrapper.setHeightFull();
        wrapper.add(grid);
        return wrapper;
    }

    private void updateList() {
        List<Rol> roles = rolService.findAll(); // Asumiendo que existe un método findAll en RolService
        grid.setItems(roles);
    }
    private void editRole(Rol rol) {
        if (rol == null) {
            clearForm();
        } else {
            this.rol = rol;
            binder.readBean(this.rol);
        }
    }
    private void save() {
        if (this.rol == null) {
            this.rol = new Rol();
        }
        try {
            binder.writeBean(this.rol);
            rolService.save(this.rol);
            updateList();
            clearForm();
            Notification.show("Rol guardado con éxito");
        } catch (ValidationException e) {
            Notification.show("Error de validación: " + e.getMessage()).addThemeVariants(NotificationVariant.LUMO_ERROR);
        } catch (Exception e) {
            Notification.show("Error al guardar el rol: " + e.getMessage()).addThemeVariants(NotificationVariant.LUMO_ERROR);
        }
    }

    private void delete() {
        if (this.rol != null) {
            rolService.delete(this.rol); // Asumiendo que existe un método delete en RolService
            updateList();
            clearForm();
            Notification.show("Rol eliminado");
        }
    }

    private void clearForm() {
        this.rol = null;
        binder.readBean(new Rol());
    }
    private void populateForm(Rol rol) {
        // Establece los valores de los campos del formulario con las propiedades del rol
        this.rol = rol; // Asegúrate de que la variable 'rol' esté declarada en tu clase
        binder.setBean(rol);

        if (rol != null) {
            area.setValue(rol.getArea() != null ? rol.getArea() : "");
            cargo.setValue(rol.getCargo() != null ? rol.getCargo() : "");
            descripcion.setValue(rol.getDescripcion() != null ? rol.getDescripcion() : "");
            canCreate.setValue(rol.getCanCreate() != null && rol.getCanCreate());
            canRead.setValue(rol.getCanRead() != null && rol.getCanRead());
            canUpdate.setValue(rol.getCanUpdate() != null && rol.getCanUpdate());
            canDelete.setValue(rol.getCanDelete() != null && rol.getCanDelete());
        } else {
            clearForm();
        }
    }
    private void refreshGrid() {
        List<Rol> roles = rolService.findAll(); // Utiliza el método 'findAll' de tu RolService para obtener todos los roles
        grid.setItems(roles); // Actualiza los ítems del grid con la lista de roles
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<Long> rolId = event.getRouteParameters().get(this.ROL_ID).map(Long::parseLong);
        if (rolId.isPresent()) {
            Optional<Rol> rolFromBackend = rolService.findById(rolId.get());
            if (rolFromBackend.isPresent()) {
                populateForm(rolFromBackend.get());
            } else {
                Notification.show(String.format("El rol solicitado no fue encontrado, ID = %s", rolId.get()), 3000, Notification.Position.BOTTOM_START);
                // Cuando se selecciona una fila pero los datos ya no están disponibles,
                // refrescar la lista y redirigir, si es necesario.
                refreshGrid();
                event.forwardTo(RolesView.class);
            }
        } else {
            // No hay un ID de Rol en la URL, asegúrate de que la vista esté en un estado limpio
            clearForm();
        }
    }
}
    


