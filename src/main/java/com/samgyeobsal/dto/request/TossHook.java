package com.samgyeobsal.dto.request;

import lombok.Data;

@Data
public class TossHook {

    private String createAt;
    private String secret;
    private String status;
    private String transactionKey;
    private String orderId;
}
