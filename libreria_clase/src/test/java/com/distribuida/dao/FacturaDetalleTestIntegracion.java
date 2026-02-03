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
public class FacturaDetalleTestIntegracion {

    @Autowired
    private FacturaDetalleRepository facturaDetalleRepository;
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private FacturaRepository facturaRepository;

    @Test
    public void testFacturaDetalleFindAll(){
        List<FacturaDetalle> facturaDetalles = facturaDetalleRepository.findAll();
        assertNotNull(facturaDetalles);
        assertTrue(facturaDetalles.size() > 0);
        facturaDetalles.forEach(System.out::println);
    }

    @Test
    public void testFacturaDetalleFindOne(){
        Optional<FacturaDetalle> facturaDetalle = facturaDetalleRepository.findById(1);
        assertTrue(facturaDetalle.isPresent());
        assertEquals(2, facturaDetalle.orElse(null).getCantidad());
        assertEquals(30.84, facturaDetalle.orElse(null).getSubtotal());
        System.out.println(facturaDetalle);

    }

    @Test
    public void testFacturaDetalleSave(){
        Optional<Categoria> categoria = categoriaRepository.findById(1);

        assertTrue(categoria.isPresent());

        Optional<Autor> autor = autorRepository.findById(1);

        assertTrue(autor.isPresent());

        Optional<Factura> factura = facturaRepository.findById(1);

        assertTrue(factura.isPresent());

        Optional<Cliente> cliente = clienteRepository.findById(1);

        assertTrue(cliente.isPresent());

        Optional<Libro> libro = libroRepository.findById(1);

        assertTrue(libro.isPresent());


        FacturaDetalle facturaDetalle = new FacturaDetalle();

        facturaDetalle.setIdFacturaDetalle(0);
        facturaDetalle.setCantidad(6);
        facturaDetalle.setSubtotal(10.0);
        facturaDetalle.setFactura(factura.orElse(null));
        facturaDetalle.setLibro(libro.orElse(null));

        FacturaDetalle facturaDetalleGuardado = facturaDetalleRepository.save(facturaDetalle);
        assertNotNull(facturaDetalleGuardado);
        assertEquals(6, facturaDetalleGuardado.getCantidad());
        assertEquals(10.0, facturaDetalleGuardado.getSubtotal());
    }

    @Test
    public void testLibroUptade(){
        Optional<Categoria> categoria = categoriaRepository.findById(1);

        assertTrue(categoria.isPresent());

        Optional<Autor> autor = autorRepository.findById(1);

        assertTrue(autor.isPresent());

        Optional<Factura> factura = facturaRepository.findById(1);

        assertTrue(factura.isPresent());

        Optional<Cliente> cliente = clienteRepository.findById(1);

        assertTrue(cliente.isPresent());

        Optional<Libro> libro = libroRepository.findById(1);

        assertTrue(libro.isPresent());

        Optional<FacturaDetalle> facturaDetalle = facturaDetalleRepository.findById(210);

        assertTrue(facturaDetalle.isPresent());


        facturaDetalle.orElse(null).setCantidad(66);
        facturaDetalle.orElse(null).setSubtotal(110.0);
        facturaDetalle.orElse(null).setFactura(factura.orElse(null));
        facturaDetalle.orElse(null).setLibro(libro.orElse(null));


        FacturaDetalle facturaDetalleActualizado = facturaDetalleRepository.save(facturaDetalle.orElse(null));
        assertEquals(66, facturaDetalleActualizado.getCantidad());
        assertEquals(110.0, facturaDetalleActualizado.getSubtotal());
        assertEquals("FAC-0001", facturaDetalleActualizado.getFactura().getNumFactura());
        assertEquals("Spring in Action", facturaDetalleActualizado.getLibro().getTitulo());
    }
    @Test
    public void testFacturaDetalleDelete(){

        if (facturaDetalleRepository.existsById(210)){
            facturaDetalleRepository.deleteById(210);}
        assertFalse(facturaDetalleRepository.existsById(210), "**********El dato fue eliminado");
    }
}
