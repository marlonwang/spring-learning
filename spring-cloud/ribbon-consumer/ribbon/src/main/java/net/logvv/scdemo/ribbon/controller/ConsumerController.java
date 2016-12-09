package net.logvv.scdemo.ribbon.controller;

import net.logvv.scdemo.ribbon.service.CalculateService;

import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

//    无熔断机制
//    @Autowired
//    RestTemplate restTemplate;
//    @RequestMapping(value = "/add", method = RequestMethod.GET)
//    public String add() {
//        return restTemplate.getForEntity("http://CALCULATE-SERVICE/add?a=10&b=20", String.class).getBody();
//    }
    
    @Autowired
    private CalculateService computeService;
    
    // 带熔断处理的 calculateService
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addWithCircuit(@RequestParam("a")int a,@RequestParam("b")int b) {
        return computeService.addService(a,b);
    }
    
}