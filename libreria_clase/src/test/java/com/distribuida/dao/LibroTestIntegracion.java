package com.distribuida.dao;

import com.distribuida.model.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class LibroTestIntegracion {

@Autowired
    private LibroRepository libroRepository;
@Autowired
    private CategoriaRepository categoriaRepository;
@Autowired
    private AutorRepository autorRepository;

    @Test
    public void testLibroFindAll(){
        List<Libro> libros = libroRepository.findAll();
        assertNotNull(libros);
        assertTrue(libros.size() > 0);
        libros.forEach(System.out::println);
    }

    @Test
    public void testLibroFindOne(){
        Optional<Libro> libro = libroRepository.findById(1);
        assertTrue(libro.isPresent());
        assertEquals("Spring in Action", libro.orElse(null).getTitulo());
        assertEquals("Manning", libro.orElse(null).getEditorial());
        System.out.println(libro);

    }

    @Test
    public void testLibroSave(){
        Optional<Categoria> categoria = categoriaRepository.findById(1);

        assertTrue(categoria.isPresent());

        Optional<Autor> autor = autorRepository.findById(1);

        assertTrue(autor.isPresent());


        Libro libro = new Libro();
        libro.setIdLibro(0);
        libro.setTitulo("Capo");
        libro.setEditorial("Infa");
        libro.setNumPaginas(100);
        libro.setEdision("Normal");
        libro.setIdioma("Español");
        libro.setFechaPublicacion(new Date());
        libro.setDescripcion("Accion");
        libro.setTipoPasta("Carton");
        libro.setIsbn("12344ee");
        libro.setNumEjemplares(50);
        libro.setPortada("Pace");
        libro.setPresentacion("Color");
        libro.setPrecio(100.00);
        libro.setCategoria(categoria.orElse(null));
        libro.setAutor(autor.orElse(null));

        Libro libroGuardado = libroRepository.save(libro);
        assertNotNull(libroGuardado);
        assertEquals("Capo", libroGuardado.getTitulo());
        assertEquals("Infa", libroGuardado.getEditorial());
    }

    @Test
    public void testLibroUptade(){
         Optional<Categoria> categoria = categoriaRepository.findById(1);

        assertTrue(categoria.isPresent());

        Optional<Autor> autor = autorRepository.findById(1);

        assertTrue(autor.isPresent());
        Optional<Libro> libro = libroRepository.findById(79);

        assertTrue(libro.isPresent());


        libro.orElse(null).setTitulo("Capo2");
        libro.orElse(null).setEditorial("Infa2");
        libro.orElse(null).setNumPaginas(1002);
        libro.orElse(null).setEdision("Normal2");
        libro.orElse(null).setIdioma("Español2");
        libro.orElse(null).setFechaPublicacion(new Date());
        libro.orElse(null).setDescripcion("Accion2");
        libro.orElse(null).setTipoPasta("Carton2");
        libro.orElse(null).setIsbn("12344ee2");
        libro.orElse(null).setNumEjemplares(502);
        libro.orElse(null).setPortada("Pace2");
        libro.orElse(null).setPresentacion("Color2");
        libro.orElse(null).setPrecio(1002.00);
        libro.orElse(null).setCategoria(categoria.orElse(null));
        libro.orElse(null).setAutor(autor.orElse(null));


        Libro libroActualizado = libroRepository.save(libro.orElse(null));
        assertEquals("Capo2", libroActualizado.getTitulo());
        assertEquals("Infa2", libroActualizado.getEditorial());
        assertEquals("Programación", libroActualizado.getCategoria().getCategoria());
        assertEquals("Pamela", libroActualizado.getAutor().getNombre());
    }

    @Test
    public void testLibroDelete(){

        if (libroRepository.existsById(79)){
            libroRepository.deleteById(79);}
        assertFalse(libroRepository.existsById(79), "**********El dato fue eliminado");
    }

}
