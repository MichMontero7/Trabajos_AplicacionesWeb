package com.example.tiendaproductos.dto;


import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
        @NotBlank(message = "El nombre no debe estar vacío")
        String name
) {
}
