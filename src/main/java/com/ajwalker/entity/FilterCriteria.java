package com.ajwalker.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class FilterCriteria {
    private String key;
    private String operation;
    private Object value;

}
