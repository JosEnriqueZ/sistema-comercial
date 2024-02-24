package com.elolympus.views.Administracion;

import com.elolympus.data.Administracion.Persona;
import com.elolympus.data.Administracion.Rol;
import com.elolympus.data.Administracion.Usuario;
import com.elolympus.services.PersonaService;
import com.elolympus.services.RolService;
import com.elolympus.services.UsuarioService;
import com.elolympus.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Usuarios")
@Route(value = "usuario/:UsuarioID?/:action?(edit)", layout = MainLayout.class)
@PermitAll
public class UsuariosView extends Div {

    private final UsuarioService usuarioService;
    private final RolService rolService;
    private final PersonaService personaService;
    private BeanValidationBinder<Usuario> binder;
    private Usuario usuario;

    // Componentes UI
    private Grid<Usuario> gridUsuarios = new Grid<>(Usuario.class, false);
    private final TextField usuarioField = new TextField("Usuario");
    private final PasswordField passwordField = new PasswordField("Contraseña");
    private final TextField creadorField = new TextField("Creador");
    private final Checkbox activoCheckbox = new Checkbox("Activo");
    // Asumir la existencia de un ComboBox para Rol y Persona si es necesario
     private final ComboBox<Rol> rolComboBox = new ComboBox<>("Rol");
     private final ComboBox<Persona> personaComboBox = new ComboBox<>("Persona");

    private final Button save = new Button("Guardar");
    private final Button cancel = new Button("Cancelar");
    private final Button delete = new Button("Eliminar", VaadinIcon.TRASH.create());

    private final FormLayout formLayout = new FormLayout();

    @Autowired
    public UsuariosView(UsuarioService usuarioService, RolService rolService, PersonaService personaService) {
        this.usuarioService = usuarioService;
        this.rolService = rolService;
        this.personaService = personaService;
        try {
            // Configure Form
            binder = new BeanValidationBinder<>(Usuario.class);
            // Bind fields. This is where you'd define e.g. validation rules
            binder.bindInstanceFields(this);
        }catch (Exception e){
            System.out.println("ERRORRRR: " + e.getMessage());
        }
        addClassNames("usuario-view");
        setSizeFull();
        setupGrid();
        setupForm();
        SplitLayout layout = new SplitLayout(createGridLayout(), createEditorLayout());
        layout.setSizeFull();
        add(layout);
        refreshGrid();
    }

    private void setupGrid() {
        gridUsuarios = new Grid<>();
        gridUsuarios.setClassName("grilla");
        gridUsuarios.setHeight("86%");
        gridUsuarios.addColumn(Usuario::getUsuario).setHeader("Usuario");
        gridUsuarios.addColumn(usuario -> usuario.getRol() != null ? usuario.getRol().getCargo() : "").setHeader("Rol");
        gridUsuarios.addColumn(usuario -> usuario.getPersona() != null ? usuario.getPersona().getNombreCompleto() : "").setHeader("Persona");
        gridUsuarios.addColumn(Usuario::getCreador).setHeader("Creador");
        gridUsuarios.addColumn(Usuario::getActivo).setHeader("Activo");
        gridUsuarios.asSingleSelect().addValueChangeListener(event -> editUsuario(event.getValue()));
    }

    private Component createEditorLayout() {
        Div editorDiv = new Div();
        editorDiv.setHeightFull();
        editorDiv.setClassName("editor-layout");
        Div div = new Div();
        div.setClassName("editor");
        editorDiv.add(div);
        formLayout.add(rolComboBox,personaComboBox,usuarioField, passwordField, creadorField, activoCheckbox);
        save.addClickListener(event -> save());
        cancel.addClickListener(event -> clearForm());
        delete.addClickListener(event -> delete());

        delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        binder.bindInstanceFields(this);
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
        wrapper.add(gridUsuarios);
        return wrapper;
    }

    private void refreshGrid() {
        // Actualización de los datos mostrados en el grid
        gridUsuarios.setItems(usuarioService.findAll());
    }

    private void setupForm() {
        // Configura los campos del formulario para usarlos con el Binder
        binder.forField(usuarioField)
                .withValidator(new StringLengthValidator("El nombre de usuario debe tener entre 3 y 50 caracteres", 3, 50))
                .bind(Usuario::getUsuario, Usuario::setUsuario);

        binder.forField(passwordField)
                .withValidator(new StringLengthValidator("La contraseña debe tener entre 5 y 50 caracteres", 5, 50))
                .bind(Usuario::getPassword, Usuario::setPassword);

        // Asume que creadorField y activoCheckbox no necesitan validación específica
        binder.bind(creadorField, Usuario::getCreador, Usuario::setCreador);
        binder.bind(activoCheckbox, Usuario::getActivo, Usuario::setActivo);

        // Configuración adicional del formulario como oyentes de eventos, por ejemplo, para el botón de guardar
        save.addClickListener(event -> save());
        cancel.addClickListener(event -> clearForm());
        delete.addClickListener(event -> delete());

        // Asegúrate de que el botón de guardar esté habilitado solo cuando el formulario es válido
        binder.addStatusChangeListener(event -> save.setEnabled(binder.isValid()));

        // Configura el layout de los botones
        HorizontalLayout buttonLayout = new HorizontalLayout(save, cancel, delete);

        // Agrega todos los campos y el layout de botones al layout principal del formulario
        formLayout.add(usuarioField, passwordField, creadorField, activoCheckbox, buttonLayout);

        // Establece el rol y la persona si aplicable, puedes necesitar ComboBox para estos
        // Aquí puedes configurar cómo seleccionar un Rol o Persona usando ComboBox o un componente similar

        // Nota: Para configurar un ComboBox con entidades, necesitarás configurar el DataProvider para el ComboBox
        // Por ejemplo:
         rolComboBox.setItems(rolService.findAll());
         rolComboBox.setItemLabelGenerator(Rol::getCargo); // Asumiendo que Rol tiene un método getCargo
         binder.forField(rolComboBox).bind(Usuario::getRol, Usuario::setRol);

         personaComboBox.setItems(personaService.findAll());
         personaComboBox.setItemLabelGenerator(Persona::getNombreCompleto); // Asumiendo que Persona tiene un método getNombreCompleto
         binder.forField(personaComboBox).bind(Usuario::getPersona, Usuario::setPersona);
    }



    private void save() {
        if (this.usuario == null) {
            this.usuario = new Usuario();
        }
        try {
            binder.writeBean(this.usuario);
            usuarioService.save(this.usuario);
            refreshGrid();
            clearForm();
            Notification.show("Usuario guardado con éxito");
        } catch (Exception e) {
            Notification.show("Error al guardar el usuario: " + e.getMessage(), 5000, Notification.Position.BOTTOM_START);
        }
    }

    private void delete() {
        if (this.usuario != null) {
            usuarioService.delete(this.usuario);
            refreshGrid();
            clearForm();
            Notification.show("Usuario eliminado");
        }
    }

    private void clearForm() {
        this.usuario = null; // Limpiar la referencia al usuario actual
        binder.readBean(new Usuario()); // Limpiar los campos del formulario
    }

    private void editUsuario(Usuario usuario) {
        if (usuario == null) {
            clearForm();
        } else {
            this.usuario = usuario;
            binder.readBean(this.usuario);
        }
    }
}