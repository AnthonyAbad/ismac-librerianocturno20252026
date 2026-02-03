package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class FacturaTestUnitaria {

    private Cliente cliente;
    private Factura factura;

    @BeforeEach
    public void setUp(){

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
    }

    @Test
    public void facturaTestConstructor(){
        assertAll("Validar constructor - Factura",
                () -> assertEquals(1, factura.getIdFactura()),
                () -> assertEquals("FAC-0001", factura.getNumFactura()),
               // () -> assertEquals(new Date(), factura.getFecha()),
                () -> assertEquals(100.00, factura.getTotalNeto()),
                () -> assertEquals(15.00, factura.getIva()),
                () -> assertEquals(115.00, factura.getTotal()),
                () -> assertEquals("Anthony ", factura.getCliente().getNombre())

                );
    }

    @Test
    public void facturaTestSeters(){

        cliente = new Cliente(2, "12344552", "Anthony2", "Abad2", "Tumbaco2", "09987665442", "abadantony@gmail.com2");
        factura.setIdFactura(2);
        factura.setNumFactura("FAC-0002");
        factura.setFecha(new Date());
        factura.setTotalNeto(200.00);
        factura.setIva(30.00);
        factura.setTotal(230.00);
        factura.setCliente(cliente);

        assertAll("Validar Setters - Factura",
                () -> assertEquals(2, factura.getIdFactura()),
                () -> assertEquals("FAC-0002", factura.getNumFactura()),
                // () -> assertEquals(new Date(), factura.getFecha()),
                () -> assertEquals(200.00, factura.getTotalNeto()),
                () -> assertEquals(30.00, factura.getIva()),
                () -> assertEquals(230.00, factura.getTotal()),
                () -> assertEquals("Anthony2", factura.getCliente().getNombre())

        );

    }

    @Test
    public void facturaTestToString(){
        String str = factura.toString();
        assertAll("Validar to String- Factura",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("FAC-0001")),
                () -> assertTrue(str.contains("100.0")),
                () -> assertTrue(str.contains("15.0")),
                () -> assertTrue(str.contains("115.0")),
                () -> assertTrue(str.contains("Anthony "))

        );
    }

    @Test
    public void facturaTestInyector(){
        assertAll("Validar metodo Inyector- Factura",
                () -> assertNotNull(factura.getCliente()),
                () -> assertEquals("Abad", factura.getCliente().getApellido())
                );

    }

    @Test
    public void facturaTestValoresNegativos(){
        factura.setTotal(-100.00);
        assertAll("Validar datos negativos - factura",
                () -> assertEquals(-100, factura.getTotal())
                );
        //Validar los campos negativos, solo deberia existir valores posiivos en la factura
    }



}
