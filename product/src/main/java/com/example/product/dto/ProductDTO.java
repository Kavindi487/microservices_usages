package com.example.product.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    @Id
    private int id;
    private int productId;
    private String productName;
    private String description;
    private int forSale;
}
