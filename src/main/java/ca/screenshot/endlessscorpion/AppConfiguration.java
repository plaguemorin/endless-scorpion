package ca.screenshot.endlessscorpion;

import ca.screenshot.endlessscorpion.controllers.RestEndpoint;
import ca.screenshot.endlessscorpion.remote.SubscriptionService;
import ca.screenshot.endlessscorpion.remote.appdirect.RestSubscriptionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.xml.transform.Source;
import java.util.Collections;

/**
 * Created by plaguemorin on 11/07/15.
 */
@Configuration
public class AppConfiguration {

	@Bean
	public SubscriptionService appDirectSubscription() {
		return new RestSubscriptionService();
	}

	@Bean
	public RestTemplate restTemplate() {
		final RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(
				Collections.<HttpMessageConverter<?>>singletonList(new SourceHttpMessageConverter<Source>())
		);

		return restTemplate;
	}

	@Bean
	public Object restEndpoint() {
		return new RestEndpoint();
	}
}
