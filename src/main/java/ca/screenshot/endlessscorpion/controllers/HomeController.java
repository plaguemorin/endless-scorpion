package ca.screenshot.endlessscorpion.controllers;

import ca.screenshot.endlessscorpion.model.Company;
import ca.screenshot.endlessscorpion.services.CompanyDataStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.openid.OpenIDAuthenticationStatus;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by plaguemorin on 13/07/15.
 */
@Controller
public class HomeController {
	private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private CompanyDataStorage companyDataStorage;

	@RequestMapping("/")
	public String index(final Model model, final OpenIDAuthenticationToken authentication) {
		model.addAttribute("authenticated", authentication != null ? OpenIDAuthenticationStatus.SUCCESS.equals(authentication.getStatus()) : Boolean.FALSE);

		return "index";
	}

	@RequestMapping("/show/")
	public String show(final Model model, final OpenIDAuthenticationToken authentication) {
		model.addAttribute("authentication", authentication);
		return "show";
	}

	@RequestMapping("/company/{companyUuid}/")
	public String companyInfo(@PathVariable("companyUuid") final String companyUuid) {
		final StringBuilder stringBuffer = new StringBuilder();
		LOG.info("Info on company {}", companyUuid);
		final Company comp = companyDataStorage.findByUuid(companyUuid);

		stringBuffer.append("<h1>");
		stringBuffer.append(comp.getName());
		stringBuffer.append("</h1>");

		// List all subscription for that company


		return stringBuffer.toString();
	}
}
