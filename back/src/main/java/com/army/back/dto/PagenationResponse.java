package com.army.back.dto;

import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PagenationResponse<T> {
    private List<T> content; 
    private int totalPages;  

    public PagenationResponse(List<T> content, int totalPages, int totalRecords) {
        this.content = content;
        this.totalPages = totalPages;
    }

}
