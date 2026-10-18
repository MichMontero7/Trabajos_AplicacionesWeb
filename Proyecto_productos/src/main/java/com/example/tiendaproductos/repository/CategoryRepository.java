package com.example.tiendaproductos.repository;

import com.example.tiendaproductos.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
