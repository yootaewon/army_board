package com.army.back.enums;

import java.time.LocalDate;

public enum ArmyType {
    army(1, 6,1,24),
    navy(1, 8,1,27),
    airForce(1, 9,1,28),
    marine(1, 6,1,28);

    private final int year;
    private final int month;
    private final int day;
    private final int annualLeave;


    ArmyType(int year, int month, int day, int annualLeave) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.annualLeave = annualLeave;
    }

    public LocalDate calculateDischargeDate(LocalDate enlistmentDate) {
        LocalDate dischargeDate = enlistmentDate.plusYears(year).plusMonths(month).minusDays(day);
        return dischargeDate;
    }

    public int getLeave(ArmyType armyType){
        return annualLeave; 
    }
}

