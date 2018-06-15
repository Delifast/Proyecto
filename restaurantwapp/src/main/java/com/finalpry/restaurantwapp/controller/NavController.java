package com.finalpry.restaurantwapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController {

	   @GetMapping("/landingpage")
	    public String landing() {
	        return "landingpage";
	    }
	    
	    @GetMapping("/restaurant")
	    public String restaurant() {
	        return "restaurant";
	    }
}
