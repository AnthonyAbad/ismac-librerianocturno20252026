package com.distribuida.service;

import com.distribuida.model.Factura;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface FacturaService {



    public List<Factura> findAll();

    public Optional<Factura> findOne(int id);

    public Factura save(Factura factura);

    public Factura update(int id, Factura factura);

    public void delete(int id);

    Object findByid(int i);
}
