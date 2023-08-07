package com.example.shoppingverse.controller;

import com.example.shoppingverse.Enum.CardType;
import com.example.shoppingverse.dto.request.CustomerRequestDto;
import com.example.shoppingverse.dto.response.CustomerDeleteResponseDto;
import com.example.shoppingverse.dto.response.CustomerResponseDto;
import com.example.shoppingverse.dto.response.CustomerResponseDtoByAge;
import com.example.shoppingverse.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
     @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequestDto customerRequestDto){
        CustomerResponseDto responseDto = customerService.addCustomer(customerRequestDto);
        return  new ResponseEntity(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/getall")
    public ResponseEntity getAll(){
        List<CustomerResponseDto> list = customerService.getAll();
        return new ResponseEntity(list,HttpStatus.FOUND);
    }

    @GetMapping("/getbyMob/{mobNo}")
    public ResponseEntity getByMobNo(@PathVariable("mobNo") String mobNo){
        try{
            CustomerResponseDto customerResponseDto = customerService.getByMobNo(mobNo);
            return new ResponseEntity(customerResponseDto,HttpStatus.FOUND);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usingvisa")
    public ResponseEntity getByCard(@RequestParam CardType cardType){
        List<CustomerResponseDto> customerResponseDtos = customerService.getByCard(cardType);
        return new ResponseEntity(customerResponseDtos,HttpStatus.FOUND);
    }

    @PutMapping("/updatebyemail")
    public ResponseEntity updateByEmail(@RequestParam String email,@RequestParam String mobNo){
        try{
            CustomerResponseDto customerResponseDto = customerService.updateByEmail(email,mobNo);
            return new ResponseEntity(customerResponseDto,HttpStatus.FOUND);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getbyid")
    public ResponseEntity getById(@RequestParam Integer id){
        try{
            CustomerResponseDto customerResponseDto = customerService.getById(id);
            return new ResponseEntity(customerResponseDto,HttpStatus.FOUND);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getbyagebetween")
    public ResponseEntity getByAge(@RequestParam Integer startAge,@RequestParam Integer endAge){
        List<CustomerResponseDtoByAge> customerResponseDtoByAges = customerService.getByAge(startAge,endAge);
        return new ResponseEntity(customerResponseDtoByAges,HttpStatus.FOUND);
    }

    @GetMapping("/getbyage/{age}")
    public ResponseEntity getByNewage(@PathVariable("age") Integer age){
        List<CustomerResponseDtoByAge> customerResponseDtoByAges = customerService.getByNewage(age);
        return new ResponseEntity(customerResponseDtoByAges,HttpStatus.FOUND);
    }

    @DeleteMapping("/deletebyemail")
    public ResponseEntity deleteByEmailId(@PathVariable String email){
        try{
            CustomerDeleteResponseDto customerDeleteResponseDto = customerService.deleteByEmailId(email);
            return new ResponseEntity(customerDeleteResponseDto,HttpStatus.OK);
        }
        catch(Exception e){
            return  new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deletebyid")
    public ResponseEntity deleteById(@RequestParam("id")int id){
        try{
            CustomerDeleteResponseDto customerDeleteResponseDto = customerService.deleteById(id);
            return new ResponseEntity(customerDeleteResponseDto,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
