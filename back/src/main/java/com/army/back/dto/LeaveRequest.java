package com.army.back.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequest {
    private long leaveRequestId;
    private String armyNumber;
    private String title;
    private String leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String period;
    private String destination;
    private String reason;
    private LocalDateTime regDateTime;
}
