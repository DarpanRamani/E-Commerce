package com.example.shoppingverse.service;

import com.example.shoppingverse.Enum.CardType;
import com.example.shoppingverse.dto.request.CardRequestDto;
import com.example.shoppingverse.dto.response.CardResponseDto;
import com.example.shoppingverse.exception.CustomerNotFoundException;
import com.example.shoppingverse.model.Card;
import com.example.shoppingverse.model.Customer;
import com.example.shoppingverse.repository.CardRepository;
import com.example.shoppingverse.repository.CustomerRepository;
import com.example.shoppingverse.transformer.CardTrasformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CardRepository cardRepository;
    public CardResponseDto addCard(CardRequestDto cardRequestDto) {
        Customer customer = customerRepository.findByMobNo(cardRequestDto.getCustomerMobileNo());

        if(customer == null){
            throw new CustomerNotFoundException("Customer doesn't exist");
        }

        //requestDto -> entity
        Card card = CardTrasformer.CardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);
        customer.getCards().add(card);

        Customer savedCustomer = customerRepository.save(customer);
        List<Card> cards = savedCustomer.getCards();
        Card latestCard = cards.get(cards.size()-1);

        //card -> responsedto
        CardResponseDto cardResponseDto = CardTrasformer.CardToCardResponseDto(latestCard);
        cardResponseDto.setCardNo(generateMaskedCard(latestCard.getCardNo()));

        return cardResponseDto;
    }

    public String generateMaskedCard(String cardNo) {
        int cardLength = cardNo.length();
        String maskedCard = "";
        for(int i=0;i<cardLength-4;i++){
            maskedCard += 'X';
        }
        maskedCard += cardNo.substring(cardLength-4);
        return maskedCard;
    }

    public List<CardResponseDto> getCard(CardType cardType) {
        List<Card> list = cardRepository.findAll();
        List<CardResponseDto> lists = new ArrayList<>();
        for(Card card : list){
            if(card.getCardType() == cardType) {
                CardResponseDto cardResponseDto = CardTrasformer.CardToCardResponseDto(card);
                lists.add(cardResponseDto);
            }
        }
        return lists;
    }

}
