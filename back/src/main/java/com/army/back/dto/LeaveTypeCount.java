package com.army.back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveTypeCount {
    private int comfort;
    private int reward;
    private int annual;
    private int discipline;
}
