package com.wfdss.nextgen.servicetemplate.controller;

import com.wfdss.nextgen.servicetemplate.model.Employee;
import com.wfdss.nextgen.servicetemplate.model.HelloWorldMessage;
import com.wfdss.nextgen.servicetemplate.model.Project;
import com.wfdss.nextgen.servicetemplate.service.HelloWorldMessageService;
import com.wfdss.nextgen.servicetemplate.service.ProjectService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static javax.servlet.http.HttpServletResponse.*;


@Api(tags = "Hello World API")
@RestController
@RequestMapping("/helloWorld")
public class HelloWorldMessageController {
    private static final String APPLICATION_JSON_VALUE = "application/json";

    @Autowired
    HelloWorldMessageService helloWorldMessageService;

    @Autowired
    ProjectService projectService;

    @RequestMapping(method = RequestMethod.GET,
            value = "/",
            produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retrieves a list of HelloWorldMessage objects.")
    @ApiResponses(value = { @ApiResponse(code = SC_OK, message = "ok"),
            @ApiResponse(code = SC_BAD_REQUEST,
                    message = "Bad request. Please try again."),
            @ApiResponse(code = SC_NO_CONTENT, message = "No content found.")
    })
    public ResponseEntity<List<HelloWorldMessage>> getHelloWorldMessageList() {
        List<HelloWorldMessage> messageList = helloWorldMessageService.getHelloWorldMessageList();
        if(messageList.isEmpty()) {
            return new ResponseEntity<List<HelloWorldMessage>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<HelloWorldMessage>>(messageList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,
                    value = "/getRandom",
                    produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retrieves a random HelloWorldMessage object.")
    @ApiResponses(value = { @ApiResponse(code = SC_OK, message = "ok"),
                            @ApiResponse(code = SC_BAD_REQUEST,
                                    message = "Bad request. Please try again."),
                            @ApiResponse(code = SC_NO_CONTENT, message = "No content found.")
    })
    public ResponseEntity<HelloWorldMessage> getHelloWorldMessageObjectRandom() {
        HelloWorldMessage message = helloWorldMessageService.getHelloWorldMessageObjectRandom();
        if(message == null) {
            return new ResponseEntity<HelloWorldMessage>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<HelloWorldMessage>(message, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/getById",
            produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retrieves the HelloWorldMessage object with id = id from the database.")
    @ApiResponses(value = { @ApiResponse(code = SC_OK, message = "ok"),
                            @ApiResponse(code = SC_BAD_REQUEST,
                                    message = "Bad request. Please try again."),
                            @ApiResponse(code = SC_NO_CONTENT, message = "No content found.")
    })
    public ResponseEntity<HelloWorldMessage> getHelloWorldMessageObjectById(@ApiParam(value = "ID of the " +
            "requested HelloWorldObject", required = true) @RequestParam("id") Integer id) {
        HelloWorldMessage message = helloWorldMessageService.getHelloWorldMessageObjectById(id);
        if(message == null) {
            return new ResponseEntity<HelloWorldMessage>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<HelloWorldMessage>(message, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/add",
            produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Adds a HelloWorldMessage object to database, and returns the created " +
            "object.")
    @ApiResponses(value = { @ApiResponse(code = SC_OK, message = "ok"),
                            @ApiResponse(code = SC_BAD_REQUEST,
                                    message = "Bad request. Please try again."),
                            @ApiResponse(code = SC_NO_CONTENT, message = "No content found.")
    })
    public ResponseEntity<HelloWorldMessage> addHelloWorldMessage(@ApiParam(value = "The message " +
            "object to add to the database.", required = true) @RequestBody HelloWorldMessage message) {
        HelloWorldMessage addedMessage = helloWorldMessageService.addHelloWorldMessage(message);
        if(addedMessage == null) {
            return new ResponseEntity<HelloWorldMessage>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<HelloWorldMessage>(addedMessage, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT,
            value = "/update/{id}",
            produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Updates the text of a HelloWorldMessage object, and returns the " +
            "updated object.")
    @ApiResponses(value = { @ApiResponse(code = SC_OK, message = "ok"),
                            @ApiResponse(code = SC_BAD_REQUEST, message = "Message with provided" +
                                    " ID not found.")
    })
    public ResponseEntity<HelloWorldMessage> updateHelloWorldMessage(@ApiParam(value = "The ID of the " +
            "message to update.") @PathVariable("id") Integer id, @ApiParam(value = "The " +
            "message object to update.", required = true) @RequestBody HelloWorldMessage message) {
        try{
             HelloWorldMessage updatedMessage = helloWorldMessageService.updateHelloWorldMessage(id,
                     message);
            return new ResponseEntity<HelloWorldMessage>(updatedMessage, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<HelloWorldMessage>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/getEmployee",
            produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retrieves a single Employee object from a different microservice.")
    @ApiResponses(value = { @ApiResponse(code = SC_OK, message = "ok"),
                            @ApiResponse(code = SC_BAD_REQUEST, message = "Bad request.  Please try again.")
    })
    public ResponseEntity<Employee> getEmployee() {
        //This is here to demonstrate the use of Integration Services.  Please see the Note in
        // the HelloWorldMessageService class.
        Employee employee = helloWorldMessageService.getEmployee();
        if(employee == null) {
            return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @ExceptionHandler
    void handleIllegalArgumentException(
            IllegalArgumentException e,
            HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/projects",
            produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retrieves a list of Project objects.")
    @ApiResponses(value = { @ApiResponse(code = SC_OK, message = "ok"),
            @ApiResponse(code = SC_BAD_REQUEST,
                    message = "Bad request. Please try again."),
            @ApiResponse(code = SC_NO_CONTENT, message = "No content found.")
    })
    public ResponseEntity<List<Project>> getProjects(
            @ApiParam(value = "How many pages to offset the limit by.") @RequestParam(required = true, defaultValue = "0") Integer offset,
            @ApiParam(value = "The number of items limited to one page of data.") @RequestParam(required = true, defaultValue = "100") Integer limit) {
        Pageable sortedByDate = PageRequest.of(offset, limit);
        List<Project> messageList = projectService.findAll(sortedByDate).toList();
        if(CollectionUtils.isEmpty(messageList)) {
            return new ResponseEntity<List<Project>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Project>>(messageList, HttpStatus.OK);
    }


}
