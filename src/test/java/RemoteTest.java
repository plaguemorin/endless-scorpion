import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

/**
 * Created by plaguemorin on 13/07/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestAppConfiguration.class)
//@WebAppConfiguration
//@IntegrationTest({"server.port=0"})
public class RemoteTest {
	//@Value("${local.server.port}")
	//private int port;

	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void test() {

	}
}
