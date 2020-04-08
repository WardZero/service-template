package com.wfdss.nextgen.servicetemplate.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    @ApiModelProperty(value = "The ID that uniquely identifies a Story object.")
    private Long storyId;

    @ApiModelProperty(value = "The name of the Story.", required = true, allowEmptyValue = false)
    private String StoryName;

    @JsonBackReference
    @ManyToOne
    @ApiModelProperty(value = "The parent project of this timeline", required = true, allowEmptyValue = false)
    @JoinColumn(name = "project_id")
    private Project project;
}
