package com.example.shoppingverse.model;

import com.example.shoppingverse.Enum.ProductCategory;
import com.example.shoppingverse.Enum.ProductStatus;
import jakarta.persistence.*;
import jdk.jfr.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String productName;

    int price;

    int availableQuantity;

    @Enumerated(EnumType.STRING)
    ProductCategory category;

    @Enumerated(EnumType.STRING)
    ProductStatus productStatus;

    @JoinColumn
    @ManyToOne
    Seller seller;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    List<Item> items = new ArrayList<>();
}
