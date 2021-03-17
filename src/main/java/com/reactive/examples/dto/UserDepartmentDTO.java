package com.reactive.examples.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserDepartmentDTO {
    private Integer userId;
    private String userName;
    private int age;
    private BigDecimal salary;
    private Integer departmentId;
    private String departmentName;
    private String loc;
}
