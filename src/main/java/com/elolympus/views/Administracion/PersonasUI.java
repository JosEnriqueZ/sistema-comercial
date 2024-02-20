package com.elolympus.views.Administracion;

import com.elolympus.component.grid;
import com.elolympus.data.Administracion.Persona;
import com.elolympus.data.SamplePerson;
import com.elolympus.views.MainLayout;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.HasValue.ValueChangeEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.SerializableBiConsumer;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Personas")
@Route(value = "persona/:PersonaID?/:action?(edit)", layout = MainLayout.class)
@PermitAll
public abstract class PersonasUI extends VerticalLayout {

    public final String PERSONA_ID = "PersonaID";
    public final String PERSONA_EDIT_ROUTE_TEMPLATE = "persona/%s/edit";
    public List<Persona> listPersonas       = new ArrayList();
    public Grid<Persona> gridPersonas       = new Grid<>(Persona.class,false);
    public final TextField txtDni           = new TextField("DNI","","Busqueda por DNI");
    public final TextField txtNombres       = new TextField("Nombres","","Busqueda por Nombres");
    public final TextField txtApellidos     = new TextField("Apellidos","","Busqueda por Apellidos");
    public final Button btnFiltrar          = new Button("BUSCAR",new Icon(VaadinIcon.FILTER));
    public final HorizontalLayout tophl     = new HorizontalLayout(txtDni,txtNombres,txtApellidos,btnFiltrar);

    public final FormLayout formLayout = new FormLayout();
    public TextField nombres           = new TextField("Nombres","");
    public TextField apellidos         = new TextField("Apellidos","");
    public TextField tipo_documento    = new TextField("Tipo de Documento","");
    public TextField num_documento     = new TextField("DNI","");
    public TextField celular           = new TextField("Celular","");
    public TextField email             = new TextField("Correo","");
    public TextField sexo              = new TextField("Sexo","");
    public TextField creador           = new TextField("Creador", "");
    private final Button cancel = new Button("Cancelar");
    private final Button save = new Button("Guardar");
    private final Button delete = new Button("Eliminar",VaadinIcon.TRASH.create());


    public final SplitLayout splitLayout = new SplitLayout();
    public final VerticalLayout VL          = new VerticalLayout();
    public final HorizontalLayout HL        = new HorizontalLayout();


    public PersonasUI() {

        //addClassNames("editar-tabla-view");
        // Create UI

        this.createEditorLayout(splitLayout);

        //gridPersonas.setItems(listPersonas);

        //getToolBar().add(getBtnAdd(),getBtnEdit(),getBtnRefresh());
        //getHeader().add(getToolBar());
        //getBody().

        gridPersonas.addColumn(CrearComponmenteActivoRenderer())          .setHeader("Activo")       .setAutoWidth(true);
        gridPersonas.addColumn(Persona::getNum_documento)   .setHeader("DNI")          .setAutoWidth(true);
        gridPersonas.addColumn(Persona::getApellidos)       .setHeader("Apellidos")    .setAutoWidth(true);
        gridPersonas.addColumn(Persona::getNombres)         .setHeader("Nombres")      .setAutoWidth(true);
        gridPersonas.addColumn(Persona::getCelular)         .setHeader("Ciclo Actual") .setAutoWidth(true);

        btnFiltrar.addClickListener(e->onBtnFiltrar());
        txtApellidos.addValueChangeListener(e->onBtnFiltrar());
        txtNombres.addValueChangeListener(e->onBtnFiltrar());
        txtDni.addValueChangeListener(e->onBtnFiltrar());

        initStyles();
        this.VL.add(tophl,gridPersonas);
        this.HL.add(VL,splitLayout);
        this.add(HL);

        save.addClickListener(e->onBtnSave());
        cancel.addClickListener(e->onBtnCancel());
        delete.addClickListener(e->onBtnDelete());
        gridPersonas.asSingleSelect().addValueChangeListener(e->asSingleSelect(e.getValue()));
    }

    public void initStyles(){
        //gridPersonas.setSizeFull();
        this.delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        tophl.setAlignItems(Alignment.CENTER);
        this.VL.setSizeFull();
        this.HL.setSizeFull();
/*        Div celularPrefijo = new Div();
        celularPrefijo.setText("(+51)");
        celular.setPrefixComponent(celularPrefijo);*/
    }

    private static final SerializableBiConsumer<Span, Persona> EstadoComponenteActivo = (
            span, persona) -> {
        String theme = String.format("badge %s",
                persona.getActivo() ? "success" : "error");
        span.getElement().setAttribute("theme", theme);
        span.setText(persona.getActivo()?"Activo":"Desactivado");
    };

    private static ComponentRenderer<Span, Persona> CrearComponmenteActivoRenderer() {
        return new ComponentRenderer<>(Span::new, EstadoComponenteActivo);
    }
    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        //editorLayoutDiv.setClassName("editor-layout");

        Div editorDiv = new Div();
       // editorDiv.setClassName("editor");
        editorLayoutDiv.add(editorDiv);
        formLayout.add(nombres, apellidos, tipo_documento, num_documento, celular, email, sexo, creador);
        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel,delete);
        //this.getStyle().set("background","red");
        editorLayoutDiv.add(buttonLayout);
    }

    public abstract void onBtnFiltrar();
    public abstract void onRefresh();
    public abstract void onBtnSave();
    public abstract void onBtnCancel();
    public abstract void onBtnDelete();
    public abstract void asSingleSelect(Persona e);


}

