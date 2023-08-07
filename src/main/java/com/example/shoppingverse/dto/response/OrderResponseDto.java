package com.example.shoppingverse.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderResponseDto {

    String orderId;

    Date orderDate;

    String cardUsed;

    int orderTotal;

    String customerName;

    List<ItemResponseDto> item;
}
