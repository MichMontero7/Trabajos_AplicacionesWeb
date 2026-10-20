package com.example.tiendaproductos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PalabraRequest {
    @NotBlank(message = "No debe haber vació, ni ser menor que 0")
    String word;

    @Positive
    @NotNull
    int quantity;

    public PalabraRequest(){}

    public String getWord() { return word; }

    public void setWord(String word) { this.word = word; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
}
