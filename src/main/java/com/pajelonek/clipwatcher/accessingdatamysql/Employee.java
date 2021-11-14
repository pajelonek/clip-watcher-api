package com.pajelonek.clipwatcher.accessingdatamysql;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@Data
public class Employee {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String dept;
    private long salary;

}