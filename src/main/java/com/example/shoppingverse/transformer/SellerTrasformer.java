package com.example.shoppingverse.transformer;

import com.example.shoppingverse.dto.request.SellerRequestDto;
import com.example.shoppingverse.dto.response.SellerResponseByEmailDto;
import com.example.shoppingverse.dto.response.SellerResponseDto;
import com.example.shoppingverse.model.Seller;

public class SellerTrasformer {
    public static Seller SellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){
        return Seller.builder()
                .name(sellerRequestDto.getName())
                .emailId(sellerRequestDto.getEmailId())
                .panNo(sellerRequestDto.getPanNo())
                .build();
    }

    public static SellerResponseDto SellerToSellerResponseDto(Seller seller){
        return SellerResponseDto.builder()
                .name(seller.getName())
                .emailId(seller.getEmailId())
                .build();
    }

    public static SellerResponseByEmailDto SellerToSellerResponseByEmailDto(Seller seller){
        return SellerResponseByEmailDto.builder()
                .sellerName(seller.getName())
                .emailId(seller.getEmailId())
                .panNo(seller.getPanNo())
                .build();
    }
}
