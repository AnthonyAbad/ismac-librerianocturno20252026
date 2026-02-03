package com.distribuida.dao;

import com.distribuida.model.Categoria;
import com.distribuida.model.Cliente;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class CategoriaTestIntegracion {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    public void testCategoriaFindAll(){
        List<Categoria> categorias = categoriaRepository.findAll();
        assertNotNull(categorias);
        assertTrue(categorias.size()> 0);
        categorias.forEach(System.out::println);
        }


    @Test
    public void testCategoriaFindOne(){
        Optional<Categoria> categorias = categoriaRepository.findById(1);
        assertNotNull(categorias.isPresent());
        assertEquals(1, categorias.orElse(null).getIdCategoria());
        assertEquals("Programación", categorias.orElse(null).getCategoria());
        System.out.println(categorias);
    }

    @Test
    public void testCategoriaSave(){
       Categoria categoria  = new Categoria(0, "terror2", "libro de terror2");
        Categoria categoriaGuardada = categoriaRepository.save(categoria);
        assertNotNull(categoriaGuardada);
        assertEquals("terror2", categoriaGuardada.getCategoria());
        assertEquals("libro de terror2", categoriaGuardada.getDescripcion());
    }

    @Test
    public void testCategoriaActualizar(){
        Optional<Categoria> categoria2 =categoriaRepository.findById(58);

        categoria2.orElse(null).setCategoria("terror33");
        categoria2.orElse(null).setDescripcion("libro de terror33");

        categoriaRepository.save(categoria2.orElse(null));
    }

    @Test
    public void testCategoriaBorrar(){
        categoriaRepository.deleteById(58);
    }

}
