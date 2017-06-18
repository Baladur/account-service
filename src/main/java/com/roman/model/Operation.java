package com.roman.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by roman on 17.06.2017.
 */
@Getter
@Setter
@Entity
@Table(name = "Operation")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long sourceAccId;
    private Long destAccId;
    private float sum;
    private Date date;

    public Operation(Long id, Long sourceAccId, Long destAccId, float sum, Date date) {
        this.id = id;
        this.sourceAccId = sourceAccId;
        this.destAccId = destAccId;
        this.sum = sum;
        this.date = date;
    }

    public Operation() {}
}
