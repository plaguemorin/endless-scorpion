package ca.screenshot.endlessscorpion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by plaguemorin on 12/07/15.
 */
@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties
@ComponentScan
public class Application {
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String args[]) {
		final ApplicationContext ctx = SpringApplication.run(Application.class, args);
	}

}
