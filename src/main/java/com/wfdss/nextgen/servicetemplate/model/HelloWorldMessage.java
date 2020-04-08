package com.wfdss.nextgen.servicetemplate.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "helloworldmessage")
@NoArgsConstructor
@AllArgsConstructor
public class HelloWorldMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    @ApiModelProperty(value = "The ID that uniquely identifies a HelloWorldMessage object.",
            required = true, allowEmptyValue = false)
    private Integer id;

    @Column(name = "MESSAGE_TEXT", updatable = true, nullable = false)
    @ApiModelProperty(value = "The message text of the HelloWorldMessage object.", required =
            true, example = "Hello World!")
    private String messageText;
}
