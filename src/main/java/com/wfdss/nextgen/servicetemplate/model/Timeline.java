package com.wfdss.nextgen.servicetemplate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@ApiModel
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Timeline {

    @Id
    private Long timelineId;

    @ApiModelProperty(value = "A Project's start date.", required = true, allowEmptyValue = false)
    private Date startDate;

    @ApiModelProperty(value = "A Project's MVP1 delivery date.", required = true, allowEmptyValue = false)
    private Date mvp1;

    @ApiModelProperty(value = "A Project's end date.", required = true, allowEmptyValue = false)
    private Date endDate;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @ApiModelProperty(value = "The parent project of this timeline", required = true, allowEmptyValue = false)
    @MapsId
    private Project parentProject;

}
