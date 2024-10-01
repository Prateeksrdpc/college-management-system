package org.jsp.College_Management_App.payload;



public class MessageResponse {

    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    // Getter
    public String getMessage() {
        return message;
    }
}

