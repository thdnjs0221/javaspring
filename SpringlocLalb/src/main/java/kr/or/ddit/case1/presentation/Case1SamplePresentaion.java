package kr.or.ddit.case1.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kr.or.ddit.case1.service.Case1SampleService;
import kr.or.ddit.case1.service.Case1SampleServiceImpl;
import lombok.extern.slf4j.Slf4j;
@Controller
@Slf4j
public class Case1SamplePresentaion {
	
	private Case1SampleService service;
	
	public Case1SamplePresentaion() {
		super();
		log.info("기본 생성자로 {} 생성",this.getClass().getSimpleName());
	}

	@Autowired
	//주입할수 있는 방법1
	public Case1SamplePresentaion(Case1SampleService service) {
		super();
		this.service = service;
		log.info("{} 생성되었음. 생성자 주입으로 {} 로 주입받음",
				this.getClass().getSimpleName(), service.getClass().getSimpleName());
	}

	
	public void setService(Case1SampleService service) {
		this.service = service;
		//주입할수 있는 방법2
		log.info("setter 주입으로 {} 로 주입받음",
				this.getClass().getSimpleName(), service.getClass().getSimpleName());
	}

	public String makeContent(String pk) {
		StringBuffer model = service.retrieveSample(pk);
		return String.format("%s를 꾸며서 만들어진 content", model);
	}

}
