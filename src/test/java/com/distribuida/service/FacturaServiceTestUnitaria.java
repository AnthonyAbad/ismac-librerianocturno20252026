package com.distribuida.service;

import com.distribuida.dao.FacturaRepository;
import com.distribuida.model.Cliente;
import com.distribuida.model.Factura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FacturaServiceTestUnitaria {

    @Mock
    private FacturaRepository facturaRepository;

    @InjectMocks
    private FacturaServiceImpl facturaServiceImpl;

    private Factura factura;

    private Cliente cliente;

    @BeforeEach
    public void setUp(){

        cliente = new Cliente(1,"178888443", "Anthony","Castillo","Direccion1","099887765432","correo@gmail.com" );

        factura = new Factura();
        factura.setIdFactura(1);
        factura.setNumFactura("FAC-0001");
        factura.setFecha(new Date());
        factura.setTotalNeto(100.00);
        factura.setIva(15.00);
        factura.setTotal(115.00);
        factura.setCliente(cliente);
    }

    @Test
    public  void findAll(){
        when(facturaRepository.findAll()).thenReturn(List.of(factura));
        List<Factura> facturas = facturaServiceImpl.findAll();

        assertNotNull(facturas);
        assertEquals(1, facturas.size());
        verify(facturaRepository, times(1)).findAll();
    }

    @Test
    public void findOneExistente(){
        when(facturaRepository.findById(1)).thenReturn(Optional.ofNullable(factura));

        Optional<Factura> factura1 = facturaServiceImpl.findOne(1);
        assertNotNull(factura1);
        assertEquals("FAC-0001", factura1.orElse(null).getNumFactura());
    }

    @Test
    public void findOneNoExistente(){
        when(facturaRepository.findById(2)).thenReturn(null);
        Optional<Factura> factura = facturaServiceImpl.findOne(2);
        assertNull(factura);
    }

    @Test
    public void save(){
        when(facturaRepository.save(factura)).thenReturn(factura);
        Factura factura1 = facturaServiceImpl.save(factura);
        assertNotNull(factura1);
        assertEquals("FAC-0001", factura1.getNumFactura());
    }

    @Test
    public void updateExistente(){
        Factura facturaActualizada = new Factura();
        facturaActualizada.setNumFactura("FAC-0002");
        facturaActualizada.setFecha(new Date());
        facturaActualizada.setTotalNeto(200.00);
        facturaActualizada.setIva(30.00);
        facturaActualizada.setTotal(230.00);
        facturaActualizada.setCliente(cliente);

        when(facturaRepository.findById(1)).thenReturn(Optional.ofNullable((factura)));
        when(facturaRepository.save(any())).thenReturn(facturaActualizada);

        Factura facturaResultado = facturaServiceImpl.update(1, facturaActualizada);
        assertNotNull(facturaResultado);
        assertEquals("FAC-0002", facturaResultado.getNumFactura());
        verify(facturaRepository, times(1)).save(factura);
    }

    @Test
    public void updateNoExistente(){

        Factura facturaNueva = new Factura();
        when(facturaRepository.findById(333)).thenReturn(null);
        Factura resultado = facturaServiceImpl.update(333, facturaNueva);
        assertNull(resultado);
        verify(facturaRepository, never()).save(any());
    }

    @Test
    public void deleteExistente(){
        when(facturaRepository.existsById(1)).thenReturn(true);
        facturaServiceImpl.delete(1);
        verify(facturaRepository).deleteById(1);
    }

    @Test
    public void deleteNoExistente(){
        when(facturaRepository.existsById(999)).thenReturn(false);
        facturaServiceImpl.delete(999);
        verify(facturaRepository, never()).deleteById(anyInt());
    }
}
