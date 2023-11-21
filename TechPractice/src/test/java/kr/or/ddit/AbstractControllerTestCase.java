package kr.or.ddit;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ContextHierarchy({
	@ContextConfiguration(name="parent", locations = "file:src/main/resources/kr/or/ddit/spring/*-context.xml")
	, @ContextConfiguration(name="child", locations = "file:src/main/webapp/WEB-INF/spring/appServlet/*.xml")
})
public abstract class AbstractControllerTestCase extends AbstractRootTestCase{

	@Inject
	private WebApplicationContext context;
	
	protected MockMvc mockMvc;

	@BeforeEach
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
}
