package com.army.back.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Leave {
    
    private long leaveId;
    private String armyNumber;
    private String leaveType;
    private int leaveDays;
    private String reason;
}
