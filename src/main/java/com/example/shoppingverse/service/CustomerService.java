package com.example.shoppingverse.service;

import com.example.shoppingverse.Enum.CardType;
import com.example.shoppingverse.dto.request.CustomerRequestDto;
import com.example.shoppingverse.dto.response.CustomerDeleteResponseDto;
import com.example.shoppingverse.dto.response.CustomerResponseDto;
import com.example.shoppingverse.dto.response.CustomerResponseDtoByAge;
import com.example.shoppingverse.exception.CustomerNotFoundException;
import com.example.shoppingverse.model.Cart;
import com.example.shoppingverse.model.Customer;
import com.example.shoppingverse.repository.CustomerRepository;
import com.example.shoppingverse.transformer.CustomerTransformer;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) {
        Customer customer = CustomerTransformer.CustomerRequestDtoToCustomer(customerRequestDto);

        Cart cart = new Cart();
        cart.setCartTotal(0);
        cart.setCustomer(customer);

        customer.setCart(cart);

        Customer savedCustomer = customerRepository.save(customer);  //these will save both customer and cart because customer is parent

        return CustomerTransformer.CustomerToCustomerResponseDto(savedCustomer);
    }

    public List<CustomerResponseDto> getAll() {
        List<CustomerResponseDto> list = new ArrayList<>();
        List<Customer> lists = customerRepository.findAll();
        for(Customer customer : lists){
            CustomerResponseDto customerResponseDto = CustomerTransformer.CustomerToCustomerResponseDto(customer);
            list.add(customerResponseDto);
        }
        return list;
    }

    public CustomerResponseDto getByMobNo(String mobNo) throws CustomerNotFoundException{
       if(customerRepository.findByMobNo(mobNo) == null){
           throw new CustomerNotFoundException("Give number is not found");
        }
        Customer customer = customerRepository.findByMobNo(mobNo);
       return CustomerTransformer.CustomerToCustomerResponseDto(customer);
    }

    public List<CustomerResponseDto> getByCard(CardType cardType) {
        List<CustomerResponseDto> list = new ArrayList<>();
        List<Customer> lists = customerRepository.findAll();
        for(Customer customer : lists){
            if(customer.getCards().contains(CardType.VISA)){
                CustomerResponseDto customerResponseDto = CustomerTransformer.CustomerToCustomerResponseDto(customer);
                list.add(customerResponseDto);
            }
        }
        return list;
    }

    public CustomerResponseDto updateByEmail(String email, String mobNo) throws CustomerNotFoundException{
        if(customerRepository.findByEmailId(email) == null){
            throw new CustomerNotFoundException("customer not found enter valid email");
        }
        Customer customer = customerRepository.findByEmailId(email);
        customer.setEmailId(email);
       Customer savedCustomer = customerRepository.save(customer);
       return CustomerTransformer.CustomerToCustomerResponseDto(savedCustomer);
    }


    public CustomerResponseDto getById(Integer id) throws CustomerNotFoundException{
        if(customerRepository.findById(id) == null){
            throw new CustomerNotFoundException("Customer not exist enter valid id");
        }
        Customer customer = customerRepository.findById(id).get();
        return CustomerTransformer.CustomerToCustomerResponseDto(customer);
    }

    public List<CustomerResponseDtoByAge> getByAge(Integer startAge, Integer endAge) {
        List<CustomerResponseDtoByAge> lists = new ArrayList<>();
        List<Customer> customers = customerRepository.findAllAge(startAge,endAge);
        for(Customer customer : customers){
            CustomerResponseDtoByAge customerResponseDtoByAge = CustomerTransformer.CustomerToCustomerResponseDtoByAge(customer);
            lists.add(customerResponseDtoByAge);
        }
        return lists;
    }

    public List<CustomerResponseDtoByAge> getByNewage(Integer age) {
        List<CustomerResponseDtoByAge> list = new ArrayList<>();
        List<Customer> customers = customerRepository.findByAge(age);
        for(Customer customer : customers){
            CustomerResponseDtoByAge customerResponseDtoByAge = CustomerTransformer.CustomerToCustomerResponseDtoByAge(customer);
            list.add(customerResponseDtoByAge);
        }
        return list;
    }

    public CustomerDeleteResponseDto deleteByEmailId(String email) throws CustomerNotFoundException{
        if(customerRepository.findByEmailId(email) == null){
            throw  new CustomerNotFoundException("Enter valid emailid");
        }
        Customer customer = customerRepository.findByEmailId(email);
        CustomerDeleteResponseDto customerDeleteResponseDto = CustomerTransformer.CustomerToCustomerDeleteResponseDto(customer);
        customerRepository.findByEmailId(email);
        return customerDeleteResponseDto;
    }

    public CustomerDeleteResponseDto deleteById(int id) throws CustomerNotFoundException{
        if(customerRepository.findById(id) == null){
            throw new CustomerNotFoundException("Enter valid id");
        }
        Customer customer = customerRepository.findById(id).get();
        CustomerDeleteResponseDto customerDeleteResponseDto = CustomerTransformer.CustomerToCustomerDeleteResponseDto(customer);
        customerRepository.deleteById(id);
        return customerDeleteResponseDto;
    }
}
