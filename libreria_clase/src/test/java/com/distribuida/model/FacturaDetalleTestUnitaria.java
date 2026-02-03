package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FacturaDetalleTestUnitaria {

    private FacturaDetalle facturaDetalle;
    private Categoria categoria;
    private Autor autor;
    private Cliente cliente;
    private Factura factura;
    private Libro libro;

    @BeforeEach
    public void setUp(){

        autor = new Autor(1, "Juan", "Alimaña", "España", "Madrid", "0987654321", "juanalimaña@gmail.com");
        categoria  = new Categoria(1, "terror", "libro de terror");
        cliente = new Cliente(1, "1234455", "Anthony ", "Abad", "Tumbaco", "0998766544", "abadantony@gmail.com");
        factura = new Factura();
        factura.setIdFactura(1);
        factura.setNumFactura("FAC-0001");
        factura.setFecha(new Date());
        factura.setTotalNeto(100.00);
        factura.setIva(15.00);
        factura.setTotal(115.00);
        //inyeccion de dependencias
        factura.setCliente(cliente);

        libro = new Libro();
        libro.setIdLibro(1);
        libro.setTitulo("Terrorcito");
        libro.setEditorial("buena vista");
        libro.setNumPaginas(100);
        libro.setEdision("normal");
        libro.setIdioma("Español");
        libro.setFechaPublicacion(new Date());
        libro.setDescripcion("Libro de terror");
        libro.setTipoPasta("Carton");
        libro.setIsbn("123445e");
        libro.setNumEjemplares(5);
        libro.setPortada("terr");
        libro.setPresentacion("Normal");
        libro.setPrecio(10.00);

//Inyeccion de dependencias
        libro.setCategoria(categoria);
        libro.setAutor(autor);

        facturaDetalle = new FacturaDetalle();
        facturaDetalle.setIdFacturaDetalle(1);
        facturaDetalle.setCantidad(3);
        facturaDetalle.setSubtotal(10.00);
        //Inyeccion de dependencias
        facturaDetalle.setFactura(factura);
        facturaDetalle.setLibro(libro);

    }

    @Test
    public void facturaDetalleTestConstructor(){
        assertAll("Validar constructor - FacturaDetalle",
                () -> assertEquals(1, facturaDetalle.getIdFacturaDetalle()),
                () -> assertEquals(3, facturaDetalle.getCantidad()),
                () -> assertEquals(10.00, facturaDetalle.getSubtotal()),
                () -> assertEquals("FAC-0001", facturaDetalle.getFactura().getNumFactura()),
        () -> assertEquals("Terrorcito", facturaDetalle.getLibro().getTitulo())
        );
    }

    @Test
    public void facturaDetalleTestSeters(){

        factura = new Factura();
        factura.setIdFactura(2);
        factura.setNumFactura("FAC-00012");
        factura.setFecha(new Date());
        factura.setTotalNeto(100.002);
        factura.setIva(15.002);
        factura.setTotal(115.002);
        //inyeccion de dependencias
        factura.setCliente(cliente);

        libro = new Libro();
        libro.setIdLibro(2);
        libro.setTitulo("Terrorcito2");
        libro.setEditorial("buena vista2");
        libro.setNumPaginas(1002);
        libro.setEdision("normal2");
        libro.setIdioma("Español2");
        libro.setFechaPublicacion(new Date());
        libro.setDescripcion("Libro de terror2");
        libro.setTipoPasta("Carton2");
        libro.setIsbn("123445e2");
        libro.setNumEjemplares(52);
        libro.setPortada("terr2");
        libro.setPresentacion("Normal2");
        libro.setPrecio(10.002);

//Inyeccion de dependencias
        libro.setCategoria(categoria);
        libro.setAutor(autor);




        facturaDetalle.setIdFacturaDetalle(2);
        facturaDetalle.setCantidad(32);
        facturaDetalle.setSubtotal(10.002);
        //Inyeccion de dependencias
        facturaDetalle.setFactura(factura);
        facturaDetalle.setLibro(libro);

        assertAll("Validar constructor - FacturaDetalle",
                () -> assertEquals(2, facturaDetalle.getIdFacturaDetalle()),
                () -> assertEquals(32, facturaDetalle.getCantidad()),
                () -> assertEquals(10.002, facturaDetalle.getSubtotal()),
                () -> assertEquals("FAC-00012", facturaDetalle.getFactura().getNumFactura()),
                () -> assertEquals("Terrorcito2", facturaDetalle.getLibro().getTitulo())
        );

    }

    @Test
    public void facturaDetalleTestToString(){
        String str = facturaDetalle.toString();
        assertAll("Validar to String- FacturaDetalle",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("3")),
                () -> assertTrue(str.contains("10.0")),
                () -> assertTrue(str.contains("FAC-0001")),
                () -> assertTrue(str.contains("Terrorcito"))
        );
    }

    @Test
    public void setFacturaDetalleTestInyector(){
        assertAll("Validar metodo Inyector- FacturaDetalle",
                () -> assertNotNull(facturaDetalle.getLibro()),
                () -> assertEquals("Terrorcito", facturaDetalle.getLibro().getTitulo())
        );

    }

}
