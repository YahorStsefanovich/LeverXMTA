package com.leverx.leverxspringproj.controller;

import com.leverx.leverxspringproj.service.CloudService;
import com.leverx.leverxspringproj.service.HomeService;
import com.leverx.leverxspringproj.service.SecurityService;
import com.sap.cloud.sdk.s4hana.connectivity.exception.AccessDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@Autowired
	private CloudService cloudService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private HomeService homeService;

	@GetMapping(value="/")
	public String getHome(Model model) {
		model = homeService.setAttributes(model, cloudService);
		return "index";
	}

	@GetMapping(value="/jwt")
	public String getJWT(Model model) {
		model = homeService.parseJWT(model, cloudService);
		return "jwt";
	}

	@GetMapping(value="/scope")
	public String checkScope() throws AccessDeniedException {
		securityService.userHasAuthorization("Display");
		return "scope";
	}
}
