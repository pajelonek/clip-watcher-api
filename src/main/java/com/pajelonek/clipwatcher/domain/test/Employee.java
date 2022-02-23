package com.pajelonek.clipwatcher.domain.test;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class Employee {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String dept;
    private BigDecimal salary;

}