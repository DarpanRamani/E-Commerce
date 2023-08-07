package com.example.shoppingverse.dto.response;

import com.example.shoppingverse.Enum.ProductCategory;
import com.example.shoppingverse.Enum.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponseBySellerEmailDto {
    String name;
    Integer price;
    Integer quantity;
    ProductCategory productCategory;
    ProductStatus productStatus;
}
