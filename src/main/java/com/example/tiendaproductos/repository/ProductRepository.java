package com.example.tiendaproductos.repository;

import com.example.tiendaproductos.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
