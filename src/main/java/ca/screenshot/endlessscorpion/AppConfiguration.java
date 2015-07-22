package ca.screenshot.endlessscorpion;

import ca.screenshot.endlessscorpion.services.DefaultEventProcessService;
import ca.screenshot.endlessscorpion.services.EventDispatchService;
import ca.screenshot.endlessscorpion.services.EventProcessService;
import ca.screenshot.endlessscorpion.services.SimpleTaskExecutorEventDispatchService;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.signature.QueryStringSigningStrategy;
import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

/**
 * Created by plaguemorin on 11/07/15.
 * --
 */
@Configuration
@EnableJpaRepositories
public class AppConfiguration {
	private static final Logger LOG = LoggerFactory.getLogger(AppConfiguration.class);

	@Value("#{environment.consumerKey}")
	private String oauthConsumerKey;
	@Value("#{environment.consumerSecret}")
	private String oauthConsumerSecret;
	@Value("#{environment.DATABASE_URL}")
	private String databaseUrl;

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

		/* Magic numbers ahead! */
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
		// Don't hardcode the credentials. Really, don't
		final OAuthConsumer consumer = new DefaultOAuthConsumer(this.oauthConsumerKey, this.oauthConsumerSecret);
		consumer.setSigningStrategy(new QueryStringSigningStrategy());

		LOG.info("Using consumer key = {}", this.oauthConsumerKey);

		return consumer;
	}

	@Bean
	public AuthenticationUserDetailsService<OpenIDAuthenticationToken> userDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public SecurityConfig securityConfig() {
		return new SecurityConfig();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(final DataSource dataSource, final JpaVendorAdapter jpaVendorAdapter) {
		final LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
		lef.setDataSource(dataSource);
		lef.setJpaVendorAdapter(jpaVendorAdapter);
		lef.setPackagesToScan("ca.screenshot");
		return lef;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		final HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setShowSql(true);
		jpaVendorAdapter.setGenerateDdl(true); //Auto creating scheme when true
		jpaVendorAdapter.setDatabase(Database.POSTGRESQL);//Database type
		return jpaVendorAdapter;
	}

	@Bean
	public DataSource dataSource() throws URISyntaxException {
		final URI dbUri = new URI(this.databaseUrl);

		final String[] split = dbUri.getUserInfo().split(":");
		final String username = split[0];
		final String password = split[1];

		final String dbUrl = String.format("jdbc:postgresql://%s:%d%s", dbUri.getHost(), dbUri.getPort(), dbUri.getPath());
		LOG.info("Using database {} with user {}", dbUrl, username);

		final BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setUrl(dbUrl);
		basicDataSource.setUsername(username);
		basicDataSource.setPassword(password);
		return basicDataSource;
	}
}
