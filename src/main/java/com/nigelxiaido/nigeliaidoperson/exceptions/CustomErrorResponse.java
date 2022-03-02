package com.nigelxiaido.nigeliaidoperson.exceptions;

import java.util.Date;

public class CustomErrorResponse {
    private Date timestamp;
    private String message;
    private String error;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public CustomErrorResponse(Date timestamp, String message, String error) {
        this.timestamp = timestamp;
        this.message = message;
        this.error = error;
    }
}
