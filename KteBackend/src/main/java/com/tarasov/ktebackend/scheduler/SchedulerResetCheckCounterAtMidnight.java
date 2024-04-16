package com.tarasov.ktebackend.scheduler;

import com.tarasov.ktebackend.service.SaleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableScheduling
@EnableAsync
@RequiredArgsConstructor
public class SchedulerResetCheckCounterAtMidnight {
    private final SaleService saleService;

    @Scheduled(cron = "@midnight")
    @Async
    void resetCheckCounterAtMidnight() {

        saleService.reset();
    }
}
