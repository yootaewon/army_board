package com.army.back.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.army.back.dto.LeaveRequest;

@Mapper
public interface LeaveRequestMapper {

    void insertLeave(@Param("leave") LeaveRequest leave, @Param("armyNumber") String armyNumber);

    void deleteLeave(@Param("leaveIds") List<Long> leaveIds);

    void modifyLeave(LeaveRequest leave);

    List<LeaveRequest> selectLeaveHistory(@Param("armyNumber") String armyNumber, 
                                          @Param("offset") int offset, 
                                          @Param("limit") int limit);

    int countLeaveHistory(String armyNumber);
    
} 