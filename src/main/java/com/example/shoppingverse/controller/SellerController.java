package com.example.shoppingverse.controller;

import com.example.shoppingverse.dto.request.SellerRequestDto;
import com.example.shoppingverse.dto.response.SellerResponseByEmailDto;
import com.example.shoppingverse.dto.response.SellerResponseDto;
import com.example.shoppingverse.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto){
        SellerResponseDto sellerResponseDto = sellerService.addSeller(sellerRequestDto);
        return new ResponseEntity(sellerResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/get_by_mail")
    public ResponseEntity getSellerByEmail(@RequestParam("email")String email){
        try{
            SellerResponseByEmailDto sellerResponseByEmailDto = sellerService.getSellerByEmail(email);
            return new ResponseEntity(sellerResponseByEmailDto,HttpStatus.FOUND);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get_by_id")
    public ResponseEntity getSellerById(@RequestParam("id") int id){
        try{
            SellerResponseByEmailDto sellerResponseByEmailDto = sellerService.getSellerById(id);
            return new ResponseEntity(sellerResponseByEmailDto,HttpStatus.FOUND);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateemail")
    public ResponseEntity updateEmail(@RequestParam("id") int sellerId,@RequestParam("email") String email){
        try{
            SellerResponseDto sellerResponseDto = sellerService.updateEmail(sellerId,email);
            return new ResponseEntity(sellerResponseDto,HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_all_seller")
    public ResponseEntity getAllSeller(){
        List<SellerResponseDto> list = sellerService.getAllSeller();
        return new ResponseEntity(list, HttpStatus.FOUND);
    }

    @DeleteMapping("/deletebyid")
    public ResponseEntity deleteById(@RequestParam("id")int id){
        try{
            SellerResponseDto sellerResponseDto = sellerService.deleteById(id);
            return new ResponseEntity(sellerResponseDto,HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
