package com.esta.assignment.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorResponse {

    public final String detail;

    public final String message;

    public ErrorResponse(Exception exception, String detail) {
        this.message = exception.getLocalizedMessage();
        this.detail = detail;
    }

}