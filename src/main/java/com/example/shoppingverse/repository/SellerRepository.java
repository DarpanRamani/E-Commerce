package com.example.shoppingverse.repository;

import com.example.shoppingverse.dto.response.SellerResponseDto;
import com.example.shoppingverse.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {
    public Seller findByEmailId(String email);

}
