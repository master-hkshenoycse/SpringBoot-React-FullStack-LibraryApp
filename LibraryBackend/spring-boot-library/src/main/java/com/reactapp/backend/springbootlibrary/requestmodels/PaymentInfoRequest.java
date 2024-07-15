package com.reactapp.backend.springbootlibrary.requestmodels;


import lombok.Data;

@Data
public class PaymentInfoRequest {
    private int amount;
    private String currency;
    private String receiptEmail;

}
