package com.reactive.examples.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("users")
public class User {

    @Id
    private Integer id;

    @NotNull
    @ApiModelProperty(value = "Customer Name", example = "John Doe")
    @Pattern(regexp="^([a-zA-Z0-9\\-]{3,25})$",message="Invalid name")
    private String name;

    @NotNull
    @DecimalMin("18")
    @DecimalMax("65")
    @Digits(integer = 3,fraction = 0)
    @ApiModelProperty(value = "Customer Age",example = "28")
    private Integer age;

    @NotNull
    @Positive
    @DecimalMin(value = "1000.00",inclusive = true)
    @DecimalMax(value = "10000000000.00", inclusive = true)
    @Digits(integer = 16,fraction = 2)
    private BigDecimal salary;

    @NotNull
    @Email
    @ApiModelProperty(value = "Customer Email Id", example = "john.doe@gmail.com")
    private String email;

    @FutureOrPresent
    @ApiModelProperty(value = "Insert Date" ,example = "29-07-2029")
    private LocalDate insertDate;
}
