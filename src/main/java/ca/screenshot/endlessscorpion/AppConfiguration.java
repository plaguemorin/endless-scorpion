package ca.screenshot.endlessscorpion;

import ca.screenshot.endlessscorpion.services.DefaultEventProcessService;
import ca.screenshot.endlessscorpion.services.EventDispatchService;
import ca.screenshot.endlessscorpion.services.EventProcessService;
import ca.screenshot.endlessscorpion.services.SimpleTaskExecutorEventDispatchService;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.signature.QueryStringSigningStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by plaguemorin on 11/07/15.
 * --
 */
@Configuration
public class AppConfiguration {
	@Value("#{environment.consumerKey}")
	private String oauthConsumerKey;
	@Value("#{environment.consumerSecret}")
	private String oauthConsumerSecret;

	@Bean
	public RestTemplate restTemplate() {
		final RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(
				Arrays.<HttpMessageConverter<?>>asList(new SourceHttpMessageConverter<>(), new Jaxb2RootElementHttpMessageConverter())
		);

		return restTemplate;
	}

	@Bean
	public TaskExecutor executor() {
		final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5);
		executor.setMaxPoolSize(10);
		executor.setQueueCapacity(25);

		return executor;
	}

	@Bean
	public EventDispatchService eventDispatchService() {
		// <Got a new event, fetch it> Object
		return new SimpleTaskExecutorEventDispatchService();
	}

	@Bean
	public EventProcessService eventProcessService() {
		// <Fetched an event, process it> Object
		return new DefaultEventProcessService();
	}

	@Bean
	public OAuthConsumer oAuthConsumer() {
		// TODO: Don't hardcode the credentials
		// Really, don't
		final OAuthConsumer consumer = new DefaultOAuthConsumer(this.oauthConsumerKey, this.oauthConsumerSecret);
		consumer.setSigningStrategy(new QueryStringSigningStrategy());
		return consumer;
	}
}
