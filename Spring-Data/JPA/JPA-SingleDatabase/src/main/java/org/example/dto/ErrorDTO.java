package org.example.dto;

public class ErrorDTO {

    private String error_message;

    public ErrorDTO() {
    }

    public ErrorDTO(String error_message) {
        this.error_message = error_message;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

}
