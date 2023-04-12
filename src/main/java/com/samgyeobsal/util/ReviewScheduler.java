package com.samgyeobsal.util;

import com.samgyeobsal.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
@Async
@Log4j2
@RequiredArgsConstructor
@Configuration
@EnableScheduling
public class ReviewScheduler {
    private final ReviewMapper reviewMapper;

    @Scheduled(cron = "0 0 * * * *") // 매 시간 정각마다 실행
    public void updateScores() {
        log.info("스코어 업데이트");
        reviewMapper.updateScore();
    }
}

