package com.example.shoppingverse.dto.response;

import com.example.shoppingverse.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardResponseDto {
    String customerName;
    String cardNo;
    Date validTill;
    CardType cardType;
}
