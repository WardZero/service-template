package com.wfdss.nextgen.servicetemplate.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@ApiModel
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    @ApiModelProperty(value = "The ID that uniquely identifies a Project object.")
    private Long projectId;

    @ApiModelProperty(value = "The name of the project.", required = true, allowEmptyValue = false)
    private String projectName;

    @ApiModelProperty(value = "The Project Manager", required = true, allowEmptyValue = false)
    @ManyToOne
    private Employee projectManager;

    @ApiModelProperty(value = "Employees who are team members of this project.")
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Set<Employee> teamMembers;

    @JsonManagedReference
    @ApiModelProperty(value = "Stories for the Project.")
    @OneToMany(mappedBy = "project", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Story> stories;

    @JsonManagedReference
    @ApiModelProperty(value = "The timeline of dates for the Project.")
    @OneToOne(mappedBy = "parentProject", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Timeline timeline;

    public void addStory(Story story) {
        story.setProject(this);
        stories.add(story);
    }
}
