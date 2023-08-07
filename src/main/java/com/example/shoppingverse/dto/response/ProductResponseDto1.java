package com.example.shoppingverse.dto.response;

import com.example.shoppingverse.Enum.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponseDto1 {
    String productName;
    String sellerName;
    Integer quantity;
    ProductStatus productStatus;
}
