package com.example.shoppingverse.transformer;

import com.example.shoppingverse.dto.request.CustomerRequestDto;
import com.example.shoppingverse.dto.response.CustomerDeleteResponseDto;
import com.example.shoppingverse.dto.response.CustomerResponseDto;
import com.example.shoppingverse.dto.response.CustomerResponseDtoByAge;
import com.example.shoppingverse.model.Customer;

public class CustomerTransformer {
    public static Customer CustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){
       return Customer.builder()
                .name(customerRequestDto.getName())
                .gender(customerRequestDto.getGender())
                .emailId(customerRequestDto.getEmailId())
                .mobNo(customerRequestDto.getMobNo()).build();
    }

    public  static CustomerResponseDto CustomerToCustomerResponseDto(Customer customer){
       return CustomerResponseDto.builder()
                .name(customer.getName())
                .gender(customer.getGender())
                .emailId(customer.getEmailId())
                .mobNo(customer.getMobNo())
                .build();
    }

    public static CustomerResponseDtoByAge CustomerToCustomerResponseDtoByAge(Customer customer){
        return CustomerResponseDtoByAge.builder()
                .name(customer.getName())
                .emailId(customer.getEmailId())
                .mobNo(customer.getMobNo())
                .build();
    }

    public static CustomerDeleteResponseDto CustomerToCustomerDeleteResponseDto(Customer customer){
        return CustomerDeleteResponseDto.builder()
                .name(customer.getName())
                .emailId(customer.getEmailId())
                .mobNo(customer.getMobNo())
                .build();
    }
}
