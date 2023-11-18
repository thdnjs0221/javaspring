package com.anboyoung.socket.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service  //Bean으로 생성하기 보통 서비스로 만든다
public class ScheduleTest {
	

	@Scheduled(fixedDelay = 3000)
	public void runPerThreeSeconds() {
		log.debug("check:" + "3초마다 찍혀용");
	}
	                                /*초   분   시   일  월  요일 */
	@Scheduled(cron = "10  10  10   *   *    *")
	public void runPerCronSetting() {
		log.debug("check:" + "매일 10시 10분 10초에 찍혀용");
	}

}
