package com.example.tiendaproductos.controller;

import com.example.tiendaproductos.dto.ProductRequest;
import com.example.tiendaproductos.dto.ProductResponse;
import com.example.tiendaproductos.model.Product;
import com.example.tiendaproductos.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity<List<ProductResponse>> findAll(){
        List<ProductResponse> listaResponse = new ArrayList<>();
        for (Product p : service.getAll()){
            listaResponse.add(toResponse(p));
        }
        return ResponseEntity.ok(listaResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id){
        Optional<Product> product = service.findById(id); //product es una entity y
        if (product.isPresent()){
                                     //Esto es para parsearlo, para que sea del mismo tipo
            return ResponseEntity.ok( toResponse(product.get()) );
        }else {
            return ResponseEntity.notFound().build();
        }


    }

    @PostMapping()
    public ResponseEntity<ProductResponse> save(@Valid  @RequestBody ProductRequest product){
        /*service.save(product);
        if (product.name().isEmpty()){
            ResponseEntity.badRequest().body("No puede estar vació, dame un nombre...");
        }*/
        Product productSaved = service.save(toEntity(product)); //Para igualar las entity

        return ResponseEntity.status(HttpStatus.CREATED).body( toResponse(productSaved) ) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        boolean deleted = service.delete(id);
        if (deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> putMethod(@Valid @PathVariable Long id, @RequestBody ProductRequest product){
        Optional<Product> optional = service.update(id, toEntity(product));
        if (optional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body( toResponse(optional.get()) );
        }
        //return ResponseEntity.ok(optional.get()); es para pasar el objeto es con un body para que regrese algo, el build() es para regresar nada
        return ResponseEntity.notFound().build();
    }

    private ProductResponse toResponse(Product product){
        return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getStock());
    }

    private Product toEntity(ProductRequest request){
        Product product = new Product();
        product.setName(request.name());
        product.setPrice(request.price());
        product.setStock(request.stock());

        return product;
    }
}
