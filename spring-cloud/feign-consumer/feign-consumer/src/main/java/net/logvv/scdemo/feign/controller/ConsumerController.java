package net.logvv.scdemo.feign.controller;

import net.logvv.scdemo.feign.impl.CalculateClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {
	@Autowired
	private CalculateClient calculateClient;
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public Integer add(@RequestParam("a")Integer a,@RequestParam("b")Integer b){
		return calculateClient.add(10, 20);
	}
}
