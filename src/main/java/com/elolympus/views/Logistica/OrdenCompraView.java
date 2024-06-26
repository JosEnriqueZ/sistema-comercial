package com.elolympus.views.Logistica;

import com.elolympus.component.DataGrid;
import com.elolympus.data.Logistica.OrdenCompra;
import com.elolympus.services.services.OrdenCompraService;
import com.elolympus.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

import java.time.LocalDate;

@PageTitle("Lista Orden de Compra")
@Route(value = "listOrdenCompra", layout = MainLayout.class)
@PermitAll
public class ListOrdenCompraView extends Div {

    private final OrdenCompraService ordenCompraService;
    private OrdenCompra ordenCompra;
    private final BeanValidationBinder<OrdenCompra> binder= new BeanValidationBinder<>(OrdenCompra.class);

    //GRID
    private final Grid<OrdenCompra> grid = new Grid<>(OrdenCompra.class);
    private final DataGrid<OrdenCompra> dataGrid = new DataGrid<>(true,false);
    private final Button editar = new Button("EDITAR");
    private final Button agregar = new Button("AGREGAR");
    private final Button eliminar = new Button("ELIMINAR");
    private final Button cancelar = new Button("CANCELAR");
    private final FormLayout headerForm = new FormLayout();
    private final FormLayout detailForm = new FormLayout();


    private final VerticalLayout panel = new VerticalLayout();
    private final HorizontalLayout panelFiltro     = new HorizontalLayout();
    private final HorizontalLayout panelButton     = new HorizontalLayout();

    private final TextField Sucursal = new TextField("Documento Pago");
    private final DatePicker FechaInicio     = new DatePicker("Fecha Inicio",LocalDate.now());
    private final DatePicker FechaFin        = new DatePicker("Fecha Fin",LocalDate.now());

    //constructor
    public ListOrdenCompraView(OrdenCompraService ordenCompraService) {
        this.ordenCompraService = ordenCompraService;


        this.panelFiltro.add(Sucursal,FechaInicio,FechaFin);
        this.panelButton.add(editar,agregar,eliminar,cancelar);
        this.panel.add(panelFiltro,dataGrid,panelButton);
        this.add(panel);
        init();
    }
    private void init(){
        this.panel.setSizeFull();
        this.panelButton.setWidthFull();
        //this.panelButton.setAlignItems(FlexComponent.Alignment.END);
        this.panelButton.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        this.panelFiltro.setWidthFull();
        initDataGrid();
        initButtons();
        addClassName("orden-compra-view");
        setSizeFull();
        refreshGrids();
    }

    private void initButtons(){
        //configuración de botones
        agregar.addClickListener(event -> add());
        //cancelar.addClickListener(event -> deleteOrdenCompra());
        eliminar.addClickListener(event -> deleteOrdenCompra());

        agregar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancelar.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        eliminar.addThemeVariants(ButtonVariant.LUMO_ERROR);

        //buttons.setClassName("button-layout");
    }

    private void initDataGrid(){
        dataGrid.addColumn(OrdenCompra::getAlmacenEntrega,"Almacen Entrega");
        dataGrid.addColumn(OrdenCompra::getNumeroProveedor,"Numero Proveedor");
        dataGrid.addColumn(OrdenCompra::getDireccionProveedor,"Direccion Proveedor");
        dataGrid.addColumn(OrdenCompra::getFecha,"Fecha");
        dataGrid.addColumn(OrdenCompra::getFechaEntrega,"Fecha Entrega");
        dataGrid.addColumn(OrdenCompra::getMoneda,"Moneda");
        dataGrid.addColumn(OrdenCompra::getImpuesto,"Impuesto");
        dataGrid.addColumn(OrdenCompra::getObservaciones,"Observaciones");
        dataGrid.addColumn(OrdenCompra::getTotalCobrado,"Total Cobrado");
        dataGrid.addColumn(OrdenCompra::getTipoCambio,"Tipo Cambio");
        dataGrid.addColumn(OrdenCompra::getDiasCredito,"Credias/Dias");
        dataGrid.addColumn(OrdenCompra::getSucursal,"Sucursal");
        dataGrid.addColumn(OrdenCompra::getImpuesto_incluido,"Impuesto Incluido");
        dataGrid.addColumn(OrdenCompra::getDocumento_pago,"Documento Pago");
        dataGrid.addColumn(OrdenCompra::getTotal,"Total");

        dataGrid.asSingleSelect().addValueChangeListener(evt -> editOrdenCompra(evt.getValue()));
    }

    private void refreshGrids() {
        dataGrid.setList(ordenCompraService.findAll());
    }



    private void add(){
        OrdenCompraViewnackup ordenCompra = new OrdenCompraViewnackup(this.ordenCompraService);
        Dialog view = new Dialog();
        view.add(ordenCompra);
        view.open();
    }

    private void deleteOrdenCompra(){
        ordenCompra = dataGrid.getSelectedValue();
        if (ordenCompra != null) {
            ordenCompraService.delete(ordenCompra);
            refreshGrids();
            Notification.show("Orden de Compra eliminada correctamente");
        } else {
            Notification.show("No se pudo eliminar la Orden de Compra");
        }
    }


    private void editOrdenCompra(OrdenCompra ordenCompra){

    }


}
