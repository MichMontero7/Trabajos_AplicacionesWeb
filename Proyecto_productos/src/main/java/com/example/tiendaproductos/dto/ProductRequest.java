package com.example.tiendaproductos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ProductRequest(
        @NotBlank(message = "No debe haber vació, ni ser menor que 0")
        String name,
        @PositiveOrZero
        @NotNull
        Double price,
        @Positive
        @NotNull
        int stock,
        @NotNull
        Long categoryId
) {

}
