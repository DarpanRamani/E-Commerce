package com.example.shoppingverse.controller;

import com.example.shoppingverse.Enum.CardType;
import com.example.shoppingverse.dto.request.CardRequestDto;
import com.example.shoppingverse.dto.response.CardResponseDto;
import com.example.shoppingverse.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;

    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto){
        try{
            CardResponseDto cardResponseDto = cardService.addCard(cardRequestDto);
            return new ResponseEntity(cardResponseDto, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_all_same_card")
    public ResponseEntity getCard(@RequestParam CardType cardType){
        List<CardResponseDto> list = cardService.getCard(cardType);
        return new ResponseEntity(list,HttpStatus.FOUND);
    }
}
