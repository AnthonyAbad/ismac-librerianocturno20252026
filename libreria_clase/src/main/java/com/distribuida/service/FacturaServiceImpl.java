package com.distribuida.service;

import com.distribuida.dao.FacturaRepository;
import com.distribuida.model.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaServiceImpl implements FacturaService{
    @Autowired
    private FacturaRepository facturaRepository;

    @Override
    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    @Override
    public Optional<Factura> findOne(int id) {
        return facturaRepository.findById(id);
    }

    @Override
    public Factura save(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Override
    public Factura update(int id, Factura factura) {
        Optional<Factura> facturaExistente = facturaRepository.findById(id);

        facturaExistente.orElse(null).setNumFactura(factura.getNumFactura());
        facturaExistente.orElse(null).setFecha(factura.getFecha());
        facturaExistente.orElse(null).setTotalNeto(factura.getTotalNeto());
        facturaExistente.orElse(null).setIva(factura.getIva());
        facturaExistente.orElse(null).setTotal(factura.getTotal());
        facturaExistente.orElse(null).setCliente(factura.getCliente());

        return facturaRepository.save(facturaExistente.orElse(null));
    }

    @Override
    public void delete(int id) {
        if (facturaRepository.existsById(id)) {
            facturaRepository.deleteById(id);
        }
        }

    @Override
    public Object findByid(int i) {
        return null;
    }

}

