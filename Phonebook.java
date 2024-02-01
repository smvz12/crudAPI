package com.java.Phonebook.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Phonebook")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class Phonebook {
    private String name;
    private String phoneNumber;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


}
