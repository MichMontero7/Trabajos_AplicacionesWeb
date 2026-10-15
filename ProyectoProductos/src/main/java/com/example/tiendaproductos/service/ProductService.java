package com.example.tiendaproductos.service;

import com.example.tiendaproductos.model.Category;
import com.example.tiendaproductos.model.Product;
import com.example.tiendaproductos.repository.CategoryRepository;
import com.example.tiendaproductos.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repo;
    private final CategoryRepository repoCategory;

    public ProductService(ProductRepository repo, CategoryRepository repoCategory){
        this.repo = repo;
        this.repoCategory = repoCategory;
    }

    public List<Product> getAll(){
        return repo.findAll();
    }

    public Optional<Product> findById(Long id){
        return repo.findById(id);
    }

    public Product save(Product product, Long categoryId){
        Category category = repoCategory.findById(categoryId).orElseThrow(); //Arroja un error cuando no encuentre el optional para cat
        //entonces arroja un error
        product.setCategory(category);
        return repo.save(product);
    }

    public Optional<Product> update(Long id, Product product){
        Optional<Product> optional = repo.findById(id);
        if (optional.isEmpty()){
            return optional.empty();
        }

        Product productDB = optional.get();
        productDB.setName(product.getName());
        productDB.setPrice(product.getPrice());
        productDB.setStock(product.getStock());

        return Optional.of(repo.save(productDB));
    }

    public boolean delete(Long id){
        if (!repo.existsById(id)){ //El ! solo significa negación
            return false;
        }
        repo.deleteById(id);
        return true;
    }

}
