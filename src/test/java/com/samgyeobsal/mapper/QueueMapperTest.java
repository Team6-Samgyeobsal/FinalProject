package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.queue.QueueVO;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class QueueMapperTest {

    @Autowired
    QueueMapper queueMapper;
    @Test
    public void getQueueListTest(){
        List<QueueVO> list = queueMapper.getQueueList("1");

        list.forEach( queue-> log.info("queue = {}", queue));
    }
}
