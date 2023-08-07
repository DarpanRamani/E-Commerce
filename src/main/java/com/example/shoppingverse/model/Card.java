package com.example.shoppingverse.model;

import com.example.shoppingverse.Enum.CardType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(unique = true)
    String cardNo;

    int cvv;

    Date validTill;

    @Enumerated(EnumType.STRING)
    CardType cardType;

    @ManyToOne
    @JoinColumn
    Customer customer;
}
