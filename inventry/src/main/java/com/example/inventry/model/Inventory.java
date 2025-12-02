package com.example.inventry.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@Table(name = "inventory") // <-- use your actual DB table name
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Inventory {
    @Id
    private int id;
    private int itemId;
    private int productId;
    private int quantity;
}
