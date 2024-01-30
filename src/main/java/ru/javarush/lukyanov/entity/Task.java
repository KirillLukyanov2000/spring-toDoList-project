package ru.javarush.lukyanov.entity;

import lombok.ToString;
import ru.javarush.lukyanov.enums.Status;

import javax.persistence.*;

@Entity
@Table
@ToString
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String description;
    @Column(name = "status", columnDefinition = "tinyint")
    @Enumerated(EnumType.ORDINAL)
    private Status status;
}
