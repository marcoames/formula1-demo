package com.example.formula1_demo.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "cabins")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cabin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double price;

    private String description;

    private Boolean isAvailable;

}
