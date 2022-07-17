package com.fayardev.regmsfileserver;

public record UploadResponseMessage(String responseMessage) {

    public String getResponseMessage() {
        return responseMessage;
    }
}