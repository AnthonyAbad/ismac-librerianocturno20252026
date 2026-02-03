package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTestUnitaria {

    private Cliente cliente;
    @BeforeEach
    public void setUp() {

        cliente = new Cliente(1, "1234455", "Anthony ", "Abad", "Tumbaco", "0998766544", "abadantony@gmail.com");
    }
        @Test

         public void testClienteConstructor(){

            assertAll("Validar datos cliente - constructor",
                    () -> assertEquals(1, cliente.getIdCliente()) ,
                    () -> assertEquals("1234455", cliente.getCedula()) ,
                    () -> assertEquals("Anthony ", cliente.getNombre()) ,
                    () -> assertEquals("Abad", cliente.getApellido()) ,
                    () -> assertEquals("Tumbaco", cliente.getDireccion() ),
                    () -> assertEquals("0998766544", cliente.getTelefono()),
                    () -> assertEquals("abadantony@gmail.com", cliente.getCorreo())
                    );
        }

        @Test
    public void testClienteSetters(){
        cliente.setIdCliente(2);
        cliente.setCedula("123456789");
        cliente.setNombre("Pedro");
        cliente.setApellido("Benavides");
        cliente.setDireccion("Cumbaya");
        cliente.setTelefono("091234568");
        cliente.setCorreo("pedrob@gmail.com");

            assertAll("Validar datos cliente - constructor",
                    () -> assertEquals(2, cliente.getIdCliente()) ,
                    () -> assertEquals("123456789", cliente.getCedula()) ,
                    () -> assertEquals("Pedro", cliente.getNombre()) ,
                    () -> assertEquals("Benavides", cliente.getApellido()) ,
                    () -> assertEquals("Cumbaya", cliente.getDireccion() ),
                    () -> assertEquals("091234568", cliente.getTelefono()),
                    () -> assertEquals("pedrob@gmail.com", cliente.getCorreo())
            );

        }

        @Test
    public void clienteTestToString(){
        String str = cliente.toString();
        assertAll("Validar datos cliente - To String",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("1234455")),
                () -> assertTrue(str.contains("Anthony ")),
                () -> assertTrue(str.contains("Abad")),
                () -> assertTrue(str.contains("Tumbaco")),
                () -> assertTrue(str.contains("0998766544")),
            () -> assertTrue(str.contains("abadantony@gmail.com"))
        );
        }

    }



