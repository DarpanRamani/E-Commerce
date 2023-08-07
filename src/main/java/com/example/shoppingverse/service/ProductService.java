package com.example.shoppingverse.service;

import com.example.shoppingverse.Enum.ProductCategory;
import com.example.shoppingverse.Enum.ProductStatus;
import com.example.shoppingverse.dto.request.CheckOutCartRequestDto;
import com.example.shoppingverse.dto.request.ProductRequestDto;
import com.example.shoppingverse.dto.response.*;
import com.example.shoppingverse.exception.InvalidSellerException;
import com.example.shoppingverse.exception.ProductNotFoundException;
import com.example.shoppingverse.exception.SellerNotFoundException;
import com.example.shoppingverse.model.Product;
import com.example.shoppingverse.model.Seller;
import com.example.shoppingverse.repository.ProductRepository;
import com.example.shoppingverse.repository.SellerRepository;
import com.example.shoppingverse.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SellerRepository sellerRepository;
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
        Seller seller = sellerRepository.findByEmailId(productRequestDto.getSellerEmail());
        if(seller == null){
            throw new SellerNotFoundException("Seller doesn't exist");
        }
        //dtp -> entity
        Product product = ProductTransformer.ProductRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);
        seller.getProducts().add(product);

        Seller savedSeller = sellerRepository.save(seller);
        List<Product> productList = savedSeller.getProducts();
        Product latestProduct = productList.get(productList.size() - 1);

        //product -> response
        return ProductTransformer.ProductToProductResponseDto(latestProduct);

    }

    public List<ProductResponseDto> getProductByCategoryAndPriceGreaterThan(int price, ProductCategory category){

        List<Product> products = productRepository.getProductByCategoryAndPriceGreaterThan(price,category);

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product : products){
            productResponseDtos.add(ProductTransformer.ProductToProductResponseDto(product));
        }
        return productResponseDtos;
    }

    public List<ProductResponseDto1> getAllProductByCategory(ProductCategory productCategory) {
        List<ProductResponseDto1> list = new ArrayList<>();
        List<Product> lists = productRepository.findByProductCategory(productCategory);
        for(Product product : lists){
            list.add(ProductTransformer.ProductToProductResponseDto1(product));
        }
        return list;
    }

    public List<ProductResponseBySellerEmailDto> getProductBySellerEmail(String email) throws InvalidSellerException {
        if(sellerRepository.findByEmailId(email) == null){
            throw new InvalidSellerException("Enter valid seller mail");
        }
        Seller seller = sellerRepository.findByEmailId(email);
        List<ProductResponseBySellerEmailDto> list = new ArrayList<>();
        List<Product> productList = seller.getProducts();
        for(Product product : productList){
            list.add(ProductTransformer.ProductToProductResponseBySellerEmailDto(product));
        }
        return list;
    }

    public List<ProductOutOfStockResponseDto> getOutOfStock() {
        List<ProductOutOfStockResponseDto> list = new ArrayList<>();
        List<Product> lists = productRepository.findAll();
        for(Product product : lists){
            if(product.getProductStatus() == ProductStatus.OUT_OF_STOCK){
                ProductOutOfStockResponseDto productOutOfStockResponseDto = ProductTransformer.ProductToProductOutOfStockResposnseDto(product);
                    list.add(productOutOfStockResponseDto);
            }
        }
        return list;
    }

    public List<ProductOutOfStockResponseDto> getAvailable() {
        List<ProductOutOfStockResponseDto> list = new ArrayList<>();
        List<Product> productList = productRepository.findAll();
        for(Product product : productList){
            if(product.getProductStatus() == ProductStatus.AVAILABLE){
                ProductOutOfStockResponseDto productAvailable = ProductTransformer.ProductToProductOutOfStockResposnseDto(product);
                list.add(productAvailable);
            }
        }
        return list;
    }

    public List<ProductOutOfStockResponseDto> getByQuantity(int quantity) {
        List<ProductOutOfStockResponseDto> list = new ArrayList<>();
        List<Product> productList = productRepository.findAll();
        for(Product product : productList){
            if(product.getAvailableQuantity() > quantity){
                ProductOutOfStockResponseDto productQuantity = ProductTransformer.ProductToProductOutOfStockResposnseDto(product);
                list.add(productQuantity);
            }
        }
        return list;
    }

    public List<ChepestProductResponseDto> getCheapestProduct() {
        List<ChepestProductResponseDto> list = new ArrayList<>();
        List<Product> productList = productRepository.findTop5ByOrderByPriceAsc();
        for(Product product : productList){
            ChepestProductResponseDto chepestProductResponseDto = ProductTransformer.ProductToChepestProductResponseDto(product);
            list.add(chepestProductResponseDto);
        }
        return list;
    }

    public List<ChepestProductResponseDto> getCostliestProduct() {
        List<ChepestProductResponseDto> list = new ArrayList<>();
        List<Product> productList = productRepository.findTop5ByOrderByPriceDesc();
        for(Product product : productList){
            ChepestProductResponseDto costliestproduct = ProductTransformer.ProductToChepestProductResponseDto(product);
            list.add(costliestproduct);
        }
        return list;
    }


    public ChepestProductResponseDto getProduct(int productId, int sellerId) throws ProductNotFoundException,InvalidSellerException{
        if(productRepository.findById(productId).get() == null){
            throw new ProductNotFoundException("Enter valid productId");
        }
        if(productRepository.findById(productId).get().getSeller().getId() != sellerId){
            throw new SellerNotFoundException("Enter valid SellerId");
        }

        Product product = productRepository.findById(productId).get();

        return ProductTransformer.ProductToChepestProductResponseDto(product);
    }
}
