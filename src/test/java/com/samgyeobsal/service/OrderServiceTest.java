package com.samgyeobsal.service;

import com.samgyeobsal.domain.order.OrderFormDTO;
import com.samgyeobsal.domain.order.OrderStep1DTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

}
