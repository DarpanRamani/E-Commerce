package com.example.shoppingverse.transformer;

import com.example.shoppingverse.Enum.ProductStatus;
import com.example.shoppingverse.dto.request.ProductRequestDto;
import com.example.shoppingverse.dto.response.*;
import com.example.shoppingverse.model.Product;

public class ProductTransformer {
    public static Product ProductRequestDtoToProduct(ProductRequestDto productRequestDto){
        return Product.builder()
                .productName(productRequestDto.getProductName())
                .price(productRequestDto.getPrice())
                .availableQuantity(productRequestDto.getAvailableQuantity())
                .category(productRequestDto.getCategory())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }
    public static ProductResponseDto ProductToProductResponseDto(Product product){
        return ProductResponseDto.builder()
                .sellerName(product.getSeller().getName())
                .productName(product.getProductName())
                .productStatus(product.getProductStatus())
                .price(product.getPrice())
                .category(product.getCategory())
                .availableQuantity(product.getAvailableQuantity())
                .build();
    }

    public static ProductResponseDto1 ProductToProductResponseDto1(Product product){
        return ProductResponseDto1.builder()
                .productName(product.getProductName())
                .sellerName(product.getSeller().getName())
                .quantity(product.getAvailableQuantity())
                .productStatus(product.getProductStatus())
                .build();
    }

    public static ProductResponseBySellerEmailDto ProductToProductResponseBySellerEmailDto(Product product){
        return ProductResponseBySellerEmailDto.builder()
                .name(product.getProductName())
                .productCategory(product.getCategory())
                .productStatus(product.getProductStatus())
                .price(product.getPrice())
                .quantity(product.getAvailableQuantity())
                .build();
    }

    public static ProductOutOfStockResponseDto ProductToProductOutOfStockResposnseDto(Product product){
        return ProductOutOfStockResponseDto.builder()
                .name(product.getProductName())
                .productCategory(product.getCategory())
                .build();
    }

    public static ChepestProductResponseDto ProductToChepestProductResponseDto(Product product){
        return ChepestProductResponseDto.builder()
                .name(product.getProductName())
                .price(product.getPrice())
                .quantity(product.getAvailableQuantity())
                .build();
    }
}
