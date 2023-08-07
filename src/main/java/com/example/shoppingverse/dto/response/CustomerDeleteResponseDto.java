package com.example.shoppingverse.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerDeleteResponseDto {
    String name;
    String emailId;
    String mobNo;
}
