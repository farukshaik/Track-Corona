package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Modelclass;
import com.example.demo.service.Serviceclass;

@RestController
public class Controllerclass {

	@Autowired
	Serviceclass sc;

	@GetMapping(value = "/getData")
	public Modelclass getService(@RequestParam String stateName) throws Exception {
		return sc.getData(stateName);
	}

}
