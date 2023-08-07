package com.example.shoppingverse.controller;

import com.example.shoppingverse.Enum.ProductCategory;
import com.example.shoppingverse.dto.request.ProductRequestDto;
import com.example.shoppingverse.dto.response.*;
import com.example.shoppingverse.model.Product;
import com.example.shoppingverse.repository.ProductRepository;
import com.example.shoppingverse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto){
        try {
            ProductResponseDto productResponseDto = productService.addProduct(productRequestDto);
            return new ResponseEntity(productResponseDto, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    //Get all products of particular category and price is greater than 2000
    //using custom sql query
    @GetMapping("/get-by-category-and-price-greater-than")
    public ResponseEntity getProductByCategoryAndPriceGreaterThan(@RequestParam("price") int price,
                                                                  @RequestParam("category")ProductCategory category){
        List<ProductResponseDto> productResponseDtoList = productService.getProductByCategoryAndPriceGreaterThan(price,category);
        return new ResponseEntity(productResponseDtoList,HttpStatus.FOUND);
    }

    @GetMapping("/get")
    public ResponseEntity getAllProductByCategory(@RequestParam("category") ProductCategory productCategory){
        List<ProductResponseDto1> list = productService.getAllProductByCategory(productCategory);
        return new ResponseEntity(list,HttpStatus.FOUND);
    }

    @GetMapping("/getproductbyemail")
    public ResponseEntity getProductBySellerEmail(@RequestParam("email") String email){
        try{
            List<ProductResponseBySellerEmailDto> productResponseBySellerEmailDtos = productService.getProductBySellerEmail(email);
            return new ResponseEntity(productResponseBySellerEmailDtos,HttpStatus.FOUND);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/outofstock")
    public ResponseEntity getOutOfStock(){
        List<ProductOutOfStockResponseDto> productOutOfStockResponseDtos = productService.getOutOfStock();
        return new ResponseEntity(productOutOfStockResponseDtos,HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity getAvailable(){
        List<ProductOutOfStockResponseDto> productAvailable = productService.getAvailable();
        return new ResponseEntity(productAvailable,HttpStatus.OK);
    }

    @GetMapping("/getquantity")
    public ResponseEntity getByQuantity(@RequestParam("quantity")int quantity){
        List<ProductOutOfStockResponseDto> productquantity = productService.getByQuantity(quantity);
        return new ResponseEntity(productquantity,HttpStatus.FOUND);
    }

    @GetMapping("/cheapestproduct")
    public ResponseEntity getCheapestProduct(){
        List<ChepestProductResponseDto> list = productService.getCheapestProduct();
        return new ResponseEntity(list,HttpStatus.FOUND);
    }

    @GetMapping("/costliestproduct")
    public ResponseEntity getCostliestProduct(){
        List<ChepestProductResponseDto> list = productService.getCostliestProduct();
        return new ResponseEntity(list,HttpStatus.FOUND);
    }

    @GetMapping("/getproduct")
    public ResponseEntity getProduct(@RequestParam int productId,@RequestParam int sellerId){
        try{
            ChepestProductResponseDto list = productService.getProduct(productId,sellerId);
            return new ResponseEntity(list,HttpStatus.FOUND);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
