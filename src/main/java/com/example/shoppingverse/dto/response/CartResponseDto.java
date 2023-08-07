package com.example.shoppingverse.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartResponseDto {
    String customerName;
    int cartTotal;
    List<ItemResponseDto> items;
}
