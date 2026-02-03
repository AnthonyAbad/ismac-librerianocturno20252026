package com.distribuida.dao;

import com.distribuida.model.Cliente;
import com.distribuida.model.Factura;
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

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class FacturaTestIntegracion {

    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private ClienteRepository clienteRepository;


    @Test
    public void testFacturaFindAll(){
        List<Factura> facturas = facturaRepository.findAll();
        assertNotNull(facturas);
        assertTrue(facturas.size() > 0);
        facturas.forEach(System.out::println);
    }

    @Test
    public void testFacturaFindOne(){
        Optional<Factura> factura = facturaRepository.findById(1);
        assertTrue(factura.isPresent());
        assertEquals("FAC-0001", factura.orElse(null).getNumFactura());
        assertEquals("150.96", factura.orElse(null).getTotal());
        System.out.println(factura);

        //150.96 no reconoce dos cifras decimales - validar metodos de presicion decimal.
    }

    @Test
    public void testFacturaSave(){
        Optional<Cliente> cliente = clienteRepository.findById(1);

        assertTrue(cliente.isPresent());


        Factura factura = new Factura();
        factura.setIdFactura(0);
        factura.setNumFactura("FAC-0066");
        factura.setFecha(new Date());
        factura.setTotalNeto(100.00);
        factura.setIva(15.00);
        factura.setTotal(115.00);
        factura.setCliente(cliente.orElse(null));

        Factura facturaGuardada = facturaRepository.save(factura);
        assertNotNull(facturaGuardada);
        assertEquals("FAC-0066", facturaGuardada.getNumFactura());
        assertEquals("100.0", facturaGuardada.getTotalNeto());
    }

    @Test
    public void testFacturaUptade(){
        Optional<Cliente> cliente = clienteRepository.findById(2);

        assertTrue(cliente.isPresent());
        Optional<Factura> factura = facturaRepository.findById(86);

        assertTrue(factura.isPresent());
        factura.orElse(null).setNumFactura("FAC-0077");
        factura.orElse(null).setFecha(new Date());
        factura.orElse(null).setTotalNeto(200.00);
        factura.orElse(null).setIva(60.00);
        factura.orElse(null).setTotal(260.00);
        factura.orElse(null).setCliente(cliente.orElse(null));
        Factura facturaActualizada = facturaRepository.save(factura.orElse(null));
assertEquals("FAC-0077", facturaActualizada.getNumFactura());
        assertEquals("200.0", facturaActualizada.getTotalNeto());
        assertEquals("Juan", facturaActualizada.getCliente().getNombre());
    }

    @Test
    public void testFacturaDelete(){

        if (facturaRepository.existsById(86)){
        facturaRepository.deleteById(86);}
        assertFalse(facturaRepository.existsById(86), "**********El dato fue eliminado");
    }
}
