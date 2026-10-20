package com.example.tiendaproductos.service;

import com.example.tiendaproductos.dto.PalabraRequest;
import com.example.tiendaproductos.exception.ResourceConflictException;
import com.example.tiendaproductos.exception.ResourceNotFoundException;
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
        Category category = repoCategory.findById(categoryId).orElseThrow(
                ()-> new ResourceNotFoundException("Category not found with id: " + categoryId)
        ); //Arroja un error cuando no encuentre el optional para cat
        product.setCategory(category);
        return repo.save(product);
    }

    public Product update(Long id, Product product, Long categoryId){

        Product productDB = repo.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Product not found with id: " + id)
        );
        Category category = repoCategory.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category not found with id: " + categoryId)
        );
        /*if (optional.isEmpty()){
            return optional.empty();
        }*/
        //Product productDB = optional.get();

        productDB.setName(product.getName()+""); // el +"" es para forzar que sea string
        productDB.setPrice(product.getPrice());
        productDB.setStock(product.getStock());
        productDB.setCategory(category);
        return repo.save(productDB);
    }

    public boolean delete(Long id){
        if (!repo.existsById(id)){ //El ! solo significa negación
            return false;
        }
        repo.deleteById(id);
        return true;
    }

    public Product modify(long id, PalabraRequest palabra){
        Product productBD = repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product not found with id: " + id)
        );

        if (palabra.getWord().equalsIgnoreCase("add") || palabra.getWord().equalsIgnoreCase("remover")){
            if (palabra.getWord().equalsIgnoreCase("add")){
                productBD.setStock(productBD.getStock() + palabra.getQuantity());
            }else {
                if (palabra.getQuantity() > productBD.getStock()){
                    throw new ResourceConflictException("No tiene suficiente stock");
                }else {
                    productBD.setStock(productBD.getStock() - palabra.getQuantity());
                }
            }
        }else {
            throw new ResourceNotFoundException("Word not found :(" );
        }

        return repo.save(productBD);
    }

}