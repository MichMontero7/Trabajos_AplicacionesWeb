package com.example.tiendaproductos.controller;

import com.example.tiendaproductos.model.Product;
import com.example.tiendaproductos.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/products")
public class ProductController {
    private ProductService service;

    public ProductController(ProductService service){
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id){
        Optional<Product> product = service.findById(id);
        if (product.isPresent()){
            return ResponseEntity.ok(product.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Product> save(@RequestBody Product product){
        service.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable Long id){
        boolean deleted = service.delete(id);
        if (deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> putMethod(@PathVariable Long id, @RequestBody Product product){
        Optional<Product> optional = service.update(id, product);
        if (optional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optional.get());
        }
        return ResponseEntity.ok(optional.get());
        //para pasar el objeto es con un body para que regrese algo, el build() es para regresar nada
    }
}
