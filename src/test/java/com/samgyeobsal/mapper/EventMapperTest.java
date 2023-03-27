package com.samgyeobsal.mapper;

import java.time.LocalDateTime;
import java.util.List;

import com.samgyeobsal.domain.event.Event;
import com.samgyeobsal.dto.response.UserCouponList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class EventMapperTest {

    @Autowired
    private EventMapper eventMapper;

    @Test
    public void testFindByName() {
        Event event = eventMapper.findByName("exampleName");
        log.info(event);
    }

    @Test
    public void testFindUserCouponList() {
        List<UserCouponList> userCouponList = eventMapper.findUserCouponList("exampleEmail");
        log.info(userCouponList);
    }

    @Test
    public void testUpdateUseDate() {
        int result = eventMapper.updateUseDate(LocalDateTime.now(), "exampleId");
        log.info(result);
    }

}
