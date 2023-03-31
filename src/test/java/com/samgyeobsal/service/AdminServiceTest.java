package com.samgyeobsal.service;

import com.samgyeobsal.domain.order.OrderVO;
import com.samgyeobsal.mapper.AdminMapper;
import com.samgyeobsal.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Slf4j
public class AdminServiceTest {

    @Autowired
    private AdminService adminService;


    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper orderMapper;

//    @Test
//    @Transactional
//    void promoteFundingToStore(){
//        String fid = "1";
//        adminService.promoteFundingToStore(fid);
//        List<String> orderIdList = orderMapper.findOrderIdListByFundingId(fid);
//        for (String orderId : orderIdList) {
//            OrderVO orderByOrderId = orderService.getOrderByOrderId(orderId);
//            log.info("order = {}", orderByOrderId);
//        }
//    }
}
