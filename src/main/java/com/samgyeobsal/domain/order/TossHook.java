package com.samgyeobsal.domain.order;

import lombok.Data;

@Data
public class TossHook {

    private String createAt;
    private String secret;
    private String status;
    private String transactionKey;
    private String orderId;
    private String customerMobilePhone;
}