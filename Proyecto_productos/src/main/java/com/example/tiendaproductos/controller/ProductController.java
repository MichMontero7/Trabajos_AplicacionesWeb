package com.example.tiendaproductos.controller;

import com.example.tiendaproductos.dto.PalabraRequest;
import com.example.tiendaproductos.dto.ProductRequest;
import com.example.tiendaproductos.dto.ProductResponse;
import com.example.tiendaproductos.exception.ResourceNotFoundException;
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
    private final ProductService service;

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
        Product product = service.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product not found with id: " + id)
        );
        /*if (product.isPresent()){//Esto es para parsearlo, para que sea del mismo tipo
            return ResponseEntity.ok( toResponse(product.get()) );
        }else {
            return ResponseEntity.notFound().build();
        }*/
        return ResponseEntity.ok().build();
    }

    @PostMapping()
    public ResponseEntity<ProductResponse> save(@Valid  @RequestBody ProductRequest product){
        Product productSaved = service.save(toEntity(product), product.categoryId()); //Para igualar las entity

        return ResponseEntity.status(HttpStatus.CREATED).body( toResponse(productSaved) ) ;
        /*service.save(product);
        if (product.name().isEmpty()){
            ResponseEntity.badRequest().body("No puede estar vació, dame un nombre...");
        }*/
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
    public ResponseEntity<ProductResponse> update(@PathVariable Long id, @Valid @RequestBody ProductRequest product){
        Product optional = service.update(id, toEntity(product), product.categoryId());
        /*if (optional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body( toResponse(optional.get()) );
        }
        return ResponseEntity.ok(optional.get()); es para pasar el objeto es con un body para que regrese algo, el build() es para regresar nada
        return ResponseEntity.notFound().build();*/
        return ResponseEntity.ok(toResponse(optional));
    }

    @PatchMapping("/{id}/add-remove")
    public ResponseEntity<ProductResponse> addRemove(@PathVariable Long id, @Valid @RequestBody PalabraRequest request) {
        Product addOrRemove = service.modify(id, request);

        return ResponseEntity.status(HttpStatus.OK).body( toResponse(addOrRemove) );
    }

    private ProductResponse toResponse(Product product){
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock(),
                0L,
                " ");
    }

    private Product toEntity(ProductRequest request){
        Product product = new Product();
        product.setName(request.name());
        product.setPrice(request.price());
        product.setStock(request.stock());

        return product;
    }

}
//El optional es para no general nulos al vuelo, si es nulo regresa un optional...
