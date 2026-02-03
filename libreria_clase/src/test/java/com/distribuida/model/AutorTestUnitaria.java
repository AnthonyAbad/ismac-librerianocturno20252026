package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AutorTestUnitaria {

    private Autor autor;
    @BeforeEach
    public void setUp(){
        autor = new Autor(1, "Juan", "Alimaña", "España", "Madrid", "0987654321", "juanalimaña@gmail.com");

    }

    @Test

    public void testAutorConstructor(){

        assertAll("Validar datos autor - constructor",
                () -> assertEquals(1, autor.getIdAutor()) ,
                () -> assertEquals("Juan", autor.getNombre()) ,
                () -> assertEquals("Alimaña", autor.getApellido()) ,
                () -> assertEquals("España", autor.getPais()) ,
                () -> assertEquals("Madrid", autor.getDireccion() ),
                () -> assertEquals("0987654321", autor.getTelefono()),
                () -> assertEquals("juanalimaña@gmail.com", autor.getCorreo())
        );
    }

    @Test
    public void testAutorSetters(){
        autor.setIdAutor(2);
        autor.setNombre("Juan2");
        autor.setApellido("Alimaña2");
        autor.setPais("España2");
        autor.setDireccion("Madrid2");
        autor.setTelefono("09876543212");
        autor.setCorreo("juanalimaña@gmail.com2");

        assertAll("Validar datos autor - constructor",
                () -> assertEquals(2, autor.getIdAutor()) ,
                () -> assertEquals("Juan2", autor.getNombre()) ,
                () -> assertEquals("Alimaña2", autor.getApellido()) ,
                () -> assertEquals("España2", autor.getPais()) ,
                () -> assertEquals("Madrid2", autor.getDireccion() ),
                () -> assertEquals("09876543212", autor.getTelefono()),
                () -> assertEquals("juanalimaña@gmail.com2", autor.getCorreo())
        );

    }

    @Test
    public void autorTestToString(){
        String str = autor.toString();
        assertAll("Validar datos autor - To String",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("Juan")),
                () -> assertTrue(str.contains("Alimaña")),
                () -> assertTrue(str.contains("España")),
                () -> assertTrue(str.contains("Madrid")),
                () -> assertTrue(str.contains("0987654321")),
                () -> assertTrue(str.contains("juanalimaña@gmail.com"))
        );
    }


}
