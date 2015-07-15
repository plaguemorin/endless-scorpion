package ca.screenshot.endlessscorpion.remote.appdirect;

import ca.screenshot.endlessscorpion.remote.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestSubscriptionService implements SubscriptionService {
	@Autowired
	private RestTemplate template;


}
