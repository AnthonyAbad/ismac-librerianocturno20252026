package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LibroTestUnitaria {

private Libro libro;
private Categoria categoria;
private Autor autor;

@BeforeEach

    public void setUp(){

    autor = new Autor(1, "Juan", "Alimaña", "España", "Madrid", "0987654321", "juanalimaña@gmail.com");
    categoria  = new Categoria(1, "terror", "libro de terror");

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

}


    @Test
    public void libroTestConstructor(){
        assertAll("Validar constructor - Libro",
                () -> assertEquals(1, libro.getIdLibro()),
                () -> assertEquals("Terrorcito", libro.getTitulo()),
                () -> assertEquals("buena vista", libro.getEditorial()),
                () -> assertEquals(100, libro.getNumPaginas()),
                () -> assertEquals("normal", libro.getEdision()),
                () -> assertEquals("Español", libro.getIdioma()),
               // () -> assertEquals(115.00, libro.getFechaPublicacion()),
                () -> assertEquals("Libro de terror", libro.getDescripcion()),
                () -> assertEquals("Carton", libro.getTipoPasta()),
                () -> assertEquals("123445e", libro.getIsbn()),
                () -> assertEquals(5, libro.getNumEjemplares()),
                () -> assertEquals("terr", libro.getPortada()),
                () -> assertEquals("Normal", libro.getPresentacion()),
                () -> assertEquals(10.00, libro.getPrecio()),
                () -> assertEquals("libro de terror", libro.getCategoria().getDescripcion()),
                () -> assertEquals("Juan", libro.getAutor().getNombre())
        );
    }


    @Test
    public void libroTestSeters(){

        autor = new Autor(2, "Juan2", "Alimaña2", "España2", "Madrid2", "09876543212", "juanalimaña@gmail.com2");
        categoria  = new Categoria(2, "terror2", "libro de terror2");

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

        assertAll("Validar setters - Libro",
                () -> assertEquals(2, libro.getIdLibro()),
                () -> assertEquals("Terrorcito2", libro.getTitulo()),
                () -> assertEquals("buena vista2", libro.getEditorial()),
                () -> assertEquals(1002, libro.getNumPaginas()),
                () -> assertEquals("normal2", libro.getEdision()),
                () -> assertEquals("Español2", libro.getIdioma()),
                // () -> assertEquals(115.00, libro.getFechaPublicacion()),
                () -> assertEquals("Libro de terror2", libro.getDescripcion()),
                () -> assertEquals("Carton2", libro.getTipoPasta()),
                () -> assertEquals("123445e2", libro.getIsbn()),
                () -> assertEquals(52, libro.getNumEjemplares()),
                () -> assertEquals("terr2", libro.getPortada()),
                () -> assertEquals("Normal2", libro.getPresentacion()),
                () -> assertEquals(10.002, libro.getPrecio()),
                () -> assertEquals("libro de terror2", libro.getCategoria().getDescripcion()),
                () -> assertEquals("Juan2", libro.getAutor().getNombre())
        );

    }


    @Test
    public void libroTestToString(){
        String str = libro.toString();
        assertAll("Validar to String- Libro",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("Terrorcito")),
                () -> assertTrue(str.contains("buena vista")),
                () -> assertTrue(str.contains("100")),
                () -> assertTrue(str.contains("normal")),
                () -> assertTrue(str.contains("Español")),
                () -> assertTrue(str.contains("Libro de terror")),
                () -> assertTrue(str.contains("Carton")),
                () -> assertTrue(str.contains("123445e")),
                () -> assertTrue(str.contains("5")),
                () -> assertTrue(str.contains("terr")),
                () -> assertTrue(str.contains("Normal")),
                () -> assertTrue(str.contains("10.0")),
                () -> assertTrue(str.contains("libro de terror")),
                () -> assertTrue(str.contains("Juan"))

        );
    }

    @Test
    public void libroTestInyector(){
        assertAll("Validar metodo Inyector- Libro",
                () -> assertNotNull(libro.getAutor()),
                () -> assertEquals("Alimaña", libro.getAutor().getApellido())
        );

    }
}
