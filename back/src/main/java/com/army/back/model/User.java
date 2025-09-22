package com.army.back.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import com.army.back.enums.ArmyType;  // ArmyType enum import
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
    private String email;
    private String phoneNumber;
    private ArmyType armyType;  
    private String name;
    private String dept;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate enlistmentDate;

    private LocalDate dischargeDate;
}
