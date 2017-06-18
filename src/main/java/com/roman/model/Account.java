package com.roman.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by roman on 17.06.2017.
 */
@Getter
@Setter
@Entity
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String surname;
    private String name;
    private String father;
    private float sum;

    public Account(Long id, String surname, String name, String father, float sum) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.father = father;
        this.sum = sum;
    }

    public Account() {}
}
