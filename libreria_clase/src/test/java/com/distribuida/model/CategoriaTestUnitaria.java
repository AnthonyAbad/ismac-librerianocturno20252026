package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CategoriaTestUnitaria {

    private Categoria categoria;
    @BeforeEach
    public void setUp(){
        categoria  = new Categoria(1, "terror", "libro de terror");
    }

    @Test

    public void testCategoriaConstructor(){

        assertAll("Validar datos categoria - constructor",
                () -> assertEquals(1, categoria.getIdCategoria()) ,
                () -> assertEquals("terror", categoria.getCategoria()) ,
                () -> assertEquals("libro de terror", categoria.getDescripcion())
        );
    }

    @Test
    public void testCategoriaSetters() {
        categoria.setIdCategoria(2);
        categoria.setCategoria("terror2");
        categoria.setDescripcion("libro2");


        assertAll("Validar datos categoria - constructor",
                () -> assertEquals(2, categoria.getIdCategoria()),
                () -> assertEquals("terror2", categoria.getCategoria()),
                () -> assertEquals("libro2", categoria.getDescripcion())
        );
    }

    @Test
    public void categoriaTestToString(){
        String str = categoria.toString();
        assertAll("Validar datos categoria - To String",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("terror")),
                () -> assertTrue(str.contains("libro de terror"))

        );
    }

}
