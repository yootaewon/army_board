package com.army.back.enums;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public enum ArmyType {
    army(1, 6,1),
    navy(1, 8,1),
    airForce(1, 9,1),
    marine(1, 6,1) ;

    private final int year;
    private final int month;
    private final int day;

    ArmyType(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Map<String, Integer> getPeriod() {
        Map<String, Integer> period = new HashMap<>();
        period.put("year", year);
        period.put("month", month);
        period.put("day", day);
        return period;
    }

    public LocalDate calculateDischargeDate(LocalDate enlistmentDate) {
        LocalDate dischargeDate = enlistmentDate.plusYears(year).plusMonths(month).minusDays(day);
        return dischargeDate;
    }
}

