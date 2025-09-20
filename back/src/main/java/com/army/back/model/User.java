package com.army.back.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String armyNumber;
    private String password;
    private String name;
    private String email;
    private String armyType;
    private Date enlistmentDate;
    private Date dischargeDate;
}
