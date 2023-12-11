package com.anboyoung.socket.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service     // Bean으로 생성하기 위해서, 보통 서비스로 맹금!
public class ScheduleTest {

	@Scheduled(fixedDelay = 3000)
	public void runPerThreeSeconds() {
		log.debug("check:" + "3초마다 찍혀용");
	}
	                 /*초     분     시     일     월      요일 */
	@Scheduled(cron = "30  06  14   *   *    *")
	public void runPerCronSetting() {
		log.debug("check:" + "매일 14시 06분 30초에 찍혀용");
	}
}
