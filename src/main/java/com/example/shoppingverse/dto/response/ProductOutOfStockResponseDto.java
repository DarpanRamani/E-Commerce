package com.example.shoppingverse.dto.response;

import com.example.shoppingverse.Enum.ProductCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.security.PrivateKey;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductOutOfStockResponseDto {
    String name;
    ProductCategory productCategory;
}
