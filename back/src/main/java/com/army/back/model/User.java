package com.army.back.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String armyNumber;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;
    private String armyType;
    private LocalDate enlistmentDate;
    private LocalDate dischargeDate;
}
