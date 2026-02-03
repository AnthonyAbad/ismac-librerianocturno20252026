package com.distribuida.dao;

import com.distribuida.model.Autor;
import com.distribuida.model.Categoria;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class AutorTestIntegracion {

    @Autowired
    private AutorRepository autorRepository;

    @Test
    public void testAutorFindAll(){
        List<Autor> autores = autorRepository.findAll();
        assertNotNull(autores);
        assertTrue(autores.size()> 0);
        autores.forEach(System.out::println);
    }

    @Test
    public void testAutorFindOne(){
        Optional<Autor> autores = autorRepository.findById(1);
        assertNotNull(autores.isPresent());
        assertEquals("Pamela", autores.orElse(null).getNombre());
        assertEquals("Lema", autores.orElse(null).getApellido());
        System.out.println(autores);
    }

    @Test
    public void testAutorSave(){
       Autor autor = new Autor(0, "Juan2", "Alimaña2", "España2", "Madrid2", "09876543212", "juanalimaña@gmail.com2");
        Autor autorGuardado = autorRepository.save(autor);
        assertNotNull(autorGuardado);
        assertEquals("Juan2", autorGuardado.getNombre());
        assertEquals("Alimaña2", autorGuardado.getApellido());
    }

    @Test
    public void testAutorActualizar(){
        Optional<Autor> autor2 =autorRepository.findById(54);

        autor2.orElse(null).setNombre("Juan33");
        autor2.orElse(null).setApellido("Alimaña33");
        autor2.orElse(null).setPais("ecuador");
        autor2.orElse(null).setDireccion("parama");
        autor2.orElse(null).setTelefono("12345");
        autor2.orElse(null).setCorreo("pedroa@gmail.com");

        autorRepository.save(autor2.orElse(null));
    }
    @Test
    public void testAutorBorrar(){
        autorRepository.deleteById(54);
    }

}
