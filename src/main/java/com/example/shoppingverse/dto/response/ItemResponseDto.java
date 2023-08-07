package com.example.shoppingverse.dto.response;

import com.example.shoppingverse.Enum.ProductCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemResponseDto {
    int quantityAdded;
    String itemName;
    int itemPrice;
    ProductCategory category;
}
