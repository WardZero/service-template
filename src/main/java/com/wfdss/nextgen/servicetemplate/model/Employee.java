package com.wfdss.nextgen.servicetemplate.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@ApiModel
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    @ApiModelProperty(value = "The ID that uniquely identifies an Employee object.")
    private Long employeeId;

    @ApiModelProperty(value = "The name of the employee.")
    private String employeeName;

    @ApiModelProperty(value = "The salary of the employee.")
    private Integer employeeSalary;

    @ApiModelProperty(value = "The age of the employee.")
    private Integer employeeAge;

    @ApiModelProperty(value = "The path to the profile image of the employee.")
    private String profileImage;
}
