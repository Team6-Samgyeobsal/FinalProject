package com.samgyeobsal.domain.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderListDetailVO {
    private String poid;
    private String pooption;
    private int amount;
}
