package com.shop.utility;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorInfo {

    public Integer errorCode;
    public String errorMessage;
    public LocalDateTime timeStamp;
}
