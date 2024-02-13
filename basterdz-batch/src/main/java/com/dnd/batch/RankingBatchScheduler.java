package com.dnd.batch;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RankingBatchScheduler {

	private final RankingJob rankingJob;

	@Scheduled(cron = "30 0 * * *")
	public void run() {
		rankingJob.run();
	}
}
