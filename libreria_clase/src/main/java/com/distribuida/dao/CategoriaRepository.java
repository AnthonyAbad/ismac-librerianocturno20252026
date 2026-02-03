package com.distribuida.dao;

import com.distribuida.model.Categoria;
import com.distribuida.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
