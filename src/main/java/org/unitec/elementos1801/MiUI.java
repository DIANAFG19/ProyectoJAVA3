package org.unitec.elementos1801;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/*
 * @author YOO
 */
@SpringUI
@Theme("valo")
public class MiUI extends UI {

    @Autowired RepositorioMensajito repoMensa;

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        //generamos una etiqueta
        Label etiqueta = new Label("HOLA, SOY DIANA :3");
        etiqueta.addStyleName(ValoTheme.LABEL_H1);
        //layout.addComponent(etiqueta);
        //setContent(layout);      
        Label etiquetaGuardar = new Label("APLICACIÓN MENSAJE");
        etiqueta.addStyleName(ValoTheme.LABEL_H2);
        TextField textoTitulo = new TextField();
        //el siguiente es un placeholder
        textoTitulo.setPlaceholder("Escribe el titulo");
        TextArea textoCuerpo = new TextArea();
        textoCuerpo.setPlaceholder("cuerpo del mensaje");
        Button boton = new Button("Guardar mensaje");

        //esto esta en vaddin server,side component
        Grid<Mensajito> grid = new Grid<>();
        grid.setItems((List) repoMensa.findAll());
        grid.addColumn(Mensajito::getId).setCaption("ID del mensaje");
        grid.addColumn(Mensajito::getTitulo).setCaption("Titulo del mensaje");
        grid.addColumn(Mensajito::getCuerpo).setCaption("Cuerpo del mensaje");

        //Manejamos el evento del boton
        boton.addClickListener(evento -> {
            if (textoTitulo.getValue().equals("") || textoCuerpo.getValue().equals("")) {
                Notification.show("Los campos son requeridos!!", Notification.TYPE_ERROR_MESSAGE);
            } else {
                repoMensa.save(new Mensajito(textoTitulo.getValue(), textoCuerpo.getValue()));
                Notification.show("Se guardo el mensaje!!", Notification.TYPE_ERROR_MESSAGE);
            }
            grid.setItems((List) repoMensa.findAll());
        });

        //Agregamos los layout
        layout.addComponent(etiqueta);
        layout.addComponent(etiquetaGuardar);
        layout.addComponent(textoTitulo);
        layout.addComponent(textoCuerpo);
        layout.addComponent(boton);
        layout.addComponent(grid);

        //primero creamos un horizontal layout
        HorizontalLayout layout1 = new HorizontalLayout();
        TextField textoId = new TextField();
        textoId.setPlaceholder("Introduce un ID");
        Button botonBuscarId = new Button("Buscar");
        botonBuscarId.addStyleName(ValoTheme.BUTTON_PRIMARY);
        layout1.addComponent(textoId);
        layout1.addComponent(botonBuscarId);
        layout.addComponent(layout1);

        //Creamos otro layout para los campos de texto
        HorizontalLayout layout2 = new HorizontalLayout();
        TextField textoBuscarId = new TextField();
        TextField textoBuscarTitulo = new TextField();
        TextArea textoBuscarCuerpo = new TextArea();
        layout2.addComponent(textoBuscarId);
        layout2.addComponent(textoBuscarTitulo);
        layout2.addComponent(textoBuscarCuerpo);
        layout.addComponent(layout2);

        //Creamos un HorizontalLayout para poner el boton de actualizar y eliminar
        HorizontalLayout layout3 = new HorizontalLayout();
        Button botonActualizar = new Button("Actualizar");
        botonActualizar.addStyleName(ValoTheme.BUTTON_QUIET);
        Button botonEliminar = new Button("Eliminar");
        botonEliminar.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        layout3.addComponent(botonActualizar);
        layout3.addComponent(botonEliminar);
        layout.addComponent(layout3);
        setContent(layout);

        //vamos a buscar por id
        botonBuscarId.addClickListener(evento -> {
            Mensajito mensa = repoMensa.findOne(Integer.parseInt(textoId.getValue()));
            //Ajustamos los tres campos con los datos
            //primero id
            textoBuscarId.setValue("" + mensa.getId());
            textoBuscarTitulo.setValue(mensa.getTitulo());
            textoBuscarCuerpo.setValue(mensa.getCuerpo());
        });

        //vamos a actualizar
        botonActualizar.addClickListener(evento -> {
            if (textoBuscarId.getValue().equals("") || textoBuscarTitulo.getValue().equals("") || textoBuscarCuerpo.getValue().equals("")) {
                Notification.show("Los campos son requeridos!!", Notification.TYPE_ERROR_MESSAGE);
            } else {
                repoMensa.save(new Mensajito(Integer.parseInt(textoBuscarId.getValue()), textoBuscarTitulo.getValue(), textoBuscarCuerpo.getValue()));
                Notification.show("se guardo el mensaje!!", Notification.TYPE_ERROR_MESSAGE);
            }
            grid.setItems((List) repoMensa.findAll());
        });

        //vamos a eliminar
        botonEliminar.addClickListener(evento -> {
            if (textoBuscarId.getValue().equals("") || textoBuscarTitulo.getValue().equals("") || textoBuscarCuerpo.getValue().equals("")) {
                Notification.show("Los campos son requeridos!!", Notification.TYPE_ERROR_MESSAGE);
            } else {
                repoMensa.delete(new Mensajito(Integer.parseInt(textoBuscarId.getValue())));
                Notification.show("Se eleimino el mensaje", Notification.TYPE_ERROR_MESSAGE);
            }
            grid.setItems((List) repoMensa.findAll());
        });
    }//cierre del metodo init
}//cierre de la clase
