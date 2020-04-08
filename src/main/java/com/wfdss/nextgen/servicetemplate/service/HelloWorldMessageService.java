package com.wfdss.nextgen.servicetemplate.service;

import com.wfdss.nextgen.servicetemplate.integrationservice.SampleIntegrationService;
import com.wfdss.nextgen.servicetemplate.model.Employee;
import com.wfdss.nextgen.servicetemplate.model.HelloWorldMessage;
import com.wfdss.nextgen.servicetemplate.repository.HelloWorldMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
public class HelloWorldMessageService {

    @Autowired
    private HelloWorldMessageRepository helloWorldMessageRepository;

    @Autowired
    private SampleIntegrationService sampleIntegrationService;

    /**
     * Method to create a HelloWorldMessage object from supplied message text.
     *
     * @param messageText The messageText String for the object
     * @return HelloWorldMessage object
     */
    public HelloWorldMessage populateHelloWorldMessageString(String messageText) {
        HelloWorldMessage helloWorldMessage = new HelloWorldMessage();
        helloWorldMessage.setMessageText(messageText);
        return helloWorldMessage;
    }

    /**
     * Retrieves a random HelloWorldMessage object from the database.
     *
     * @return HelloWorldMessage object
     */
    public HelloWorldMessage getHelloWorldMessageObjectRandom() {
        Random random = new Random();
        Integer maxId = getHelloWorldMessageList().size();
        Integer id = random.nextInt(maxId) + 1;
        HelloWorldMessage message = helloWorldMessageRepository.getOne(id);
        return message;
    }

    /**
     * Retrieves the HelloWorldMessage object with id = id from the database.
     *
     * @param id The id of the HelloWorldMessage object requested
     * @return HelloWorldMessage object
     */
    public HelloWorldMessage getHelloWorldMessageObjectById(Integer id) {
        Optional<HelloWorldMessage> message = helloWorldMessageRepository.findById(id);
        if(message.isPresent()) {
            return message.get();
        }
        log.info("Message with id = {} not found.", id);
        return null;
    }

    /**
     * Returns a List of all HelloWorldMessage objects in the database.
     *
     * @return List<HelloWorldMessage> objects
     */
    public List<HelloWorldMessage> getHelloWorldMessageList() {
        return helloWorldMessageRepository.findAll();
    }

    /**
     * Adds a new HelloWorldMessage object to the database.
     *
     * @param message The message object to be created.
     */
    public HelloWorldMessage addHelloWorldMessage(HelloWorldMessage message) {
        HelloWorldMessage newMessage = helloWorldMessageRepository.save(message);
        log.info("HelloWorldMessage object created: {}", newMessage);
        return newMessage;
    }

    /**
     * Updates the message text of a HelloWorldMessage object and saves to the database.
     *
     * @param id The ID of the message object to be updated
     * @param message The message object to be created.
     */
    public HelloWorldMessage updateHelloWorldMessage(Integer id, HelloWorldMessage message) throws Exception {
        String newMessageText = message.getMessageText();
        Optional<HelloWorldMessage> messageToUpdate = helloWorldMessageRepository.findById(id);
        if(messageToUpdate.isPresent()) {
            message = messageToUpdate.get();
            message.setMessageText(newMessageText);
            return helloWorldMessageRepository.save(message);
        } else {
            log.info("Message with id = {} does not exist.", id);
            throw new Exception("Message with id = " + id + " does not exist.");
        }
    }

    /**
     * Retrieves an Employee object from the SampleIntegrationService.
     *
     * @return Employee object
     */
    public Employee getEmployee() {
        //NOTE: Typically, integration services are called when you need to use the data from
        // another service to perform a function specific to the calling service.  For example,
        // in WFDSS NextGen, we may need to call the Incident service from the User service to
        // create a list of the Incidents associated with a particular User.
        return sampleIntegrationService.getEmployee();
    }
}
