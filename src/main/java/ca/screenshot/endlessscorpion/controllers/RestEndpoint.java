package ca.screenshot.endlessscorpion.controllers;

import ca.screenshot.endlessscorpion.model.Company;
import ca.screenshot.endlessscorpion.services.CompanyDataStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by plaguemorin on 13/07/15.
 */
@RestController
public class RestEndpoint {
	private static final Logger LOG = LoggerFactory.getLogger(RestEndpoint.class);

	@Autowired
	private CompanyDataStorage companyDataStorage;

	@RequestMapping("/")
	public String index() {
		final StringBuilder stringBuffer = new StringBuilder();
		LOG.info("Index requested");

		// Truth be told, this is very ugly
		// Don't do this at home kids
		stringBuffer.append("<ol>");
		final Iterable<Company> comps = this.companyDataStorage.findAll();
		for (final Company company : comps) {
			stringBuffer.append("<li><a href=\"company/");
			stringBuffer.append(company.getUuid());
			stringBuffer.append("\">");
			stringBuffer.append(company.getName());
			stringBuffer.append("</a></li>\n");
		}
		stringBuffer.append("</ol>");

		return stringBuffer.toString();
	}
}
