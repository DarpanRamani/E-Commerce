package com.example.shoppingverse.dto.request;

import com.example.shoppingverse.Enum.CardType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardRequestDto {
    String customerMobileNo;
    String cardNo;
    int cvv;
    Date validTill;
    CardType cardType;
}
