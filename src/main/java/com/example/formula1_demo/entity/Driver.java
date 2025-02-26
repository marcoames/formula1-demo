package com.example.formula1_demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "drivers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String driver;
    private String nationality;
    
    @Column(columnDefinition = "TEXT")
    private String seasons;
    
    private Integer championships;
    private Integer raceEntries;
    private Integer raceStarts;
    private Integer polePositions;
    private Integer raceWins;
    private Integer podiums;
    private Integer fastestLaps;
    private Double points;
    private Boolean active;
    
    @Column(columnDefinition = "TEXT")
    private String championshipYears;
    
    private Integer decade;
    private Double poleRate;
    private Double startRate;
    private Double winRate;
    private Double podiumRate;
    private Double fastLapRate;
    private Double pointsPerEntry;
    private Integer yearsActive;
    private Boolean champion;
}