package com.example.shoppingverse.service;

import com.example.shoppingverse.dto.request.SellerRequestDto;
import com.example.shoppingverse.dto.response.SellerResponseByEmailDto;
import com.example.shoppingverse.dto.response.SellerResponseDto;
import com.example.shoppingverse.exception.SellerNotFoundException;
import com.example.shoppingverse.model.Seller;
import com.example.shoppingverse.repository.SellerRepository;
import com.example.shoppingverse.transformer.SellerTrasformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerService {
    @Autowired
    SellerRepository sellerRepository;

    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) {
        //sellerRequestDto -> Entity
        Seller seller = SellerTrasformer.SellerRequestDtoToSeller(sellerRequestDto);
        //Saving the data
        Seller savedSeller = sellerRepository.save(seller);
        //Entity -> sellerResponse
        return SellerTrasformer.SellerToSellerResponseDto(savedSeller);
    }

    public SellerResponseByEmailDto getSellerByEmail(String email) throws SellerNotFoundException{
        if(sellerRepository.findByEmailId(email) == null){
            throw new SellerNotFoundException("Please Enter valid EmailId");
        }
        Seller seller = sellerRepository.findByEmailId(email);
        return SellerTrasformer.SellerToSellerResponseByEmailDto(seller);
    }


    public SellerResponseByEmailDto getSellerById(int id) throws SellerNotFoundException{
        if(sellerRepository.findById(id) == null){
            throw new SellerNotFoundException("Enter valid seller ID");
        }
        Seller seller = sellerRepository.findById(id).get();
        return SellerTrasformer.SellerToSellerResponseByEmailDto(seller);
    }

    public SellerResponseDto updateEmail(int sellerId,String email) throws SellerNotFoundException{
        if(sellerRepository.findById(sellerId) == null){
            throw  new SellerNotFoundException("Please enter valid ID");
        }
        Seller seller = sellerRepository.findById(sellerId).get();
        seller.setEmailId(email);
        Seller savedSeller = sellerRepository.save(seller);
        return SellerTrasformer.SellerToSellerResponseDto(savedSeller);
    }


    public List<SellerResponseDto> getAllSeller() {
        List<Seller> seller = sellerRepository.findAll();
        List<SellerResponseDto> list = new ArrayList<>();
        for(Seller sellers : seller){
            SellerResponseDto sellerResponseDto = SellerTrasformer.SellerToSellerResponseDto(sellers);
            list.add(sellerResponseDto);
        }
        return list;
    }


    public SellerResponseDto deleteById(int id) throws SellerNotFoundException{
        if(sellerRepository.findById(id) == null){
            throw new SellerNotFoundException("Enter valid seller ID");
        }
        Seller seller = sellerRepository.findById(id).get();
        SellerResponseDto sellerResponseDto = SellerTrasformer.SellerToSellerResponseDto(seller);
        sellerRepository.deleteById(id);
        return sellerResponseDto;
    }
}
