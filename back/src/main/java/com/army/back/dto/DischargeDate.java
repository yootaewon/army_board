package com.army.back.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class DischargeDate {
    private LocalDate enlistmentDate;
    private LocalDate dischargeDate;
    private String persent;
}
