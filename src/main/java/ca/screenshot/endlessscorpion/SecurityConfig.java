package ca.screenshot.endlessscorpion;

/**
 * Created by plaguemorin on 22/07/15.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private AuthenticationUserDetailsService<OpenIDAuthenticationToken> userDetailsService;

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
				.csrf().disable();
		http
				.authorizeRequests()
				.antMatchers("/", "/home", "/appdirect/**", "/test/**").permitAll()
				.anyRequest().authenticated();
		http
				.openidLogin()
				.permitAll()
				.authenticationUserDetailsService(userDetailsService)
				.attributeExchange("https://www.appdirect.com.*")
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
				.required(true);
		http
				.logout()
				.logoutSuccessHandler(new CustomLogoutSuccessHandler());
	}

	private static class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
		@Override
		public void onLogoutSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException, ServletException {
			if (authentication instanceof OpenIDAuthenticationToken) {
				final OpenIDAuthenticationToken openIDToken = (OpenIDAuthenticationToken) authentication;
				response.sendRedirect("https://www.appdirect.com/applogout?openid=" + openIDToken.getIdentityUrl());
			} else {
				response.sendRedirect("/?logout");
			}
		}
	}

}
