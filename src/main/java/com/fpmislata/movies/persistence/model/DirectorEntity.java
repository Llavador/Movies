package com.fpmislata.movies.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "directors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DirectorEntity {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "birthyear")
    private int birthYear;
    @Column(name = "deathyear", nullable = true)
    private Integer deathYear;
}
