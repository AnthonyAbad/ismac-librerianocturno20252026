package com.distribuida.service;

import com.distribuida.dao.FacturaDetalleRepository;
import com.distribuida.model.FacturaDetalle;
import com.distribuida.model.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class FacturaDetalleImpl implements FacturaDetalleService{

    @Autowired
    private FacturaDetalleRepository facturaDetalleRepository;

    @Override
    public List<FacturaDetalle> findAll() {
        return facturaDetalleRepository.findAll();
    }

    @Override
    public Optional<FacturaDetalle> findOne(int id) {
        return facturaDetalleRepository.findById(id);
    }

    @Override
    public FacturaDetalle save(FacturaDetalle facturaDetalle) {
        return facturaDetalleRepository.save(facturaDetalle);
    }

    @Override
    public FacturaDetalle update(int id, FacturaDetalle facturaDetalle) {
        Optional<FacturaDetalle> facturaDetalleExistente = facturaDetalleRepository.findById(id);


        facturaDetalleExistente.orElse(null).setCantidad(facturaDetalle.getCantidad());
        facturaDetalleExistente.orElse(null).setSubtotal(facturaDetalle.getSubtotal());
        facturaDetalleExistente.orElse(null).setFactura(facturaDetalle.getFactura());
        facturaDetalleExistente.orElse(null).setLibro(facturaDetalle.getLibro());

        return facturaDetalleRepository.save(facturaDetalleExistente.orElse(null));
    }



    @Override
    public void delete(int id) {
        if (facturaDetalleRepository.existsById(id)) {
            facturaDetalleRepository.deleteById(id);
        }
    }
}
