package com.wfdss.nextgen.servicetemplate.service;

import com.wfdss.nextgen.servicetemplate.model.HelloWorldMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
public class HelloWorldMessageServiceTest {

    @Autowired
    private HelloWorldMessageService helloWorldMessageService;

    @Test
    public void populateHelloWorldMessageString_happyPath() {
        String test = "test.";
        HelloWorldMessage helloWorldMessage =
                helloWorldMessageService.populateHelloWorldMessageString(test);
        assertEquals(test, helloWorldMessage.getMessageText());
    }

    @Test
    public void getHelloWorldMessageObjectRandom_happyPath() {
        HelloWorldMessage message = helloWorldMessageService.getHelloWorldMessageObjectRandom();
        assertNotNull(message);
    }

    @Test
    public void getHelloWorldMessageObjectById_happyPath() {
        String expText = "Hello World!";
        HelloWorldMessage message = helloWorldMessageService.getHelloWorldMessageObjectById(1);
        String actualText = message.getMessageText();
        assertTrue(expText.equals(actualText));
    }

    @Test
    public void getHelloWorldMessageList_happyPath() {
        List<HelloWorldMessage> messageList = helloWorldMessageService.getHelloWorldMessageList();
        assertEquals(3, messageList.size());
    }

    @Test
    public void addHelloWorldMessage_happyPath() {
        List<HelloWorldMessage> messageList = helloWorldMessageService.getHelloWorldMessageList();
        Integer listSize = messageList.size();
        HelloWorldMessage message = new HelloWorldMessage(null, "A new message!");
        helloWorldMessageService.addHelloWorldMessage(message);
        List<HelloWorldMessage> updatedMessageList =
                helloWorldMessageService.getHelloWorldMessageList();
        assertEquals(listSize + 1, updatedMessageList.size());
    }

    @Test
    public void updateHelloWorldMessage_happyPath() {
        HelloWorldMessage message = new HelloWorldMessage(null, "Original Text");
        message = helloWorldMessageService.addHelloWorldMessage(message);
        String newText = "New Text";
        message.setMessageText(newText);
        try {
            HelloWorldMessage updatedMessage =
                    helloWorldMessageService.updateHelloWorldMessage(message.getId(), message);
            assertTrue(updatedMessage.getMessageText().equals(newText));
        } catch(Exception e) {
            System.out.println(e.getMessage());
            fail("Exception caused.");
        }
    }
}