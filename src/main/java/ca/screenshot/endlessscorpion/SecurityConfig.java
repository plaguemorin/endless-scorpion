package ca.screenshot.endlessscorpion;

/**
 * Created by plaguemorin on 22/07/15.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.openid.OpenIDAuthenticationToken;

@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private AuthenticationUserDetailsService<OpenIDAuthenticationToken> userDetailsService;

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/resources/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.openidLogin()
				.loginPage("/login")
				.permitAll()
				.authenticationUserDetailsService(userDetailsService)
				.attributeExchange("https://www.google.com/.*")
				.attribute("email")
				.type("http://axschema.org/contact/email")
				.required(true)
				.and()
				.attribute("firstname")
				.type("http://axschema.org/namePerson/first")
				.required(true)
				.and()
				.attribute("lastname")
				.type("http://axschema.org/namePerson/last")
				.required(true)
				.and()
				.and()
				.attributeExchange(".*yahoo.com.*")
				.attribute("email")
				.type("http://axschema.org/contact/email")
				.required(true)
				.and()
				.attribute("fullname")
				.type("http://axschema.org/namePerson")
				.required(true)
				.and()
				.and()
				.attributeExchange(".*myopenid.com.*")
				.attribute("email")
				.type("http://schema.openid.net/contact/email")
				.required(true)
				.and()
				.attribute("fullname")
				.type("http://schema.openid.net/namePerson")
				.required(true);
	}
}
