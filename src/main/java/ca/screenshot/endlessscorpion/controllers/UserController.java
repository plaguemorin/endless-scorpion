package ca.screenshot.endlessscorpion.controllers;

import ca.screenshot.endlessscorpion.services.UserDataStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.openid.OpenIDAuthenticationStatus;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

	@Autowired
	UserDataStorage profileService;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String showCurrentUser(final Model model, final OpenIDAuthenticationToken authentication) {
		model.addAttribute("authentication", authentication);
		model.addAttribute("authenticated", authentication != null ? OpenIDAuthenticationStatus.SUCCESS.equals(authentication.getStatus()) : Boolean.FALSE);
		return "user/show";
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String showAllUsers(final Model model, final OpenIDAuthenticationToken authentication) {
		model.addAttribute("users", this.profileService.findAll());
		model.addAttribute("authenticated", authentication != null ? OpenIDAuthenticationStatus.SUCCESS.equals(authentication.getStatus()) : Boolean.FALSE);
		return "users/show";
	}

}
