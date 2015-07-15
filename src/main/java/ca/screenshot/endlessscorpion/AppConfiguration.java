package ca.screenshot.endlessscorpion;

import ca.screenshot.endlessscorpion.controllers.RestEndpoint;
import ca.screenshot.endlessscorpion.remote.SubscriptionService;
import ca.screenshot.endlessscorpion.remote.appdirect.RestSubscriptionService;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.signature.QueryStringSigningStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.xml.transform.Source;
import java.util.Collections;

/**
 * Created by plaguemorin on 11/07/15.
 *
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
	public OAuthConsumer oAuthConsumer() {
		final OAuthConsumer consumer = new DefaultOAuthConsumer("endless-scorpion-31185", "NfYatL9YqPkxJkHo");
		consumer.setSigningStrategy(new QueryStringSigningStrategy());
		return consumer;
	}

	@Bean
	public RestEndpoint restEndpoint() {
		return new RestEndpoint();
	}
}
