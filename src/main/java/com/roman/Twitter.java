package com.roman;

import java.util.ArrayList;
import java.util.List;

public class Twitter implements Quoter {

    private List<String> messages = new ArrayList<>();

    @InjectRandomString(fieldName = "messages")
    private String message;

    public Twitter() {
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void sayQuote() {
        System.out.println("Message: " + message);
    }
}
