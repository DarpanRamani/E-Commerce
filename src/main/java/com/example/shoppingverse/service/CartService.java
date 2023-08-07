package com.example.shoppingverse.service;

import com.example.shoppingverse.dto.request.CheckOutCartRequestDto;
import com.example.shoppingverse.dto.request.ItemRequestDto;
import com.example.shoppingverse.dto.response.CartResponseDto;
import com.example.shoppingverse.dto.response.OrderResponseDto;
import com.example.shoppingverse.exception.CustomerNotFoundException;
import com.example.shoppingverse.exception.EmptyCartException;
import com.example.shoppingverse.exception.InvalidCardException;
import com.example.shoppingverse.model.*;
import com.example.shoppingverse.repository.*;
import com.example.shoppingverse.transformer.CartTransformer;
import com.example.shoppingverse.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class CartService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderEntityRepository orderEntityRepository;

    public CartResponseDto addItemToCart(ItemRequestDto itemRequestDto,Item item) {
        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmail());
        Product product = productRepository.findById(itemRequestDto.getProductId()).get();

        Cart cart = customer.getCart();
        cart.setCartTotal(cart.getCartTotal() + product.getPrice()* itemRequestDto.getRequiredQuantity());

        item.setCart(cart);
        item.setProduct(product);
        Item savedItem = itemRepository.save(item);
        cart.getItems().add(savedItem);

        product.getItems().add(savedItem);
        Cart savedCart = cartRepository.save(cart);
        productRepository.save(product);

        //prepare cartResponse Dto
        return CartTransformer.CartToCartResponseDto(savedCart);
    }

    public OrderResponseDto checkOutCart(CheckOutCartRequestDto checkOutCartRequestDto) {
        Customer customer = customerRepository.findByEmailId(checkOutCartRequestDto.getCustomerEmail());
        if(customer == null){
            throw new CustomerNotFoundException("Customer doesn't exist");
        }

        Card card = cardRepository.findByCardNo(checkOutCartRequestDto.getCardNo());
        Date currentDate = new Date();
        if(card == null || card.getCvv() != checkOutCartRequestDto.getCvv() || currentDate.after(card.getValidTill())){
            throw new InvalidCardException("Card is Not Valid");
        }

        Cart cart = customer.getCart();
        if(cart.getItems().size() == 0){
            throw new EmptyCartException("Your Cart is Empty");
        }

        OrderEntity order = orderService.placeOrder(cart,card);
        resetCart(cart);

        OrderEntity savedOrder = orderEntityRepository.save(order);
        return OrderTransformer.OrderToOrderResponseDto(savedOrder);
    }
    public void resetCart(Cart cart){
        cart.setCartTotal(0);
        for(Item item : cart.getItems()){
            item.setCart(null);
        }
        cart.setItems(new ArrayList<>());

    }
}
