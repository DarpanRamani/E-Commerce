package com.example.shoppingverse.service;

import com.example.shoppingverse.dto.request.ItemRequestDto;
import com.example.shoppingverse.exception.CustomerNotFoundException;
import com.example.shoppingverse.exception.InsufficientQuantityException;
import com.example.shoppingverse.exception.ProductNotFoundException;
import com.example.shoppingverse.model.Customer;
import com.example.shoppingverse.model.Item;
import com.example.shoppingverse.model.Product;
import com.example.shoppingverse.repository.CustomerRepository;
import com.example.shoppingverse.repository.ProductRepository;
import com.example.shoppingverse.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;
    public Item createItem(ItemRequestDto itemRequestDto) {
        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmail());
        if(customer == null){
            throw new CustomerNotFoundException("Customer doesn't exist");
        }
        Optional<Product> optionalProduct = productRepository.findById(itemRequestDto.getProductId());
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("ProductId doesn't Exist");
        }
        Product product = optionalProduct.get();

        //check required Quantity
        if(product.getAvailableQuantity() < itemRequestDto.getRequiredQuantity())
        {
            throw new InsufficientQuantityException("Sorry Required Quantity is not available");
        }
        //create Item
        Item item = ItemTransformer.ItemRequestDtiToItem(itemRequestDto.getRequiredQuantity());

        return item;
    }
}
