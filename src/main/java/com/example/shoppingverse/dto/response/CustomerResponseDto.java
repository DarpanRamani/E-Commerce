package com.example.shoppingverse.dto.response;

import com.example.shoppingverse.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerResponseDto {
    String name;

    String emailId;

    String mobNo;

    Gender gender;
}
